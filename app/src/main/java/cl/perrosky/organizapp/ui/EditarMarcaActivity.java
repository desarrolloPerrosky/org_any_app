package cl.perrosky.organizapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import cl.perrosky.organizapp.R;
import cl.perrosky.organizapp.bbdd.DataSource;
import cl.perrosky.organizapp.model.Marca;

public class EditarMarcaActivity extends AppCompatActivity {

    private Marca marca;

    private EditText txtNombre;
    private EditText txtDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_marca);

        ActionBar barra = getSupportActionBar();
        barra.setDisplayHomeAsUpEnabled(true);

        txtNombre = (EditText) findViewById(R.id.nombre);
        txtDescripcion = (EditText) findViewById(R.id.descripcion);

        marca = (Marca) getIntent().getSerializableExtra(Marca.TABLA);

        if(marca!=null){
            txtNombre.setText(marca.getNombre());
            txtDescripcion.setText(marca.getDescripcion());
            barra.setTitle(R.string.btn_add_marca);
        } else {
            marca = new Marca();
            barra.setTitle(R.string.btn_edit_marca);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.i("onCreateOptionsMenu", "AHORA CREAMOS EL MENU con " + marca.toString() );
        getMenuInflater().inflate(R.menu.menu_listado, menu);
        menu.findItem(R.id.guardar)
                .setVisible(true)
                .setTitle(R.string.btn_save_marca);

        if(marca!=null && marca.getId() > 0){
            menu.findItem(R.id.eliminar)
                    .setVisible(true)
                    .setTitle(R.string.btn_remove_marca);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
            case android.R.id.home:
                cerrar();
                break;
            case R.id.guardar:
                marca = validarMarca();
                if (marca!=null){
                    (new DataSource(this)).guardarMarca(marca);
                    Toast.makeText(getApplicationContext(), R.string.lbl_save_ok, Toast.LENGTH_SHORT).show();
                    cerrar();
                }else{
                    Toast.makeText(getApplicationContext(), R.string.lbl_save_fail, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.eliminar:
                new AlertDialog.Builder(this)
                        .setTitle(R.string.btn_remove_marca)
                        .setMessage("Estas por eliminar a " + marca.getNombre() +"\n ¿ Continuar con la eliminacion ?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                if(eliminarMarca()){
                                    Toast.makeText(EditarMarcaActivity.this, R.string.lbl_remove_ok, Toast.LENGTH_SHORT).show();
                                    cerrar();
                                } else {
                                    Toast.makeText(EditarMarcaActivity.this, R.string.lbl_remove_fail, Toast.LENGTH_LONG).show();
                                }
                            }})
                        .setNegativeButton(android.R.string.no, null).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void cerrar(){
        finish();
    }

    private boolean eliminarMarca(){
        int retorno = (new DataSource(this)).eliminarMarca(marca);
        return (retorno == 1);
    }

    private Marca validarMarca() {
        boolean validado = false;

        if(txtNombre.getText().toString().isEmpty()){
            txtNombre.setBackgroundColor(getResources().getColor(R.color.warning));
        } else {
            txtNombre.setBackgroundColor(getResources().getColor(R.color.color3));
            validado = true;
        }

        if(txtDescripcion.getText().toString().isEmpty()){
            txtDescripcion.setBackgroundColor(getResources().getColor(R.color.warning));
            validado = false;
        } else {
            txtDescripcion.setBackgroundColor(getResources().getColor(R.color.color3));
            validado &= true;
        }

        if(validado){
            marca.setNombre(txtNombre.getText().toString());
            marca.setDescripcion(txtDescripcion.getText().toString());

            return marca;
        }
        return null;
    }
}