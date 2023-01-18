package amsi.dei.estg.ipleiria.evo_menu.Adaptores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import java.util.ArrayList;
import amsi.dei.estg.ipleiria.evo_menu.Model.Restaurante;
import amsi.dei.estg.ipleiria.evo_menu.R;

public class ListaRestaurantesAdaptor extends BaseAdapter
{
    private ArrayList<Restaurante> listaRestaurantes;
    private Context contexto;
    private LayoutInflater inflater;

    public ListaRestaurantesAdaptor(Context context, ArrayList<Restaurante> lista)
    {
        this.contexto = context;
        this.listaRestaurantes = lista;

    }

    @Override
    public int getCount() { return listaRestaurantes.size();}

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
    private class ViewHolderLista
    {
        private TextView tvNome;

        public ViewHolderLista(View view)
        {
            tvNome = view.findViewById(R.id.tvNomeRestaurante);

        }

        public void update(Restaurante livro)
        {
            tvNome.setText(livro.getNome());
        }

    }
}
