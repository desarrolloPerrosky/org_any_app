package cl.perrosky.organizapp.bbdd;

import android.database.Cursor;
import android.database.SQLException;

import java.util.List;

import cl.perrosky.organizapp.model.Categoria;

public interface CategoriaAccesor {

    public List<Categoria> getListaCategoria();

    public void guardarCategoria(Categoria categoria);

    public int eliminarCategoria(Categoria categoria);

    public Cursor buscarCategoria(String constraint) throws SQLException;
}
