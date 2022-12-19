package amsi.dei.estg.ipleiria.evo_menu.Model;

import java.io.Serializable;

public class Pagamento implements Serializable {
    private int id, idPedido;
    private long valor;
    private String metodo;

    public Pagamento(int id, int idPedido, long valor, String metodo) {
        this.setId(id);
        this.setIdPedido(idPedido);
        this.setValor(valor);
        this.setMetodo(metodo);
    }


    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public int getIdPedido() {return idPedido;}
    public void setIdPedido(int idPedido) {this.idPedido = idPedido;}

    public long getValor() {return valor;}
    public void setValor(long valor) {this.valor = valor;}

    public String getMetodo() {return metodo;}
    public void setMetodo(String metodo) {this.metodo = metodo;}

    @Override
    public String toString() {
        return "Metodo:" + this.metodo + "| Valor: " + this.valor + "€";
    }
}
