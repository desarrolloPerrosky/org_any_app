package cl.perrosky.organizapp.bbdd.impl;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cl.perrosky.organizapp.bbdd.DataSource;
import cl.perrosky.organizapp.bbdd.UsuarioAccesor;
import cl.perrosky.organizapp.model.Modelo;
import cl.perrosky.organizapp.model.Usuario;

public class UsuarioDataSource extends DataSource implements UsuarioAccesor {

    public UsuarioDataSource(Context context){
        super(context);
    }

    @Override
    public List<Usuario> getListaUsuario() {

        openDb();

        Log.i("CARGA", "Generando QUERY :: " + Modelo.USUARIO.getSelect());

        List<Usuario> lista = new ArrayList<Usuario>();
        Cursor cursor = database.rawQuery(Modelo.USUARIO.getSelect(), null);
        if(cursor.getCount() > 0){
            while (cursor.moveToNext()){
                lista.add(new Usuario(cursor));
            }
        }
        closeDb();

        return lista;
    }
}
