package cl.perrosky.organizapp.bbdd.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cl.perrosky.organizapp.bbdd.DataSource;
import cl.perrosky.organizapp.bbdd.ProductoAccesor;
import cl.perrosky.organizapp.model.Modelo;
import cl.perrosky.organizapp.model.Producto;

public class ProductoDataSource extends DataSource implements ProductoAccesor {

    private static final String TAG = "ProductoDataSource";

    public ProductoDataSource(Context context){
        super(context);
    }

    @Override
    public List<Producto> getListaProducto() {
        openDb();

        Log.i(TAG, "Generando QUERY :: " + Modelo.PRODUCTO.getSelect());

        List<Producto> lista = new ArrayList<Producto>();
        Cursor cursor = database.rawQuery(Modelo.PRODUCTO.getSelect(), null);
        if(cursor.getCount() > 0){
            while (cursor.moveToNext()){
                lista.add(new Producto(cursor));
            }
        }
        closeDb();

        return lista;
    }

    @Override
    public void guardarProducto(Producto producto) {
        ContentValues param = new ContentValues();

        param.put(Producto.colNOMBRE, producto.getNombre());
        param.put(Producto.colDESCRIPCION, producto.getDescripcion());

        openDb();
        if(producto.getId().equals(new Integer(0))){
            database.insert(Producto.TABLA, null, param);
        } else {
            String[] args = new String[]{String.valueOf(producto.getId())};
            database.update(Producto.TABLA, param, Producto.colID + "=?", args);
        }
        closeDb();
    }

    @Override
    public int eliminarProducto(Producto producto) {
        openDb();
        // TODO comprobar uso de marca en relaciones cruzadas
        int retorno = database.delete(Producto.TABLA, Producto.colID + "=?", new String[]{String.valueOf(producto.getId())});
        closeDb();
        return retorno;
    }
}
