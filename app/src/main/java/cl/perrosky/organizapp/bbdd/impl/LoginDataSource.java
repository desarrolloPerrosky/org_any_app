package cl.perrosky.organizapp.bbdd.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import cl.perrosky.organizapp.bbdd.DataSource;
import cl.perrosky.organizapp.bbdd.LoginAccesor;
import cl.perrosky.organizapp.model.Login;
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

        Usuario usuario = null;

        Cursor cursor = database.rawQuery(Modelo.LOGIN.getSelect(), null);
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

        param.put(Login.colID, usuario.getId());
        param.put(Login.colNOMBRE, usuario.getNombre());
        param.put(Login.colAPELLIDO, usuario.getApellido());
        param.put(Login.colCORREO, usuario.getCorreo());
        param.put(Login.colPERFIL, usuario.getPerfil());

        openDb();
        database.insert(Login.TABLA, null, param);
        closeDb();

        return usuario;
    }

    @Override
    public void cerrarSession() {
        openDb();
        database.delete(Login.TABLA, null, null);
        closeDb();
    }
}
