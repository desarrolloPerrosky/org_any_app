package cl.perrosky.organizapp.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cl.perrosky.organizapp.R;
import cl.perrosky.organizapp.adapter.EscanearActivity;
import cl.perrosky.organizapp.bbdd.impl.ProductoDataSource;
import cl.perrosky.organizapp.model.Producto;
import cl.perrosky.organizapp.ui.adapter.ProductoAdapter;

public class EditarStockProductoActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final int CODIGO_INTENT = 2;

    private ProductoDataSource dataSource;
    private List<Producto> listado;

    private EditText txtCodigoBarra;
    private EditText txtCantidad;

    private View lyDetalle;
    private View lyLista;

    private Producto producto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_stock_producto);
//        setContentView(R.layout.activity_list_generic);

        producto = null;

        dataSource = new ProductoDataSource(this);

        ActionBar barra = getSupportActionBar();
        barra.setDisplayHomeAsUpEnabled(true);

        barra.setTitle(R.string.btn_list_producto);

        txtCodigoBarra = (EditText) findViewById(R.id.codigo);
        lyDetalle = (View) findViewById(R.id.detalleProducto);
        lyLista = (View) findViewById(R.id.listadoLayout);

        listado = new ArrayList<Producto>();
        listado.add(new Producto());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_listado, menu);

        menu.findItem(R.id.agregar)
                .setVisible(true)
                .setTitle(R.string.btn_add_producto);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.agregar:
                startActivity(new Intent(getApplicationContext(), EditarProductoActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Producto producto = listado.get(position);

        Intent intent = new Intent(getApplicationContext(), EditarProductoActivity.class);
        intent.putExtra(Producto.TABLA, producto);
        startActivity(intent);
    }

    public void escanear(View vista) {
        Intent i = new Intent(EditarStockProductoActivity.this, EscanearActivity.class);
        startActivityForResult(i, CODIGO_INTENT);
    }

    public void agregarProductos(View view){
        if(producto==null)return;

        // TODO
        Log.i("ANTES DEL ADD", producto.toString());
        listado.add(producto);

        producto = null;

        ProductoAdapter adapter = new ProductoAdapter(this, listado);
        ListView listView = (ListView) findViewById(R.id.listado);
        listView.setAdapter(adapter); // asignamos los datos
        listView.setOnItemClickListener(this); // // asignamos el escucha de eventos

        txtCodigoBarra.setText("");
        lyDetalle.setVisibility(View.INVISIBLE);
        lyLista.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODIGO_INTENT) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    String codigo = data.getStringExtra(EscanearActivity.RETORNO);

                    producto = (codigo!=null && !codigo.isEmpty()) ? dataSource.buscarProducto(codigo) : null;
                   // ((TextView)findViewById(R.id.cantidad)).setText(producto==null?"":producto.getUnidades().toString());

                    if(producto!=null){
                        Log.i("ESTADO DEL PRODUCTO LEIDO", producto.toString());

                        ((TextView)findViewById(R.id.nombre)).setText(producto.getNombre());
                        ((TextView)findViewById(R.id.descripcion)).setText(producto.getDescripcion());
                        //((TextView)findViewById(R.id.marca)).setText(codigo);
                        //((TextView)findViewById(R.id.categoria)).setText(producto.getCategoria().getNombre());

                        lyDetalle.setVisibility(View.VISIBLE);
                        lyLista.setVisibility(View.INVISIBLE);
                    } else {
                        txtCodigoBarra.setText(codigo);
                        lyDetalle.setVisibility(View.INVISIBLE);
                        lyLista.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }
}