package cl.perrosky.organizapp.bbdd;

import java.util.List;

import cl.perrosky.organizapp.model.Marca;
import cl.perrosky.organizapp.model.Producto;

public interface ProductoAccesor {

    public List<Producto> getListaProducto();

    public void guardarProducto(Producto producto);

    public int eliminarProducto(Producto producto);
}
