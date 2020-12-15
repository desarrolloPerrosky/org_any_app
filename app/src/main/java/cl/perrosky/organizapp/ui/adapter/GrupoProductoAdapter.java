package cl.perrosky.organizapp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import cl.perrosky.organizapp.R;
import cl.perrosky.organizapp.model.GrupoProducto;
import cl.perrosky.organizapp.model.Producto;

public class GrupoProductoAdapter extends ArrayAdapter {

    private Context contexto;
    private List<GrupoProducto> listado;

    public GrupoProductoAdapter(@NonNull Context context, List<GrupoProducto> datos) {
        super(context, R.layout.adapter_item_producto, datos);
        this.contexto = context;
        this.listado = datos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(contexto);

        View item = inflater.inflate(R.layout.adapter_item_grupo_producto, null);

        GrupoProducto producto = listado.get(position);

        ImageView imagen = (ImageView) item.findViewById(R.id.imagen);
        imagen.setImageResource(R.drawable.producto);

        TextView nombre = (TextView) item.findViewById(R.id.nombre);
        nombre.setText(producto.getProducto().getNombre());

        TextView valor = (TextView) item.findViewById(R.id.valorxunidad);
        valor.setText(producto.getPrecio().toString());

        TextView cantidad = (TextView) item.findViewById(R.id.cantidad);
        cantidad.setText(producto.getCantidad().toString());

        TextView marca = (TextView) item.findViewById(R.id.marca);
        marca.setText(producto.getProducto().getMarca().getNombre());

        TextView categoria = (TextView) item.findViewById(R.id.categoria);
        categoria.setText(producto.getProducto().getCategoria().getNombre());
        
        return item;
    }
}
