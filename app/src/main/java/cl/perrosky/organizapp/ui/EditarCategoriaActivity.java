package cl.perrosky.organizapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import cl.perrosky.organizapp.R;
import cl.perrosky.organizapp.bbdd.impl.CategoriaDataSource;
import cl.perrosky.organizapp.model.Categoria;

public class EditarCategoriaActivity extends AppCompatActivity {

    private Categoria categoria;
    private EditText txtNombre;
    private EditText txtDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_categoria);

        ActionBar barra = getSupportActionBar();
        barra.setDisplayHomeAsUpEnabled(true);

        txtNombre = (EditText) findViewById(R.id.nombre);
        txtDescripcion = (EditText) findViewById(R.id.descripcion);

        categoria = (Categoria) getIntent().getSerializableExtra(Categoria.TABLA);

        if(categoriaEnEdicion()){
            txtNombre.setText(categoria.getNombre());
            txtDescripcion.setText(categoria.getDescripcion());
            barra.setTitle(R.string.btn_edit_categoria);
        } else {
            categoria = new Categoria();
            barra.setTitle(R.string.btn_add_categoria);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_listado, menu);
        menu.findItem(R.id.guardar).setVisible(true).setTitle(R.string.btn_save_categoria);

        if(categoriaEnEdicion()){
            menu.findItem(R.id.eliminar).setVisible(true).setTitle(R.string.btn_remove_categoria);
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
                if (validarCategoria()){
                    (new CategoriaDataSource(this)).guardarCategoria(categoria);
                    Toast.makeText(getApplicationContext(), R.string.lbl_save_ok, Toast.LENGTH_SHORT).show();
                    cerrar();
                }else{
                    Toast.makeText(getApplicationContext(), R.string.lbl_save_fail, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.eliminar:
                new AlertDialog.Builder(this)
                        .setTitle(R.string.btn_remove_categoria)
                        .setMessage("Estas por eliminar a " + categoria.getNombre() +"\n ¿ Continuar con la eliminacion ?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                if(eliminarCategoria()){
                                    Toast.makeText(EditarCategoriaActivity.this, R.string.lbl_remove_ok, Toast.LENGTH_SHORT).show();
                                    cerrar();
                                } else {
                                    Toast.makeText(EditarCategoriaActivity.this, R.string.lbl_remove_fail, Toast.LENGTH_LONG).show();
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

    private boolean categoriaEnEdicion(){
        return (categoria!=null && categoria.getId() > 0);
    }

    private boolean eliminarCategoria(){
        int retorno = (new CategoriaDataSource(this)).eliminarCategoria(categoria);
        return (retorno == 1);
    }

    private boolean validarCategoria() {
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
            categoria.setNombre(txtNombre.getText().toString());
            categoria.setDescripcion(txtDescripcion.getText().toString());
        }
        return validado;
    }
}