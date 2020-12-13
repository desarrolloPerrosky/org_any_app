package cl.perrosky.organizapp.model;

import java.io.Serializable;

public class Usuario implements Serializable {

    public static final long serialVersionUID = 1L;

    // MODELO SQL
    public static final String TABLA = "usuario";

    public static final String colID = "_id";
    public static final String colNOMBRES ="nombres";
    public static final String colAPELLIDOS = "apellidos";
    public static final String colPERFIL = "perfil";
    public static final String colCORREO = "correo";


}
