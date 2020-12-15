package cl.perrosky.organizapp.bbdd.impl;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import cl.perrosky.organizapp.bbdd.DataSource;
import cl.perrosky.organizapp.bbdd.LoginAccesor;
import cl.perrosky.organizapp.model.Marca;
import cl.perrosky.organizapp.model.Modelo;
import cl.perrosky.organizapp.model.Usuario;

public class LoginDataSource extends DataSource implements LoginAccesor {

    private static final String TAG = "LoginDataSource";

    public LoginDataSource(Context context){
        super(context);
    }

    @Override
    public Usuario loginActive() {
        openDb();

        Usuario usuario = new Usuario();

        Cursor cursor = database.rawQuery(Modelo.USUARIO.getSelect(), null);
        if(cursor.getCount() > 0){
            while (cursor.moveToNext()){
                usuario = new Usuario(cursor);
            }
        }
        closeDb();

        return usuario;
    }

    @Override
    public Usuario iniciarSesion(Usuario usuario) {
        ContentValues param = new ContentValues();

        param.put(Usuario.colID, usuario.getId());
        param.put(Usuario.colNOMBRE, usuario.getNombre());
        param.put(Usuario.colAPELLIDO, usuario.getApellido());
        param.put(Usuario.colCORREO, usuario.getCorreo());
        param.put(Usuario.colPERFIL, usuario.getPerfil());

        openDb();
        database.insert(Usuario.TABLA, null, param);
        closeDb();

        return usuario;
    }

    @Override
    public void cerrarSession() {
        openDb();
        database.delete(Usuario.TABLA, null, null);
        closeDb();
    }
}
