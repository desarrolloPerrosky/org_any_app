package cl.perrosky.organizapp.bbdd.impl;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cl.perrosky.organizapp.bbdd.DataSource;
import cl.perrosky.organizapp.bbdd.MarcaAccesor;
import cl.perrosky.organizapp.model.Categoria;
import cl.perrosky.organizapp.model.Marca;
import cl.perrosky.organizapp.model.Modelo;

public class MarcaDataSource extends DataSource implements MarcaAccesor {

    private static final String TAG = "MarcaDataSource";

    private Activity mActivity;

    public MarcaDataSource(Context context){
        super(context);
        this.mActivity = (Activity) context;
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

    public Cursor buscarMarca(String constraint) throws SQLException {

        String queryString = "SELECT " +  Modelo.MARCA.getColumnas() + " FROM " + Marca.TABLA;

        if (constraint != null) {
            constraint = "%" + constraint.trim() + "%";
            queryString += " WHERE " + Marca.colNOMBRE + " LIKE ? OR " + Marca.colDESCRIPCION + " LIKE ?";
        }

        String params[] = { constraint, constraint };
        // Si no hay parametros, estos deben ser nulos
        if (constraint == null) {
            params = null;
        }

        try {
            openDb();
            Cursor cursor = database.rawQuery(queryString, params);
            if (cursor != null) {
                this.mActivity.startManagingCursor(cursor);
                cursor.moveToFirst();
                return cursor;
            }
            closeDb();
        }
        catch (SQLException e) {
            Log.e("AutoCompleteDbAdapter", e.toString());
            throw e;
        }
        return null;
    }
}
