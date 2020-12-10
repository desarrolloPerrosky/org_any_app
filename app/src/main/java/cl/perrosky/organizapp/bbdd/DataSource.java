package cl.perrosky.organizapp.bbdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cl.perrosky.organizapp.model.Categoria;
import cl.perrosky.organizapp.model.Marca;
import cl.perrosky.organizapp.model.Modelo;

public class DataSource {

    private static final String TAG = "DataSource";

    private SQLiteOpenHelper dbHelper;
    private SQLiteDatabase database;

    public DataSource(Context context) {
        dbHelper = new DbOpenHelper(context);
    }

    private void openDb() {
        database = dbHelper.getWritableDatabase();
        Log.i(TAG, "Abriendo base de datos");
    }

    private void closeDb() {
        dbHelper.close();
        Log.i(TAG, "base de datos Cerrada");
    }


    public List<Categoria> getListaCategoria() {
        openDb();

        Log.i(TAG, "Generando QUERY :: " + Modelo.CATEGORIA.getSelect());

        List<Categoria> lista = new ArrayList<Categoria>();
        Cursor cursor = database.rawQuery(Modelo.CATEGORIA.getSelect(), null);
        if(cursor.getCount() > 0){
            while (cursor.moveToNext()){
                lista.add(new Categoria(cursor));
            }
        }

        closeDb();

        return lista;
    }

    public List<Marca> getListaMarca() {
        openDb();

        List<Marca> lista = new ArrayList<Marca>();
        Cursor cursor = database.rawQuery(Modelo.MARCA.getSelect(), null);
        if(cursor.getCount() > 0){
            while (cursor.moveToNext()){
                lista.add(new Marca(cursor));
            }
        }

        closeDb();

        return lista;
    }

    public void guardarMarca(Marca marca) {
        ContentValues param = new ContentValues();

        param.put(Marca.colNOMBRE, marca.getNombre());
        param.put(Marca.colDESCRIPCION, marca.getDescripcion());

        openDb();
        if(marca.getId().equals(new Integer(0))){
            database.insert(Marca.TABLA, null, param);
        } else {
            String[] args = new String[]{String.valueOf( marca.getId())};
            database.update(Marca.TABLA, param, Marca.colID + "=?", args);
        }
        closeDb();
    }

    public int eliminarMarca(Marca marca) {
        openDb();
        // TODO comprobar uso de marca en relaciones cruzadas
        int retorno = database.delete(Marca.TABLA, Marca.colID + "=?", new String[]{String.valueOf(marca.getId())});
        closeDb();
        return retorno;
    }

}
