package amsi.dei.estg.ipleiria.evo_menu.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import amsi.dei.estg.ipleiria.evo_menu.Listeners.UserListener;
import amsi.dei.estg.ipleiria.evo_menu.Model.Morada;
import amsi.dei.estg.ipleiria.evo_menu.Model.SingletonGestorUsers;
import amsi.dei.estg.ipleiria.evo_menu.Model.User;
import amsi.dei.estg.ipleiria.evo_menu.R;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;


public class VerPerfilFragment extends Fragment{

    public VerPerfilFragment() {
    }

    private TextView tvNome, tvEmail, tvTelemovel, tvNif, tvRua, tvCodPost, tvPais, tvCidade;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ver_perfil, container, false);

        tvNome = view.findViewById(R.id.tvNome);
        tvEmail = view.findViewById(R.id.tvEmail);
        tvTelemovel = view.findViewById(R.id.tvTelemovel);
        tvNif = view.findViewById(R.id.tvNif);

        tvRua = view.findViewById(R.id.tvRua);
        tvCodPost = view.findViewById(R.id.tvCodPost);
        tvPais = view.findViewById(R.id.tvPais);
        tvCidade = view.findViewById(R.id.tvCidade);

        User user = SingletonGestorUsers.getInstance(getContext()).getUserLogado();

        if(user != null) {
            tvNome.setText(user.getNome());
            tvEmail.setText(user.getEmail());
            tvTelemovel.setText(user.getTelemovel());
            tvNif.setText(user.getNif());
        }

        Morada morada = user.getMorada();

        if(morada != null){
            tvPais.setText(morada.getPais());
            tvCidade.setText(morada.getCidade());
            tvRua.setText(morada.getRua());
            tvCodPost.setText(morada.getCodpost());
        }

        Button btn = view.findViewById(R.id.btEditarPerfil);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Fragment editarPerfil = new EditarPerfilFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.contentFragment, editarPerfil);
                fragmentTransaction.commit();*/


                Intent intent = new Intent(getContext(), EditarPerfilActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}