package cl.perrosky.organizapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import cl.perrosky.organizapp.R;
import cl.perrosky.organizapp.adapter.EscanearActivity;
import cl.perrosky.organizapp.bbdd.impl.ProductoDataSource;
import cl.perrosky.organizapp.model.GrupoProducto;
import cl.perrosky.organizapp.model.Producto;
import cl.perrosky.organizapp.ui.adapter.GrupoProductoAdapter;

public class ListaStockProductoActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ProductoDataSource dataSource;
    private List<GrupoProducto> listado;


    private EditText txtCodigoBarra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_stock_producto);

        dataSource = new ProductoDataSource(this);

        listado = new ArrayList<GrupoProducto>();

        asignarLista();

        ActionBar barra = getSupportActionBar();
        barra.setDisplayHomeAsUpEnabled(true);

        barra.setTitle(R.string.btn_list_producto);

        txtCodigoBarra = (EditText) findViewById(R.id.codigo);
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
        GrupoProducto gp = listado.get(position);
        editarProducto(gp);
    }

    private void editarProducto(GrupoProducto grupoProducto) {
        Intent i = new Intent(ListaStockProductoActivity.this, EditarStockProductoActivity.class);
        i.putExtra(EditarStockProductoActivity.RETORNO, grupoProducto);
        startActivityForResult(i, EditarStockProductoActivity.CODIGO_INTENT);
    }

    private void asignarLista() {
        GrupoProductoAdapter adapter = new GrupoProductoAdapter(this, listado);

        ListView listView = (ListView) findViewById(R.id.listado);
        listView.setAdapter(adapter); // asignamos los datos
        listView.setOnItemClickListener(this); // // asignamos el escucha de eventos
    }

    public void escanearProducto(View vista) {
        Intent i = new Intent(ListaStockProductoActivity.this, EscanearActivity.class);
        startActivityForResult(i, EscanearActivity.CODIGO_INTENT);
    }

    public void agregarProductos(View view){
/*
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
 */
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.i("INFORMACION DEL RETORNO", "requestCode : "  + String.valueOf(requestCode));
        Log.i("INFORMACION DEL RETORNO", "resultCode : "  + String.valueOf(resultCode));

        if (requestCode == EscanearActivity.CODIGO_INTENT) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    String codigo = data.getStringExtra(EscanearActivity.RETORNO);

                    GrupoProducto grupoProducto = new GrupoProducto();
                    Producto producto = (codigo != null && !codigo.isEmpty()) ? dataSource.buscarProducto(codigo) : null;

                    if (producto == null) {
                        producto = new Producto();
                        producto.setCodigoDeBarras(codigo);
                    }
                    grupoProducto.setProducto(producto);

                    editarProducto(grupoProducto);
                }
            }
        }

        if (requestCode == EditarStockProductoActivity.CODIGO_INTENT) {
            Log.i("INFORMACION DEL RETORNO", "requestCode == EditarStockProductoActivity.CODIGO_INTENT");

            if (resultCode == Activity.RESULT_OK) {
                Log.i("INFORMACION DEL RETORNO", "resultCode == Activity.RESULT_OK");
                if (data != null) {
                    Log.i("INFORMACION DEL RETORNO", "data != null");
                    Log.i("INFORMACION DEL RETORNO", "Listado es = " + String.valueOf(listado.size()));
                    GrupoProducto gp = (GrupoProducto) data.getSerializableExtra(EditarStockProductoActivity.RETORNO);

                    if (gp != null) {
                        if(gp.getId().equals(0)){
                            gp.setId(listado.size()+1);
                            listado.add(gp);
                        } else {
                            listado.set((gp.getId()-1), gp);
                        }
                        asignarLista();
                    }
                }
            }
        }
    }
}
// ((TextView)findViewById(R.id.cantidad)).setText(producto==null?"":producto.getUnidades().toString());
/*
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
 */