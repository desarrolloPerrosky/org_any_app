package cl.perrosky.organizapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import cl.perrosky.organizapp.R;
import cl.perrosky.organizapp.bbdd.impl.ProductoDataSource;
import cl.perrosky.organizapp.model.GrupoProducto;

public class EditarStockProductoActivity extends AppCompatActivity {

    public static final String RETORNO = "producto";
    public static final int CODIGO_INTENT = 3;

    private ProductoDataSource dataSource;


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

        if(grupoProducto.getProducto().getId().equals(0L)){
            Intent i = new Intent(EditarStockProductoActivity.this, EditarProductoActivity.class);
            i.putExtra(EditarProductoActivity.RETORNO, grupoProducto.getProducto());
            startActivityForResult(i, EditarProductoActivity.CODIGO_INTENT);
        } else {
            ((TextView) findViewById(R.id.nombre)).setText(grupoProducto.getProducto().getNombre());
            ((TextView) findViewById(R.id.descripcion)).setText(grupoProducto.getProducto().getDescripcion());

            txtCantidad.setText(grupoProducto.getCantidad().toString());
            txtValor.setText(grupoProducto.getPrecio().toString());
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

    public void agregarProductos(View view){

        grupoProducto.setCantidad(Long.valueOf(txtCantidad.getText().toString()));
        grupoProducto.setPrecio(Long.valueOf(txtValor.getText().toString()));

        Intent intentRegreso = new Intent();
        intentRegreso.putExtra(RETORNO, grupoProducto);
        setResult(Activity.RESULT_OK, intentRegreso);

        // Cerrar la actividad. Ahora mira onActivityResult de MainActivity
        finish();
    }
}