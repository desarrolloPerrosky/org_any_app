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

public abstract class DataSource {

    private static final String TAG = "DataSource";

    protected SQLiteOpenHelper dbHelper;
    protected SQLiteDatabase database;

    public DataSource(Context context) {
        dbHelper = new DbOpenHelper(context);
    }

    protected void openDb() {
        database = dbHelper.getWritableDatabase();
        Log.i(TAG, "Abriendo base de datos");
    }

    protected void closeDb() {
        dbHelper.close();
        Log.i(TAG, "base de datos Cerrada");
    }
}
