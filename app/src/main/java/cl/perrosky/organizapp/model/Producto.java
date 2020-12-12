package cl.perrosky.organizapp.model;

import android.database.Cursor;

import java.io.Serializable;

import static cl.perrosky.organizapp.model.Modelo._NUMBER;
import static cl.perrosky.organizapp.model.Modelo._PK;
import static cl.perrosky.organizapp.model.Modelo._TEXTO;
import static cl.perrosky.organizapp.model.Modelo._FK;
import static cl.perrosky.organizapp.model.Modelo.FK;
import static cl.perrosky.organizapp.model.Modelo._TEXTO_UNICO;

public class Producto implements Serializable {

    public static final long serialVersionUID = 1L;

    // MODELO SQL
    public static final String TABLA = "producto";

    public static final String colID = "_id";
    public static final String colNOMBRE ="nombre";
    public static final String colDESCRIPCION = "descripcion";
    public static final String colID_MARCA = "_id_marca";
    public static final String colID_CATEGORIA = "_id_categoria";
    public static final String colBARRA = "codigo_barra";
    public static final String colUNIDADES = "unidades";

    protected static final String[] COLS = new String[]{ colID, colNOMBRE, colDESCRIPCION, colBARRA, colUNIDADES, colID_MARCA, colID_CATEGORIA };

    protected static final String CUERPO =
                    colID + _PK +
                    colNOMBRE + _TEXTO +
                    colDESCRIPCION + _TEXTO +
                    colBARRA + _TEXTO_UNICO +
                    colUNIDADES + _NUMBER +
                    colID_MARCA + _NUMBER +
                    colID_CATEGORIA + _NUMBER +
                    _FK(Marca.TABLA, colID_MARCA, Marca.colID) +
                    FK(Categoria.TABLA, colID_CATEGORIA, Categoria.colID);

    protected static final String SELECT = "SELECT " + Modelo.COLS + " " +
            ", " + Marca.TABLA + "." + Marca.colNOMBRE + " AS nombre_marca" +
            ", " + Marca.TABLA + "." + Marca.colDESCRIPCION + " AS descripcion_marca" +
            ", " + Categoria.TABLA + "." + Categoria.colNOMBRE + " AS nombre_categoria" +
            ", " + Categoria.TABLA + "." + Categoria.colDESCRIPCION + " AS descripcion_categoria " +
            " FROM  "+ TABLA +
            " INNER JOIN " + Marca.TABLA + " ON " + Producto.TABLA + "." + Producto.colID_MARCA + " = " + Marca.TABLA + "." + Marca.colID + " " +
            " INNER JOIN " + Categoria.TABLA + " ON " + Producto.TABLA + "." + Producto.colID_CATEGORIA + " = " + Categoria.TABLA + "." + Categoria.colID + " "
            + " ORDER BY " + TABLA + "." + colNOMBRE;

    // Atributos
    private Integer id;
    private String nombre;
    private String descripcion;
    private String codigoDeBarras;
    private int unidades;
    private Marca marca;
    private Categoria categoria;

    public Producto(){
        this.id = 0;
        this.nombre = "";
        this.descripcion = "";
        this.codigoDeBarras = "";
        this.unidades = 1;
        this.marca = new Marca();
        this.categoria = new Categoria();
    }

    public Producto(Cursor cursor){
        this();
        this.id = Modelo.getInt(cursor, colID);
        this.nombre = Modelo.getStr(cursor, colNOMBRE);
        this.descripcion = Modelo.getStr(cursor, colDESCRIPCION);
        this.codigoDeBarras = Modelo.getStr(cursor, colBARRA);
        this.unidades = Modelo.getInt(cursor, colUNIDADES);
        this.marca = new Marca(
                Modelo.getInt(cursor, colID_MARCA),
                Modelo.getStr(cursor, "nombre_marca"),
                Modelo.getStr(cursor, "descripcion_marca")
        );
        this.categoria = new Categoria(
                Modelo.getInt(cursor, colID_CATEGORIA),
                Modelo.getStr(cursor, "nombre_categoria"),
                Modelo.getStr(cursor, "descripcion_categoria")
        );
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

    public String getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public void setCodigoDeBarras(String codigoDeBarras) {
        this.codigoDeBarras = codigoDeBarras;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    // GETTER AND SETTER
}

