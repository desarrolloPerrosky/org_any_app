package cl.perrosky.organizapp.ui;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import cl.perrosky.organizapp.R;
import cl.perrosky.organizapp.adapter.EscanearActivity;
import cl.perrosky.organizapp.bbdd.impl.CategoriaDataSource;
import cl.perrosky.organizapp.model.Categoria;
import cl.perrosky.organizapp.model.Modelo;

public class EditarProductoActivity extends AppCompatActivity {

    final static int[] to = new int[] { android.R.id.text1 };
    final static String[] from = new String[] { Categoria.colNOMBRE };



    private AutoCompleteTextView text;
    private MultiAutoCompleteTextView text1;
    private String[] languages = {"Android ","java","IOS","SQL","JDBC","Web services"};

    private TextView mStateCapitalView;
    private AutoCompleteTextView mStateNameView;

    private static final int CODIGO_INTENT = 2;

    private TextView txtIdCategoria;

    private TextView tvCodigoBarra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_producto);


        // INICIO TESTER
        text=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);
        text1=(MultiAutoCompleteTextView)findViewById(R.id.multiAutoCompleteTextView1);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,languages);

        text.setAdapter(adapter);
        text.setThreshold(1);

        text1.setAdapter(adapter);
        text1.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        // -----------------------
        mStateCapitalView = (TextView) findViewById(R.id.state_capital);
        txtIdCategoria = (TextView) findViewById(R.id.state_capital);
        mStateNameView = (AutoCompleteTextView) findViewById(R.id.state_name);

        // Create an ItemAutoTextAdapter for the State Name field,
        // and set it as the OnItemClickListener for that field.
        ItemAutoTextAdapter adapter2 = this.new ItemAutoTextAdapter(new CategoriaDataSource(this));
        mStateNameView.setAdapter(adapter2);
        mStateNameView.setOnItemClickListener(adapter2);
        // FIN TESTER

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
        Intent i = new Intent(EditarProductoActivity.this, EscanearActivity.class);
        startActivityForResult(i, CODIGO_INTENT);
    }



    /**
     * Specializes CursorAdapter to supply choices to a AutoCompleteTextView.
     * Also implements OnItemClickListener to be notified when a choice is made,
     * and uses the choice to update other fields on the Activity form.
     */
    class ItemAutoTextAdapter extends SimpleCursorAdapter implements android.widget.AdapterView.OnItemClickListener {

        private CategoriaDataSource mDbHelper;

        /**
         * Constructor. Note that no cursor is needed when we create the
         * adapter. Instead, cursors are created on demand when completions are
         * needed for the field. (see
         * {@link ItemAutoTextAdapter#runQueryOnBackgroundThread(CharSequence)}.)
         *
         * @param dbHelper
         *            The AutoCompleteDbAdapter in use by the outer class
         *            object.
         */
        public ItemAutoTextAdapter(CategoriaDataSource dbHelper) {
            // Call the SimpleCursorAdapter constructor with a null Cursor.
            super(EditarProductoActivity.this, android.R.layout.simple_dropdown_item_1line, null, from, to);
            mDbHelper = dbHelper;
        }

        /**
         * Invoked by the AutoCompleteTextView field to get completions for the
         * current input.
         *
         * NOTE: If this method either throws an exception or returns null, the
         * Filter class that invokes it will log an error with the traceback,
         * but otherwise ignore the problem. No choice list will be displayed.
         * Watch those error logs!
         *
         * @param constraint
         *            The input entered thus far. The resulting query will
         *            search for states whose name begins with this string.
         * @return A Cursor that is positioned to the first row (if one exists)
         *         and managed by the activity.
         */
        @Override
        public Cursor runQueryOnBackgroundThread(CharSequence constraint) {
            if (getFilterQueryProvider() != null) {
                return getFilterQueryProvider().runQuery(constraint);
            }
            Cursor cursor = mDbHelper.buscarCategoria( (constraint != null ? constraint.toString() : null));
            txtIdCategoria.setText("NADA");
            return cursor;
        }

        /**
         * Called by the AutoCompleteTextView field to get the text that will be
         * entered in the field after a choice has been made.
         *
         * @param Cursor
         *            The cursor, positioned to a particular row in the list.
         * @return A String representing the row's text value. (Note that this
         *         specializes the base class return value for this method,
         *         which is {@link CharSequence}.)
         */
        @Override
        public String convertToString(Cursor cursor) {
            final int columnIndex = cursor.getColumnIndexOrThrow(Categoria.colNOMBRE);
            final String str = cursor.getString(columnIndex);
            return str;
        }

        /**
         * Called by the AutoCompleteTextView field when a choice has been made
         * by the user.
         *
         * @param listView
         *            The ListView containing the choices that were displayed to
         *            the user.
         * @param view
         *            The field representing the selected choice
         * @param position
         *            The position of the choice within the list (0-based)
         * @param id
         *            The id of the row that was chosen (as provided by the _id
         *            column in the cursor.)
         */
        @Override
        public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
            // Get the cursor, positioned to the corresponding row in the result set
            Cursor cursor = (Cursor) listView.getItemAtPosition(position);

            // Get the state's capital from this row in the database.
            String capital = Modelo.getStr(cursor, Categoria.colDESCRIPCION);
            int idCategoria = Modelo.getInt(cursor, Categoria.colID);
            //cursor.getString(cursor.getColumnIndexOrThrow("capital"));

            // Update the parent class's TextView
            mStateCapitalView.setText(capital);
            txtIdCategoria.setText(String.valueOf(idCategoria));
        }
    }
}