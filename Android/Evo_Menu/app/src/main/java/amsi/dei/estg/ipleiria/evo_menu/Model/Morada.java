package amsi.dei.estg.ipleiria.evo_menu.Model;

import java.io.Serializable;

public class Morada implements Serializable {
    private int id;
    private String pais, cidade, rua, codpost;

    public Morada(int id, String pais, String cidade, String rua, String codpost) {
        this.id = id;
        this.pais = pais;
        this.cidade = cidade;
        this.rua = rua;
        this.codpost = codpost;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getPais() {return pais;}
    public void setPais(String pais) {this.pais = pais;}

    public String getCidade() {return cidade;}
    public void setCidade(String cidade) {this.cidade = cidade;}

    public String getRua() {return rua;}
    public void setRua(String rua) {this.rua = rua;}

    public String getCodpost() {return codpost;}
    public void setCodpost(String codpost) {this.codpost = codpost;}

    @Override
    public String toString() {
        return this.pais + ", " + this.cidade + ", " + this.rua + " " + this.codpost;
    }
}
