package amsi.dei.estg.ipleiria.evo_menu.Adaptadores;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.evo_menu.Model.Item;
import amsi.dei.estg.ipleiria.evo_menu.Model.Menu;
import amsi.dei.estg.ipleiria.evo_menu.R;

public class ListaItemsAdaptador extends BaseAdapter {
    private ArrayList<Item> listaItens;
    private Context contexto;
    private LayoutInflater inflater;
    public ListaItemsAdaptador(Context context, ArrayList<Item> lista){
        contexto = context;
        listaItens = lista;
    }

    @Override
    public int getCount() {
        return listaItens.size();
    }

    @Override
    public Object getItem(int i) {
        return listaItens.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listaItens.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (inflater == null)
            inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //preenche cada quadrado
        if (view == null)
            view = inflater.inflate(R.layout.item_lista_menu, null);

        //Preenchimento do view
        ListaItemsAdaptador.ViewHolderLista viewHolder = (ListaItemsAdaptador.ViewHolderLista) view.getTag();
        if (viewHolder == null) {
            viewHolder = new ListaItemsAdaptador.ViewHolderLista(view);
            view.setTag(viewHolder);
        }
        viewHolder.update(listaItens.get(i), listaItens.get(i).getId());
        return view;
    }

    private class ViewHolderLista {
        private TextView tvNomeItem;
        private TextView tvValorItem;
        private Button btnAdicionar;
        private int i;

        public ViewHolderLista(View view) {
            tvNomeItem = view.findViewById(R.id.tvNomeItem);
            tvValorItem = view.findViewById(R.id.tvValorItem);
            btnAdicionar = view.findViewById(R.id.btAdicionarItem);

            btnAdicionar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("Nome Item",listaItens.get(i).getNome());
                }
            });
        }

        public void update(Item item, int i) {
            if(item != null) {
                tvNomeItem.setText(item.getNome());
                tvValorItem.setText(item.getPreco() + "€");
                i = i;
            }
        }
    }
}
