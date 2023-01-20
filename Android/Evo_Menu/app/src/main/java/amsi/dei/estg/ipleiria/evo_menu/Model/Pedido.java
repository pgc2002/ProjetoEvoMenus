package amsi.dei.estg.ipleiria.evo_menu.Model;

import java.io.Serializable;

public class Pedido implements Serializable
{
    private int id;
    private double valor_total;
    private String estado;
    private int id_cliente;
    private int id_restaurante;

    public Pedido(int id, double valor_total, String estado, int id_cliente, int id_restaurante) {
        this.id = id;
        this.valor_total = valor_total;
        this.estado = estado;
        this.id_cliente = id_cliente;
        this.id_restaurante = id_restaurante;
    }


    public int getId() {return id; }

    public void setId(int id) {this.id = id; }

    public double getValor_total() {return valor_total; }

    public void setValor_total(double valor_total) {this.valor_total = valor_total; }

    public String getEstado() {return estado; }

    public void setEstado(String estado) {this.estado = estado; }

    public int getId_cliente() {return id_cliente; }

    public void setId_cliente(int id_cliente) {this.id_cliente = id_cliente; }

    public int getId_restaurante() {return id_restaurante; }

    public void setId_restaurante(int id_restaurante) {this.id_restaurante = id_restaurante; }

    @Override
    public String toString() {
        return this.estado + "Valor: " + this.valor_total + "€";
    }
}
