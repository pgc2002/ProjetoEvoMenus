package amsi.dei.estg.ipleiria.evo_menu.Model;

import java.io.Serializable;

public class Mesa implements Serializable
{
    private int id, id_restaurante, numero, capacidade;
    private String estado;

    public Mesa(int id, int numero, int capacidade, String estado, int id_restaurante) {
        this.id = id;
        this.numero = numero;
        this.capacidade = capacidade;
        this.estado = estado;
        this.id_restaurante = id_restaurante;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getId_restaurante() {
        return id_restaurante;
    }

    public void setId_restaurante(int id_restaurante) {
        this.id_restaurante = id_restaurante;
    }

    @Override
    public String toString(){
        return "Mesa nº" + this.numero + "||" + "Capacidade: " + this.capacidade + " ludares";
    }
}
