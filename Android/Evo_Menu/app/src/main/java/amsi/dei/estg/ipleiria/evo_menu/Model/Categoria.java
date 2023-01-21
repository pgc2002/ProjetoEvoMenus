package amsi.dei.estg.ipleiria.evo_menu.Model;

import java.io.Serializable;

public class Categoria implements Serializable
{
    private int id, idRestaurante;
    private String nome;

    public Categoria(int id, String nome, int idRestaurante) {
        this.id = id;
        this.nome = nome;
        this.idRestaurante = idRestaurante;
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

    public int getIdRestaurante() {
        return idRestaurante;
    }

    public void setIdRestaurante(int idRestaurante) {
        this.idRestaurante = idRestaurante;
    }
}
