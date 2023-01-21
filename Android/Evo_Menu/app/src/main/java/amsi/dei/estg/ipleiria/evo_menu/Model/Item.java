package amsi.dei.estg.ipleiria.evo_menu.Model;

import java.io.Serializable;

public class Item implements Serializable
{
    private int id;
    private int idCategoria;
    private String nome;
    private String fotografia;
    private double preco;

    public Item(int id, String nome, String fotografia, double preco, int idCategoria) {
        this.id = id;
        this.nome = nome;
        this.fotografia = fotografia;
        this.preco = preco;
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

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getFotografia() {
        return fotografia;
    }

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
}
