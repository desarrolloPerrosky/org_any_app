package cl.perrosky.organizapp.bbdd.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import cl.perrosky.organizapp.bbdd.DataSource;
import cl.perrosky.organizapp.bbdd.MarcaAccesor;
import cl.perrosky.organizapp.model.Marca;
import cl.perrosky.organizapp.model.Modelo;

public class MarcaDataSource extends DataSource implements MarcaAccesor {

    private static final String TAG = "MarcaDataSource";

    public MarcaDataSource(Context context){
        super(context);
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
