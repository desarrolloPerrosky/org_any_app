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
import cl.perrosky.organizapp.bbdd.MarcaDataSource;
import cl.perrosky.organizapp.model.Marca;
import cl.perrosky.organizapp.ui.adapter.MarcaAdapter;

public class ListaMarcasActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    MarcaDataSource dataSource;
    List<Marca> listado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_generic);
        dataSource = new MarcaDataSource(this);

        ActionBar barra = getSupportActionBar();
        barra.setDisplayHomeAsUpEnabled(true);

        barra.setTitle(R.string.btn_marca);
    }

    @Override
    protected void onResume() {
        super.onResume();

        listado = dataSource.getListaMarca();

        MarcaAdapter adapter = new MarcaAdapter(this, listado);

        ListView listView = (ListView) findViewById(R.id.listado);
        listView.setAdapter(adapter); // asignamos los datos
        listView.setOnItemClickListener(this); // // asignamos el escucha de eventos
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_listado, menu);

        menu.findItem(R.id.agregar)
                .setVisible(true)
                .setTitle(R.string.btn_add_marca);
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
                startActivity(new Intent(getApplicationContext(), EditarMarcaActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Marca marca = listado.get(position);

        Intent intent = new Intent(getApplicationContext(), EditarMarcaActivity.class);
        intent.putExtra(Marca.TABLA, marca);
        startActivity(intent);
    }



}
