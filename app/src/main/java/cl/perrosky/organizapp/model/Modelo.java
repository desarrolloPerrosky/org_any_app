package cl.perrosky.organizapp.model;

import android.database.Cursor;
import android.util.Log;

import androidx.annotation.NonNull;

public enum Modelo {

    CATEGORIA(Categoria.TABLA, Categoria.CUERPO, Categoria.SELECT, Categoria.COLS),
    MARCA(Marca.TABLA, Marca.CUERPO, Marca.SELECT, Marca.COLS);

    public static final String COLS = "%COLS%";

    public static final String SELECT  = "SELECT ";
    public static final String SELECT_FROM  = SELECT+COLS+" FROM ";
    public static final String INSERT_INTO  = "INSERT OR REPLACE INTO ";


    public static final String _PK  = " INTEGER PRIMARY KEY autoincrement,";
    public static final String TEXTO  = " TEXT";
    public static final String _TEXTO  = TEXTO + ",";
    public static final String NUMBER  = " INTEGER";
    public static final String _NUMBER  = NUMBER + ",";

    private String tabla;
    private String create;
    private String select;
    private String insert;
    private String[] cols;
    private int largo;
    private String columnas;

    Modelo (String tabla, String cuerpo, String select, String[] cols){

        StringBuilder tmp = new StringBuilder();
        for (int z = 0; z < cols.length; z++){
            String coma = ",";
            String sp = " ";
            tmp.append(sp);
            if(z > 0){
                tmp.append(coma);
            }
            tmp.append(sp).append(cols[z]);
        }
        this.columnas = tmp.toString();

        Log.i("CONSTRUCCION ENUM "+ tabla, "COLUMNAS SON :: " + columnas);

        this.insert = INSERT_INTO + tabla + "(" + this.columnas + ") VALUES ( " + COLS + " );";

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


    public static String getStr(@NonNull Cursor cursor, String col){
        return cursor.getString(cursor.getColumnIndex(col));
    }
    public static int getInt(@NonNull Cursor cursor, String col){
        return  cursor.getInt(cursor.getColumnIndex(col));
    }

}
