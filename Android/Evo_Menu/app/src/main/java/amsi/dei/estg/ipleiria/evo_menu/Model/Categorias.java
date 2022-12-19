package amsi.dei.estg.ipleiria.evo_menu.Model;

import java.io.Serializable;

public class Categorias implements Serializable
{
    private int id, id_ementa;
    private String nome;

    public Categorias(int id, int id_ementa, String nome) {
        this.id = id;
        this.id_ementa = id_ementa;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_ementa() {
        return id_ementa;
    }

    public void setId_ementa(int id_ementa) {
        this.id_ementa = id_ementa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
