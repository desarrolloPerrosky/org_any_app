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
import cl.perrosky.organizapp.bbdd.impl.CategoriaDataSource;
import cl.perrosky.organizapp.model.Categoria;
import cl.perrosky.organizapp.ui.adapter.CategoriaAdapter;

public class ListaCategoriasActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    CategoriaDataSource dataSource;
    List<Categoria> listado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_generic);
        dataSource = new CategoriaDataSource(this);

        ActionBar barra = getSupportActionBar();
        barra.setDisplayHomeAsUpEnabled(true);
        barra.setTitle(R.string.btn_categoria);
    }

    @Override
    protected void onResume() {
        super.onResume();

        listado = dataSource.getListaCategoria();

        CategoriaAdapter adapter = new CategoriaAdapter(this, listado);

        ListView listView = (ListView) findViewById(R.id.listado);
        listView.setAdapter(adapter); // asignamos los datos
        listView.setOnItemClickListener(this); // // asignamos el escucha de eventos
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_listado, menu);
        menu.findItem(R.id.agregar)
                .setVisible(true)
                .setTitle(R.string.btn_add_categoria);
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
                startActivity(new Intent(getApplicationContext(), EditarCategoriaActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Categoria categoria = listado.get(position);

        Intent intent = new Intent(getApplicationContext(), EditarCategoriaActivity.class);
        intent.putExtra(Categoria.TABLA, categoria);
        startActivity(intent);
    }
}