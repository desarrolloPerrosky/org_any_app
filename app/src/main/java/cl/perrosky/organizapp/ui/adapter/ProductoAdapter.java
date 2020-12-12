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
import cl.perrosky.organizapp.model.Producto;

public class ProductoAdapter extends ArrayAdapter {

    private Context contexto;
    private List<Producto> listado;

    public ProductoAdapter(@NonNull Context context, List<Producto> datos) {
        super(context, R.layout.adapter_item_categoria, datos);
        this.contexto = context;
        this.listado = datos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(contexto);

        View item = inflater.inflate(R.layout.adapter_item_categoria, null);

        Producto marca = listado.get(position);

        TextView nombre = (TextView) item.findViewById(R.id.nombre);
        nombre.setText(marca.getNombre());

        TextView descripcion = (TextView) item.findViewById(R.id.descripcion);
        descripcion.setText(marca.getDescripcion());

        return item;
    }
}
