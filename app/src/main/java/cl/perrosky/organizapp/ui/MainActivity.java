package cl.perrosky.organizapp.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import cl.perrosky.organizapp.R;
import cl.perrosky.organizapp.bbdd.impl.LoginDataSource;
import cl.perrosky.organizapp.model.Usuario;

public class MainActivity extends AppCompatActivity {

    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario = (Usuario) getIntent().getSerializableExtra("LOGIN");
    }

    public void cambiarPantalla(View vista){
        switch ((String) ((Button) vista).getTag()){
            case "PRODUCTOS":
                startActivity(new Intent(getApplicationContext(), ListaProductoActivity.class));
                break;
            case "PRODUCTO":
                startActivity(new Intent(getApplicationContext(), EditarProductoActivity.class));
                break;
            case "CATEGORIAS":
                startActivity(new Intent(getApplicationContext(), ListaCategoriasActivity.class));
                break;
            case "MARCAS":
                startActivity(new Intent(getApplicationContext(), ListaMarcasActivity.class));
                break;
            case "INVENTARIO":
                startActivity(new Intent(getApplicationContext(), ListaStockProductoActivity.class));
                break;
            case "USUARIO":
                startActivity(new Intent(getApplicationContext(), ListaUsuariosActivity.class));
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        switch (usuario.getPerfil()){
            case "admin":
                (findViewById(R.id.PRODUCTOS)).setVisibility(View.VISIBLE);
                (findViewById(R.id.PRODUCTO)).setVisibility(View.VISIBLE);
                (findViewById(R.id.CATEGORIAS)).setVisibility(View.VISIBLE);
                (findViewById(R.id.MARCAS)).setVisibility(View.VISIBLE);
                (findViewById(R.id.INVENTARIO)).setVisibility(View.VISIBLE);
                (findViewById(R.id.USUARIO)).setVisibility(View.VISIBLE);
                (findViewById(R.id.VENTAS)).setVisibility(View.VISIBLE);
                break;
            case "ekono":
                (findViewById(R.id.PRODUCTOS)).setVisibility(View.VISIBLE);
                (findViewById(R.id.PRODUCTO)).setVisibility(View.VISIBLE);
                (findViewById(R.id.CATEGORIAS)).setVisibility(View.VISIBLE);
                (findViewById(R.id.MARCAS)).setVisibility(View.VISIBLE);
                (findViewById(R.id.INVENTARIO)).setVisibility(View.VISIBLE);
                (findViewById(R.id.USUARIO)).setVisibility(View.GONE);
                (findViewById(R.id.VENTAS)).setVisibility(View.GONE);
                break;
            case "vendedor":
                (findViewById(R.id.PRODUCTOS)).setVisibility(View.VISIBLE);
                (findViewById(R.id.PRODUCTO)).setVisibility(View.GONE);
                (findViewById(R.id.CATEGORIAS)).setVisibility(View.GONE);
                (findViewById(R.id.MARCAS)).setVisibility(View.GONE);
                (findViewById(R.id.INVENTARIO)).setVisibility(View.GONE);
                (findViewById(R.id.USUARIO)).setVisibility(View.GONE);
                (findViewById(R.id.VENTAS)).setVisibility(View.VISIBLE);
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
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.cerrar:
                (new LoginDataSource(this)).cerrarSession();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}