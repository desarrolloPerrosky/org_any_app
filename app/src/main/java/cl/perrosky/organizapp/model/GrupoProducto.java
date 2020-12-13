package cl.perrosky.organizapp.model;

import java.io.Serializable;

public class GrupoProducto implements Serializable {

    public static final long serialVersionUID = 1L;

    // Atributos
    private Integer id;
    private Long cantidad;
    private Long precio;
    private Producto producto;

    public GrupoProducto(){
        this.id = 0;
        this.cantidad = 0L;
        this.precio = 0L;
        this.producto = new Producto();
    }
    public GrupoProducto(Producto producto){
        this();
        this.producto = producto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public Long getPrecio() {
        return precio;
    }

    public void setPrecio(Long precio) {
        this.precio = precio;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
