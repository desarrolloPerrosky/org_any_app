package cl.perrosky.organizapp.bbdd.impl;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import java.util.ArrayList;
import java.util.List;

import cl.perrosky.organizapp.bbdd.CategoriaAccesor;
import cl.perrosky.organizapp.bbdd.DataSource;
import cl.perrosky.organizapp.model.Categoria;
import cl.perrosky.organizapp.model.Marca;
import cl.perrosky.organizapp.model.Modelo;

public class CategoriaDataSource extends DataSource implements CategoriaAccesor {

    private static final String TAG = "CategoriaDataSource";
    private Activity mActivity;

    public CategoriaDataSource(Context context){
        super(context);
        this.mActivity = (Activity) context;
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

    public void guardarCategoria(Categoria categoria) {
        ContentValues param = new ContentValues();

        param.put(Marca.colNOMBRE, categoria.getNombre());
        param.put(Marca.colDESCRIPCION, categoria.getDescripcion());

        openDb();
        if(categoria.getId().equals(new Integer(0))){
            database.insert(Categoria.TABLA, null, param);
        } else {
            String[] args = new String[]{String.valueOf(categoria.getId())};
            database.update(Categoria.TABLA, param, Categoria.colID + "=?", args);
        }
        closeDb();
    }

    public int eliminarCategoria(Categoria categoria) {
        openDb();
        // TODO comprobar uso de marca en relaciones cruzadas
        int retorno = database.delete(Categoria.TABLA, Categoria.colID + "=?", new String[]{String.valueOf(categoria.getId())});
        closeDb();
        return retorno;
    }

    public Cursor buscarCategoria(String constraint) throws SQLException {

        String queryString = "SELECT " +  Modelo.CATEGORIA.getColumnas() + " FROM " + Categoria.TABLA;

        if (constraint != null) {
            constraint = "%" + constraint.trim() + "%";
            queryString += " WHERE " + Categoria.colNOMBRE + " LIKE ? OR " + Categoria.colDESCRIPCION + " LIKE ?";
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
