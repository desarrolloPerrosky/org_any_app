package cl.perrosky.organizapp.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import cl.perrosky.organizapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
    }

    public void cambiarPantalla(View vista){
        Button boton = (Button) vista;
        String opcion = (String) boton.getTag();
        switch (opcion){
            case "AGREGAR":
                startActivity(new Intent(getApplicationContext(), IngresarProductoActivity.class));
                break;
            case "CATEGORIA":
                startActivity(new Intent(getApplicationContext(), ListaCategoriasActivity.class));
                break;
            case "MARCA":
                startActivity(new Intent(getApplicationContext(), ListaMarcasActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed()  {
        finish();
        // super.onBackPressed();  // optional depending on your needs
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}