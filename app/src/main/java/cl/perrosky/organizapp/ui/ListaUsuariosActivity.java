package cl.perrosky.organizapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import cl.perrosky.organizapp.R;
import cl.perrosky.organizapp.bbdd.impl.CategoriaDataSource;
import cl.perrosky.organizapp.bbdd.impl.UsuarioDataSource;
import cl.perrosky.organizapp.model.Categoria;
import cl.perrosky.organizapp.model.Usuario;
import cl.perrosky.organizapp.ui.adapter.CategoriaAdapter;
import cl.perrosky.organizapp.ui.adapter.UsuarioAdapter;

public class ListaUsuariosActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    UsuarioDataSource dataSource;
    List<Usuario> listado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_generic);
        dataSource = new UsuarioDataSource(this);

        ActionBar barra = getSupportActionBar();
        barra.setDisplayHomeAsUpEnabled(true);
        barra.setTitle(R.string.btn_usuario);
    }

    @Override
    protected void onResume() {
        super.onResume();

        listado = dataSource.getListaUsuario();

        UsuarioAdapter adapter = new UsuarioAdapter(this, listado);

        ListView listView = (ListView) findViewById(R.id.listado);
        listView.setAdapter(adapter); // asignamos los datos
        listView.setOnItemClickListener(this); // // asignamos el escucha de eventos
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_listado, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
            case android.R.id.home:
                onBackPressed();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        /*Categoria categoria = listado.get(position);

        Intent intent = new Intent(getApplicationContext(), EditarCategoriaActivity.class);
        intent.putExtra(Categoria.TABLA, categoria);
        startActivity(intent);*/
    }
}