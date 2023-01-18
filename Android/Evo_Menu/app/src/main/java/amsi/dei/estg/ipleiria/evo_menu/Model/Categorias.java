package amsi.dei.estg.ipleiria.evo_menu.Model;

import java.io.Serializable;

public class Categorias implements Serializable
{
    private int id, id_restaurante;
    private String nome;

    public Categorias(int id, int id_ementa, String nome) {
        this.id = id;
        this.id_restaurante = id_ementa;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_restaurante() {
        return id_restaurante;
    }

    public void setId_restaurante(int id_restaurante) {
        this.id_restaurante = id_restaurante;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
