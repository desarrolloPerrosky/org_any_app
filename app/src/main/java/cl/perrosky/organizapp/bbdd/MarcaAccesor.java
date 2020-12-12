package cl.perrosky.organizapp.bbdd;

import java.util.List;

import cl.perrosky.organizapp.model.Marca;

public interface MarcaAccesor {

    public List<Marca> getListaMarca();

    public void guardarMarca(Marca marca);

    public int eliminarMarca(Marca marca);
}
