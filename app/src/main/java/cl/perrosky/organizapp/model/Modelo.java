package cl.perrosky.organizapp.model;

import android.database.Cursor;
import android.util.Log;

import androidx.annotation.NonNull;

public enum Modelo {

    CATEGORIA(Categoria.TABLA, Categoria.CUERPO, Categoria.SELECT, Categoria.COLS, false),
    USUARIO(Usuario.TABLA, Usuario.CUERPO, Usuario.SELECT, Usuario.COLS, false),
    MARCA(Marca.TABLA, Marca.CUERPO, Marca.SELECT, Marca.COLS, false),
    PRODUCTO(Producto.TABLA, Producto.CUERPO, Producto.SELECT, Producto.COLS, true);

    public static final String COLS = "%COLS%";

    public static final String SELECT  = "SELECT ";
    public static final String SELECT_FROM  = SELECT+COLS+" FROM ";
    public static final String INSERT_INTO  = "INSERT OR REPLACE INTO ";

    public static final String _PK  = " INTEGER PRIMARY KEY autoincrement,";
    public static final String TEXTO  = " TEXT";
    public static final String _TEXTO  = TEXTO + ",";
    public static final String TEXTO_UNICO  = " TEXT UNIQUE";
    public static final String _TEXTO_UNICO  = TEXTO_UNICO + ",";
    public static final String NUMBER  = " INTEGER";
    public static final String _NUMBER  = NUMBER + ",";

    private String tabla;
    private String create;
    private String select;
    private String insert;
    private String[] cols;
    private int largo;
    private String columnas;

    Modelo (String tabla, String cuerpo, String select, String[] cols, boolean redundancia){

        StringBuilder tmp = new StringBuilder();
        StringBuilder tmpToInsert = new StringBuilder();
        for (int z = 0; z < cols.length; z++){
            String coma = ",";
            String sp = " ";
            tmp.append(sp);
            tmpToInsert.append(sp);
            if(z > 0){
                tmp.append(coma);
                tmpToInsert.append(coma);
            }
            tmp.append(sp);
            tmpToInsert.append(sp);
            if(redundancia){
                tmp.append(tabla + ".");
            }
            tmp.append(cols[z]);
            tmpToInsert.append(cols[z]);
            if(redundancia){
                tmp.append(" AS " + cols[z]);
            }
        }
        this.columnas = tmp.toString();

        Log.i("CONSTRUCCION ENUM "+ tabla, "COLUMNAS SON :: " + columnas);

        this.insert = INSERT_INTO + tabla + "(" + (tmpToInsert.toString()) + ") VALUES ( " + COLS + " );";

        this.tabla = tabla;
        this.select = select.replace(COLS, columnas);
        this.cols = cols;

        this.create = CREATE(tabla, cuerpo);

        this.largo = cols.length;
    }

    public String getTabla() {
        return tabla;
    }

    public String getCreate(){
        Log.i(tabla, create);
        return create;
    }

    public String getSelect() {
        return select;
    }

    public String[] getCols() {
        return cols;
    }

    public String getColumnas() {
        return columnas;
    }

    public String get(int index) {
        return cols[index];
    }

    public int getLargo() {
        return largo;
    }


    public String getInsert(String cuerpo){
        return insert.replace(COLS, cuerpo);
    }






    // APOYO
    private static String CREATE(String tabla, String cuerpo){
        return "CREATE TABLE " + tabla + " ( " + cuerpo + " );";
    }

    public static String FK(String tabla, String clave, String foranea ){
        return "FOREIGN KEY (" + clave + ") REFERENCES " + tabla +"(" + foranea + ")";
    }

    public static String _FK(String tabla, String clave, String foranea ){
        return FK(tabla, clave, foranea) + ",";
    }


    public static String getStr(@NonNull Cursor cursor, String col){
        return cursor.getString(cursor.getColumnIndex(col));
    }
    public static int getInt(@NonNull Cursor cursor, String col){
        return  cursor.getInt(cursor.getColumnIndex(col));
    }

}
