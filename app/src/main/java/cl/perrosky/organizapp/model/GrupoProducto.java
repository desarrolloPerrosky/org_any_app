package cl.perrosky.organizapp.model;

import java.io.Serializable;

public class GrupoProducto implements Serializable {

    public static final long serialVersionUID = 1L;

    // Atributos
    private Integer id;
    private Integer cantidad;
    private Integer precio;
    private Producto producto;

    public GrupoProducto(Producto producto){
        this.id = 0;
        this.cantidad = 0;
        this.precio = 0;
        this.producto = producto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
