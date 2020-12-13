package cl.perrosky.organizapp.bbdd;

import java.util.List;

import cl.perrosky.organizapp.model.Marca;
import cl.perrosky.organizapp.model.Producto;

public interface ProductoAccesor {

    public Producto buscarProducto(String codigoBarra);

    public List<Producto> getListaProducto();

    public void guardarProducto(Producto producto);

    public int eliminarProducto(Producto producto);
}
