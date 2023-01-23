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

import amsi.dei.estg.ipleiria.evo_menu.Model.Menu;
import amsi.dei.estg.ipleiria.evo_menu.Model.SingletonGestorPedidos;
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
        viewHolder.update(listaMenus.get(i), listaMenus.get(i).getId());
        return view;
    }

    private class ViewHolderLista {
        private TextView tvNomeItem;
        private TextView tvValorItem;
        private Button btnAdicionar;
        private int i;
        ArrayList<Integer> idMenus = new ArrayList<>();

        public ViewHolderLista(View view) {
            tvNomeItem = view.findViewById(R.id.tvNomeItemPagamento);
            tvValorItem = view.findViewById(R.id.tvValorItemPagamento);
            btnAdicionar = view.findViewById(R.id.btAdicionarItem);

            btnAdicionar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("Nome Item",listaMenus.get(i).getNome());
                    if (SingletonGestorPedidos.getInstance(contexto.getApplicationContext()).getIdMenusPedido() != null)
                        SingletonGestorPedidos.getInstance(contexto.getApplicationContext()).getIdMenusPedido().add(listaMenus.get(i).getId());
                    else {
                        idMenus.add(listaMenus.get(i).getId());
                        SingletonGestorPedidos.getInstance(contexto.getApplicationContext()).setIdMenusPedido(idMenus);
                    }
                }
            });
        }

        public void update(Menu menu, int i) {
            tvNomeItem.setText(menu.getNome());
            tvValorItem.setText(menu.getDesconto()+"");
            i = i;
        }
    }
}
