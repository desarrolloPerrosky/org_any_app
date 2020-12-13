package cl.perrosky.organizapp.bbdd.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cl.perrosky.organizapp.bbdd.DataSource;
import cl.perrosky.organizapp.bbdd.ProductoAccesor;
import cl.perrosky.organizapp.model.Categoria;
import cl.perrosky.organizapp.model.Modelo;
import cl.perrosky.organizapp.model.Producto;

public class ProductoDataSource extends DataSource implements ProductoAccesor {

    private static final String TAG = "ProductoDataSource";

    public ProductoDataSource(Context context){
        super(context);
    }

    @Override
    public Producto buscarProducto(String codigoBarra) {
        Producto producto = null;
        openDb();
        final String select = Modelo.PRODUCTO.getSelect()  + " WHERE " + Producto.colBARRA + "=?";
        Log.i(TAG, "Generando QUERY :: " + select);

        Cursor cursor = database.rawQuery( select, new String[]{codigoBarra});
        if(cursor.getCount() > 0){
            while (cursor.moveToNext()){
                producto = new Producto(cursor);
                break;
            }
        }
        closeDb();
        return producto;
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
    public Producto guardarProducto(Producto producto) {
        ContentValues param = new ContentValues();

        param.put(Producto.colBARRA, producto.getCodigoDeBarras());
        param.put(Producto.colNOMBRE, producto.getNombre());
        param.put(Producto.colDESCRIPCION, producto.getDescripcion());
        param.put(Producto.colUNIDADES, producto.getUnidades());
        param.put(Producto.colID_MARCA, producto.getMarca().getId());
        param.put(Producto.colID_CATEGORIA, producto.getCategoria().getId());

        openDb();
        if(producto.getId().equals(0L)){
            Log.i(TAG, "GUARDANDO PRODUCTO ::" + producto.toString());
            Long id = database.insert(Producto.TABLA, null, param);
            if(id > -1 ){
                producto.setId(id);
            } else {
                return null;
            }
        } else {
            Log.i(TAG, "ACTUALIZANDO PRODUCTO ::" + producto.toString());
            String[] args = new String[]{producto.getId().toString()};
            database.update(Producto.TABLA, param, Producto.colID + "=?", args);
        }
        closeDb();

        return producto;
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
