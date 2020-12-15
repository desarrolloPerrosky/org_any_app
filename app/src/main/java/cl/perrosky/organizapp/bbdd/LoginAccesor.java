package cl.perrosky.organizapp.bbdd;

import java.util.List;

import cl.perrosky.organizapp.model.Usuario;

public interface LoginAccesor {

    public Usuario loginActive();

    public Usuario iniciarSesion(Usuario usuario);

    public void cerrarSession();
}
