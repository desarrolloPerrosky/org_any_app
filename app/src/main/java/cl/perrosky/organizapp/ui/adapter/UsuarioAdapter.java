package cl.perrosky.organizapp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import cl.perrosky.organizapp.R;
import cl.perrosky.organizapp.model.Usuario;

public class UsuarioAdapter extends ArrayAdapter {

    private Context contexto;
    private List<Usuario> listado;

    public UsuarioAdapter(@NonNull Context context, List<Usuario> datos) {
        super(context, R.layout.adapter_item_usuario, datos);
        this.contexto = context;
        this.listado = datos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(contexto);

        View item = inflater.inflate(R.layout.adapter_item_usuario, null);

        Usuario usuario = listado.get(position);

        TextView nombre = (TextView) item.findViewById(R.id.nombre);
        nombre.setText(usuario.getNombre());

        TextView apellido = (TextView) item.findViewById(R.id.apellido);
        apellido.setText(usuario.getApellido());

        TextView correo = (TextView) item.findViewById(R.id.correo);
        correo.setText(usuario.getCorreo());

        TextView perfil = (TextView) item.findViewById(R.id.perfil);
        perfil.setText(usuario.getPerfil());
        
        return item;
    }
}
