package amsi.dei.estg.ipleiria.evo_menu.Model;

import android.widget.ImageView;

import java.io.Serializable;

public class Menu implements Serializable
{
    private int id;
    private int idCategoria;
    private String nome;
    private String fotografia;
    private double desconto;

    public Menu(int id, String nome, String fotografia, double desconto, int idCategoria) {
        this.id = id;
        this.nome = nome;
        this.fotografia = fotografia;
        this.desconto = desconto;
        this.idCategoria = idCategoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getFotografia() {
        return fotografia;
    }

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }
}
