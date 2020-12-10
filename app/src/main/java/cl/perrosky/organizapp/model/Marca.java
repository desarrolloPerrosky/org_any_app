package cl.perrosky.organizapp.model;

import android.database.Cursor;

import java.io.Serializable;

import static cl.perrosky.organizapp.model.Modelo.SELECT_FROM;
import static cl.perrosky.organizapp.model.Modelo.TEXTO;
import static cl.perrosky.organizapp.model.Modelo._PK;
import static cl.perrosky.organizapp.model.Modelo._TEXTO;

public class Marca implements Serializable {

    public static final long serialVersionUID = 1L;

    // MODELO SQL
    public static final String TABLA = "marca";

    public static final String colID = "id";
    public static final String colNOMBRE ="nombre";
    public static final String colDESCRIPCION = "descripcion";

    protected static final String[] COLS = new String[]{ colID, colNOMBRE, colDESCRIPCION };

    protected static final String CUERPO =  colID + _PK +
                                            colNOMBRE + _TEXTO +
                                            colDESCRIPCION + TEXTO;

    protected static final String SELECT = SELECT_FROM + TABLA + " ORDER BY " + colNOMBRE;


    // Atributos
    private Integer id;
    private String nombre;
    private String descripcion;

    public Marca(){
        this.id = 0;
        this.nombre = "";
        this.descripcion = "";
    }

    public Marca(Cursor cursor){
        this();
        this.id = Modelo.getInt(cursor, colID);
        this.nombre = Modelo.getStr(cursor, colNOMBRE);
        this.descripcion = Modelo.getStr(cursor, colDESCRIPCION);
    }

    // GETTER AND SETTER
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    // GETTER AND SETTER


    @Override
    public String toString() {
        return "Marca{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
