package cl.perrosky.organizapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import cl.perrosky.organizapp.R;
import cl.perrosky.organizapp.adapter.EscanearActivity;
import cl.perrosky.organizapp.bbdd.impl.ProductoDataSource;
import cl.perrosky.organizapp.model.Producto;
import cl.perrosky.organizapp.ui.adapter.ProductoAdapter;


public class ListaProductoActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final int CODIGO_INTENT = 2;

    private ProductoDataSource dataSource;
    private List<Producto> listado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_generic);

        dataSource = new ProductoDataSource(this);

        ActionBar barra = getSupportActionBar();
        barra.setDisplayHomeAsUpEnabled(true);

        barra.setTitle(R.string.btn_list_producto);
    }

    @Override
    protected void onResume() {
        super.onResume();

        listado = dataSource.getListaProducto();

        ProductoAdapter adapter = new ProductoAdapter(this, listado);

        ListView listView = (ListView) findViewById(R.id.listado);
        listView.setAdapter(adapter); // asignamos los datos
        listView.setOnItemClickListener(this); // // asignamos el escucha de eventos
    }

    public void escanear(View vista) {
        Intent i = new Intent(ListaProductoActivity.this, EscanearActivity.class);
        startActivityForResult(i, CODIGO_INTENT);
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

}