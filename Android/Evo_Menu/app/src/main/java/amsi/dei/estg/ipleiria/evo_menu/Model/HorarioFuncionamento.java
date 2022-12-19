package amsi.dei.estg.ipleiria.evo_menu.Model;

import java.io.Serializable;

public class HorarioFuncionamento implements Serializable
{
    private int id;
    private String segunda_feira, terca_feira, quarta_feira, quinta_feira, sexta_feira, sabado, domingo;


    public HorarioFuncionamento(int id, String segunda_feira, String terca_feira, String quarta_feira, String quinta_feira, String sexta_feira, String sabado, String domingo) {
        this.id = id;
        this.segunda_feira = segunda_feira;
        this.terca_feira = terca_feira;
        this.quarta_feira = quarta_feira;
        this.quinta_feira = quinta_feira;
        this.sexta_feira = sexta_feira;
        this.sabado = sabado;
        this.domingo = domingo;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSegunda_feira() {
        return segunda_feira;
    }

    public void setSegunda_feira(String segunda_feira) {
        this.segunda_feira = segunda_feira;
    }

    public String getTerca_feira() {
        return terca_feira;
    }

    public void setTerca_feira(String terca_feira) {
        this.terca_feira = terca_feira;
    }

    public String getQuarta_feira() {
        return quarta_feira;
    }

    public void setQuarta_feira(String quarta_feira) {
        this.quarta_feira = quarta_feira;
    }

    public String getQuinta_feira() {
        return quinta_feira;
    }

    public void setQuinta_feira(String quinta_feira) {
        this.quinta_feira = quinta_feira;
    }

    public String getSexta_feira() {
        return sexta_feira;
    }

    public void setSexta_feira(String sexta_feira) {
        this.sexta_feira = sexta_feira;
    }

    public String getSabado() {
        return sabado;
    }

    public void setSabado(String sabado) {
        this.sabado = sabado;
    }

    public String getDomingo() {
        return domingo;
    }

    public void setDomingo(String domingo) {
        this.domingo = domingo;
    }
}
