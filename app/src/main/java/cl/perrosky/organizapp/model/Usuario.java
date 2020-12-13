package cl.perrosky.organizapp.model;


import android.database.Cursor;
import android.util.Log;

import java.io.Serializable;

import static cl.perrosky.organizapp.model.Modelo.SELECT_FROM;
import static cl.perrosky.organizapp.model.Modelo.NUMBER;
import static cl.perrosky.organizapp.model.Modelo._PK;
import static cl.perrosky.organizapp.model.Modelo._TEXTO;
import static cl.perrosky.organizapp.model.Modelo._TEXTO_UNICO;




public class Usuario implements Serializable {

    public static final long serialVersionUID = 1L;

    // MODELO SQL
    public static final String TABLA = "usuario";

    public static final String colID = "_id";

    public static final String colNOMBRE ="nombre";
    public static final String colAPELLIDO = "apellido";
    public static final String colCORREO = "correo";
    public static final String colPERFIL = "perfil";



    protected static final String[] COLS = new String[]{ colID, colNOMBRE, colAPELLIDO, colCORREO, colPERFIL };

    protected static final String CUERPO =
                    colID + _PK +
                    colNOMBRE + _TEXTO +
                    colAPELLIDO + _TEXTO +
                    colCORREO + _TEXTO_UNICO +
                    colPERFIL + NUMBER ;

    protected static final String SELECT = SELECT_FROM + TABLA ;






    // Atributos
    private Integer id;
    private String nombre;
    private String apellido;
    private String correo;
    private String perfil;


    public Usuario(){
        this.id = 0;
        this.nombre = "";
        this.apellido="";
        this.correo="";
        this.perfil="";
    }

    public Usuario(Cursor cursor){
        this();
        this.id = Modelo.getInt(cursor, colID);
        this.nombre = Modelo.getStr(cursor, colNOMBRE);
        this.apellido = Modelo.getStr(cursor, colAPELLIDO);
        this.correo = Modelo.getStr(cursor, colCORREO);
        this.perfil = Modelo.getStr(cursor, colPERFIL);


        Log.i("usuario CREADO", this.toString());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }


}
