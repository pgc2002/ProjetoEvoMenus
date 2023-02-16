package amsi.dei.estg.ipleiria.evo_menu.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.evo_menu.Model.Restaurante;
import amsi.dei.estg.ipleiria.evo_menu.R;

public class ListaRestaurantesAdaptador extends BaseAdapter {

    private final ArrayList<Restaurante> listaRestaurantes;
    private final Context contexto;
    private LayoutInflater inflater;

    public ListaRestaurantesAdaptador(Context context, ArrayList<Restaurante> lista)
    {
        this.contexto = context;
        this.listaRestaurantes = lista;
    }

    @Override
    public int getCount()
    {
        return listaRestaurantes.size();
    }

    @Override
    public Object getItem(int position)
    {
        return listaRestaurantes.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return listaRestaurantes.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //preenche cada quadrado
        if (convertView == null)
            convertView = inflater.inflate(R.layout.item_lista_restaurante, null);

        //Preenchimento do view
        ViewHolderLista viewHolder = (ViewHolderLista) convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new ViewHolderLista(convertView);
            convertView.setTag(viewHolder);
        }
        viewHolder.update(listaRestaurantes.get(position));
        return convertView;
    }

    private static class ViewHolderLista {
        private final TextView tvNomeRestaurante;
        //private ImageView ivRestauranteBackground;

        public ViewHolderLista(View view) {
            tvNomeRestaurante = view.findViewById(R.id.tvNomeItemPagamento);
            //ivRestauranteBackground = view.findViewById(R.id.ivRestauranteBackground);
        }

        public void update(Restaurante restaurante) {
            tvNomeRestaurante.setText(restaurante.getNome());
            //Glide.with(contexto).load(restaurante.getCapa()).placeholder(R.drawable.logoipl).diskCacheStrategy(DiskCacheStrategy.ALL).into(ivCapa);
        }
    }
}
