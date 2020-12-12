package cl.perrosky.organizapp.ui;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import cl.perrosky.organizapp.R;
import cl.perrosky.organizapp.adapter.EscanearActivity;

public class IngresarProductoActivity extends AppCompatActivity {

    private static final int CODIGO_PERMISOS_CAMARA = 1;
    private static final int CODIGO_INTENT = 2;

    private TextView tvCodigoBarra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_producto);

        tvCodigoBarra = findViewById(R.id.codigo);

        ActionBar barra = getSupportActionBar();
        barra.setDisplayHomeAsUpEnabled(true);
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODIGO_INTENT) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    String codigo = data.getStringExtra("codigo");
                    tvCodigoBarra.setText(codigo);
                }
            }
        }
    }

    public void escanear(View vista) {
        Intent i = new Intent(IngresarProductoActivity.this, EscanearActivity.class);
        startActivityForResult(i, CODIGO_INTENT);
    }

}