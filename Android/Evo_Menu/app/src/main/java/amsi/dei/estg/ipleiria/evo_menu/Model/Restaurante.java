package amsi.dei.estg.ipleiria.evo_menu.Model;

import java.io.Serializable;

public class Restaurante implements Serializable
{
    //public static int autoincremento = 0;
    private int id, lotacao_max, id_ementa, id_horario, id_morada;
    private String nome, email, telemovel;

    public Restaurante(int id, String nome, String email, int lotacao_max, String telemovel, int id_ementa, int id_horario, int id_morada)
    {
        this.id=id;
        this.nome=nome;
        this.email=email;
        this.lotacao_max=lotacao_max;
        this.telemovel=telemovel;
        this.id_ementa=id_ementa;
        this.id_horario=id_horario;
        this.id_morada=id_horario;
    }

    public int getId() {return id; }

    public void setId(int id) {this.id = id; }

    public String getNome() {return nome; }

    public void setNome(String nome) {this.nome = nome; }

    public String getEmail() {return email; }

    public void setEmail(String email) {this.email = email; }

    public int getLotacao_max() {return lotacao_max; }

    public void setLotacao_max(int lotacao_max) {this.lotacao_max = lotacao_max; }

    public String getTelemovel() {return telemovel; }

    public void setTelemovel(String telemovel) {this.telemovel = telemovel; }

    public int getId_ementa() {return id_ementa; }

    public void setId_ementa(int id_ementa) {this.id_ementa = id_ementa; }

    public int getId_horario() {return id_horario; }

    public void setId_horario(int id_horario) {this.id_horario = id_horario; }

    public int getId_morada() {return id_morada; }

    public void setId_morada(int id_morada) {this.id_morada = id_morada; }

    @Override
    public String toString() { return this.nome + ", " + this.telemovel; }
}
