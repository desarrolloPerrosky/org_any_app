package cl.perrosky.organizapp.ui;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import cl.perrosky.organizapp.R;
import cl.perrosky.organizapp.adapter.EscanearActivity;
import cl.perrosky.organizapp.bbdd.impl.CategoriaDataSource;
import cl.perrosky.organizapp.bbdd.impl.MarcaDataSource;
import cl.perrosky.organizapp.bbdd.impl.ProductoDataSource;
import cl.perrosky.organizapp.model.Categoria;
import cl.perrosky.organizapp.model.Marca;
import cl.perrosky.organizapp.model.Modelo;
import cl.perrosky.organizapp.model.Producto;

public class EditarProductoActivity extends AppCompatActivity {

    final static int[] toCategoria = new int[] { android.R.id.text1 };
    final static String[] fromCategoria = new String[] { Categoria.colNOMBRE };

    final static int[] toMarca = new int[] { android.R.id.text1 };
    final static String[] fromMarca = new String[] {Marca.colNOMBRE };



    private static final int CODIGO_INTENT = 2;


    private EditText txtCodigoBarra;
    private EditText txtNombre;
    private EditText txtDescripcion;
    private EditText txtCantidad;

    private AutoCompleteTextView autoCompleteCategoria;
    private AutoCompleteTextView autoCompleteMarca;

    private Producto producto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_producto);

        ActionBar barra = getSupportActionBar();
        barra.setDisplayHomeAsUpEnabled(true);

        autoCompleteCategoria = (AutoCompleteTextView) findViewById(R.id.categoria);

        CategoriaTextAdapter adapterCategoria = new CategoriaTextAdapter(new CategoriaDataSource(this));
        autoCompleteCategoria.setAdapter(adapterCategoria);
        autoCompleteCategoria.setOnItemClickListener(adapterCategoria);


        autoCompleteMarca = (AutoCompleteTextView) findViewById(R.id.marca);

        MarcaTextAdapter adapterMarca = new MarcaTextAdapter(new MarcaDataSource(this));
        autoCompleteMarca.setAdapter(adapterMarca);
        autoCompleteMarca.setOnItemClickListener(adapterMarca);


        txtCodigoBarra = (EditText) findViewById(R.id.codigo);
        txtNombre = (EditText) findViewById(R.id.nombre);
        txtDescripcion = (EditText) findViewById(R.id.descripcion);
        txtCantidad = (EditText) findViewById(R.id.cantidad);


        producto = (Producto) getIntent().getSerializableExtra(Producto.TABLA);
        if(productoEnEdicion()) {
            barra.setTitle(R.string.btn_edit_producto);
        } else {
            producto = new Producto();
            barra.setTitle(R.string.btn_add_producto);
        }

        txtCodigoBarra.setText(producto.getCodigoDeBarras());
        txtNombre.setText(producto.getNombre());
        txtDescripcion.setText(producto.getDescripcion());
        txtCantidad.setText(String.valueOf(producto.getUnidades()));

        autoCompleteCategoria.setText(producto.getCategoria().getNombre());
        autoCompleteMarca.setText(producto.getMarca().getNombre());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_listado, menu);
        menu.findItem(R.id.guardar).setVisible(true).setTitle(R.string.btn_save_producto);

        if(productoEnEdicion()){
            menu.findItem(R.id.eliminar).setVisible(true).setTitle(R.string.btn_remove_producto);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.guardar:
                if (validarProducto()){
                    (new ProductoDataSource(this)).guardarProducto(producto);
                    Toast.makeText(getApplicationContext(), R.string.lbl_save_ok, Toast.LENGTH_SHORT).show();
                    cerrar();
                }else{
                    Toast.makeText(getApplicationContext(), R.string.lbl_save_fail, Toast.LENGTH_SHORT).show();
                }
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
                    String codigo = data.getStringExtra(EscanearActivity.RETORNO);
                    txtCodigoBarra.setText(codigo);
                }
            }
        }
    }

    public void escanear(View vista) {
        Intent i = new Intent(EditarProductoActivity.this, EscanearActivity.class);
        startActivityForResult(i, CODIGO_INTENT);
    }

    private boolean productoEnEdicion(){
        return (producto!=null && producto.getId() > 0);
    }

    private boolean validarProducto() {
        boolean validado = false;
/*
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
 */
        return validado;
    }

    private void cerrar(){
        finish();
    }

    class CategoriaTextAdapter extends SimpleCursorAdapter implements android.widget.AdapterView.OnItemClickListener {

        private CategoriaDataSource mDbHelper;

        public CategoriaTextAdapter(CategoriaDataSource dbHelper) {
            super(EditarProductoActivity.this, android.R.layout.simple_dropdown_item_1line, null, fromCategoria, toCategoria);
            mDbHelper = dbHelper;
        }

        @Override
        public Cursor runQueryOnBackgroundThread(CharSequence constraint) {
            if (getFilterQueryProvider() != null) {
                return getFilterQueryProvider().runQuery(constraint);
            }
            Cursor cursor = mDbHelper.buscarCategoria( (constraint != null ? constraint.toString() : null));
            producto.getCategoria().setId(0);
            return cursor;
        }

        @Override
        public String convertToString(Cursor cursor) {
            final int columnIndex = cursor.getColumnIndexOrThrow(Categoria.colNOMBRE);
            final String str = cursor.getString(columnIndex);
            return str;
        }

        @Override
        public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
            Cursor cursor = (Cursor) listView.getItemAtPosition(position);

            int idCategoria = Modelo.getInt(cursor, Categoria.colID);
            producto.getCategoria().setId(idCategoria);
        }
    }

    class MarcaTextAdapter extends SimpleCursorAdapter implements android.widget.AdapterView.OnItemClickListener {

        private MarcaDataSource mDbHelper;

        public MarcaTextAdapter(MarcaDataSource dbHelper) {
            super(EditarProductoActivity.this, android.R.layout.simple_dropdown_item_1line, null, fromMarca, toMarca);
            mDbHelper = dbHelper;
        }

        @Override
        public Cursor runQueryOnBackgroundThread(CharSequence constraint) {
            if (getFilterQueryProvider() != null) {
                return getFilterQueryProvider().runQuery(constraint);
            }
            Cursor cursor = mDbHelper.buscarMarca( (constraint != null ? constraint.toString() : null));
            producto.getMarca().setId(0);
            producto.getMarca().setNombre("");
            producto.getMarca().setDescripcion("");
            return cursor;
        }

        @Override
        public String convertToString(Cursor cursor) {
            final int columnIndex = cursor.getColumnIndexOrThrow(Marca.colNOMBRE);
            final String str = cursor.getString(columnIndex);
            return str;
        }

        @Override
        public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
            Cursor cursor = (Cursor) listView.getItemAtPosition(position);

            producto.getMarca().setId(Modelo.getInt(cursor, Marca.colID));
            producto.getMarca().setNombre(Modelo.getStr(cursor, Marca.colNOMBRE));
            producto.getMarca().setDescripcion(Modelo.getStr(cursor, Marca.colDESCRIPCION));
        }
    }
}