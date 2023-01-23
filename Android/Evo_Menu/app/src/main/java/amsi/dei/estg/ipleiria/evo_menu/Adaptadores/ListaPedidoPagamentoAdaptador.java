package amsi.dei.estg.ipleiria.evo_menu.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.evo_menu.Model.Item;
import amsi.dei.estg.ipleiria.evo_menu.Model.Menu;
import amsi.dei.estg.ipleiria.evo_menu.R;

public class ListaPedidoPagamentoAdaptador extends BaseAdapter {
    private ArrayList<Object> listaElementos;
    private Context contexto;
    private LayoutInflater inflater;

    public ListaPedidoPagamentoAdaptador(Context context, ArrayList<Item> itens, ArrayList<Menu> menus){
        contexto = context;
        this.listaElementos = new ArrayList<>();
        if (itens != null)
            for (Item item: itens)
                listaElementos.add(item);

        if (menus != null)
            for (Menu menu: menus)
                listaElementos.add(menu);
    }

    @Override
    public int getCount() {
        return listaElementos.size();
    }

    @Override
    public Object getItem(int i) {
        return listaElementos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listaElementos.size();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (inflater == null)
            inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //preenche cada quadrado
        if (view == null)
            view = inflater.inflate(R.layout.item_lista_pedido_pagamento, null);

        //Preenchimento do view
        ListaPedidoPagamentoAdaptador.ViewHolderLista viewHolder = (ListaPedidoPagamentoAdaptador.ViewHolderLista) view.getTag();
        if (viewHolder == null) {
            viewHolder = new ListaPedidoPagamentoAdaptador.ViewHolderLista(view);
            view.setTag(viewHolder);
        }
        viewHolder.update(listaElementos.get(i));
        return view;
    }

    private class ViewHolderLista {
        private TextView tvNomeItemPagamento;
        private TextView tvValorItemPagamento;

        public ViewHolderLista(View view) {
            tvNomeItemPagamento = view.findViewById(R.id.tvNomeItemPagamento);
            tvValorItemPagamento = view.findViewById(R.id.tvValorItemPagamento);
        }

        public void update(Object item) {
            if(item != null)
                if (item instanceof Item) {
                    tvNomeItemPagamento.setText(Item.class.cast(item).getNome());
                    tvValorItemPagamento.setText(Item.class.cast(item).getPreco() + "€");
                }else{
                    tvNomeItemPagamento.setText(Menu.class.cast(item).getNome());
                    tvValorItemPagamento.setText(Menu.class.cast(item).getDesconto() + "€");
                }
        }
    }
}
