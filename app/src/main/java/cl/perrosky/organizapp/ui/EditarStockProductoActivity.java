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
import android.widget.EditText;
import android.widget.TextView;

import cl.perrosky.organizapp.R;
import cl.perrosky.organizapp.adapter.EscanearActivity;
import cl.perrosky.organizapp.bbdd.impl.ProductoDataSource;
import cl.perrosky.organizapp.model.GrupoProducto;
import cl.perrosky.organizapp.model.Producto;

public class EditarStockProductoActivity extends AppCompatActivity {

    public static final String RETORNO = "producto";
    public static final int CODIGO_INTENT = 5;

    private ProductoDataSource dataSource;


    private TextView txtNombre;
    private TextView txtDescripcion;
    private TextView txtMarca;
    private TextView txtCategoria;

    private EditText txtCantidad;
    private EditText txtValor;

    private GrupoProducto grupoProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_stock_producto);
//        setContentView(R.layout.activity_list_generic);

        grupoProducto = null;

        dataSource = new ProductoDataSource(this);

        ActionBar barra = getSupportActionBar();
        barra.setDisplayHomeAsUpEnabled(true);

        barra.setTitle("Agregando productos");

        grupoProducto = (GrupoProducto) getIntent().getSerializableExtra(RETORNO);

        txtCantidad = (EditText) findViewById(R.id.txt_cantidad);
        txtValor = (EditText) findViewById(R.id.txt_precio);

        txtNombre = (TextView) findViewById(R.id.nombre);
        txtDescripcion = (TextView) findViewById(R.id.descripcion);
        txtMarca = (TextView) findViewById(R.id.marca);
        txtCategoria = (TextView) findViewById(R.id.categoria);

        if(grupoProducto.getProducto().getId().equals(0L)){
            editarProducto();
        } else {
            refreshVista();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(grupoProducto !=null){
            txtCantidad.setText(grupoProducto.getCantidad().toString());
            txtValor.setText(grupoProducto.getPrecio().toString());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_listado, menu);

        menu.findItem(R.id.editar)
                .setVisible(true)
                .setTitle(R.string.btn_edit_producto);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.editar:
                editarProducto();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void agregarProductos(View view){

        grupoProducto.setCantidad(Long.valueOf(txtCantidad.getText().toString()));
        grupoProducto.setPrecio(Long.valueOf(txtValor.getText().toString()));

        Intent intentRegreso = new Intent();
        intentRegreso.putExtra(RETORNO, grupoProducto);
        setResult(Activity.RESULT_OK, intentRegreso);

        // Cerrar la actividad. Ahora mira onActivityResult de MainActivity
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == EditarProductoActivity.CODIGO_INTENT) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    Producto producto = (Producto) data.getSerializableExtra(EditarProductoActivity.RETORNO);
                    if(producto!=null){
                        Log.i("DESDE EDITAR PRODUCTO", producto.toString());
                        grupoProducto.setProducto(producto);
                        refreshVista();
                    }
                }
            }
        }
    }

    private void editarProducto(){
        Intent i = new Intent(EditarStockProductoActivity.this, EditarProductoActivity.class);
        i.putExtra(EditarProductoActivity.RETORNO, grupoProducto.getProducto());
        startActivityForResult(i, EditarProductoActivity.CODIGO_INTENT);
    }

    private void refreshVista(){
        Log.i("REFRESH VISTA", grupoProducto.toString());
        txtNombre.setText(grupoProducto.getProducto().getNombre());
        txtDescripcion.setText(grupoProducto.getProducto().getDescripcion());
        txtMarca.setText(grupoProducto.getProducto().getMarca().getNombre());
        txtCategoria.setText(grupoProducto.getProducto().getCategoria().getNombre());

        txtCantidad.setText(grupoProducto.getCantidad().toString());
        txtValor.setText(grupoProducto.getPrecio().toString());
    }
}