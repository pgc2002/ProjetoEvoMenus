package amsi.dei.estg.ipleiria.evo_menu.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.evo_menu.Model.Menu;
import amsi.dei.estg.ipleiria.evo_menu.Model.Restaurante;
import amsi.dei.estg.ipleiria.evo_menu.R;

public class ListaMenusAdaptador extends BaseAdapter {
    private ArrayList<Menu> listaMenus;
    private Context contexto;
    private LayoutInflater inflater;

    public ListaMenusAdaptador(Context context, ArrayList<Menu> lista){
        contexto = context;
        listaMenus = lista;
    }

    @Override
    public int getCount() {
        return listaMenus.size();
    }

    @Override
    public Object getItem(int i) {
        return listaMenus.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listaMenus.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (inflater == null)
            inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //preenche cada quadrado
        if (view == null)
            view = inflater.inflate(R.layout.item_lista_menu, null);

        //Preenchimento do view
        ListaMenusAdaptador.ViewHolderLista viewHolder = (ListaMenusAdaptador.ViewHolderLista) view.getTag();
        if (viewHolder == null) {
            viewHolder = new ListaMenusAdaptador.ViewHolderLista(view);
            view.setTag(viewHolder);
        }
        viewHolder.update(listaMenus.get(i));
        return view;
    }

    private class ViewHolderLista {
        private TextView tvNomeItem;
        private TextView tvValorItem;

        public ViewHolderLista(View view) {
            tvNomeItem = view.findViewById(R.id.tvNomeItem);
            tvValorItem = view.findViewById(R.id.tvValorItem);
        }

        public void update(Menu menu) {
            tvNomeItem.setText(menu.getNome());
            tvValorItem.setText(menu.getDesconto()+"");
        }
    }
}
