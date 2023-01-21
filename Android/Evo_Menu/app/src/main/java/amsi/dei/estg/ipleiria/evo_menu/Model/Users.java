package amsi.dei.estg.ipleiria.evo_menu.Model;

import java.io.Serializable;
import java.util.Date;

public class Users implements Serializable
{
    private int id, id_restaurante, id_mesa, id_morada;
    private String username, pass, email, telemovel, nif, tipo, nome;
    private short status;


    public Users(int id, String username, String nome, String pass, String email, String telemovel, String nif, int id_morada) {
        this.id = id;
        this.username = username;
        this.pass = pass;
        this.email = email;
        this.status = 10;
        this.telemovel = telemovel;
        this.nif = nif;
        this.tipo = "Cliente";
        this.nome = nome;
        this.id_restaurante = -1;
        this.id_morada = -1;
        this.id_mesa = -1;
    }

    public Users(int id, String username, String nome, String pass, String email, String telemovel, String nif) {
        this.id = id;
        this.username = username;
        this.pass = pass;
        this.email = email;
        this.status = 10;
        this.telemovel = telemovel;
        this.nif = nif;
        this.tipo = "Cliente";
        this.nome = nome;
        this.id_restaurante = -1;
        this.id_morada = -1;
        this.id_mesa = -1;
    }


    public int getId() {return id; }

    public void setId(int id) {this.id = id; }

    public String getUsername() {return username; }

    public void setUsername(String username) {this.username = username; }

    public String getPass() {return pass; }

    public void setPass(String pass) {this.pass = pass; }

    public String getEmail() {return email; }

    public void setEmail(String email) {this.email = email; }

    public short getStatus() {return status; }

    public void setStatus(short status) {this.status = status; }

    public String getTelemovel() {return telemovel; }

    public void setTelemovel(String telemovel) {this.telemovel = telemovel; }

    public String getNif() {return nif; }

    public void setNif(String nif) {this.nif = nif; }

    public String getTipo() {return tipo; }

    public void setTipo(String tipo) {this.tipo = tipo; }

    public String getNome() {return nome; }

    public void setNome(String nome) {this.nome = nome; }

    public int getId_restaurante() {return id_restaurante; }

    public void setId_restaurante(int id_restaurante) {this.id_restaurante = id_restaurante; }

    public int getId_morada() {return id_morada; }

    public void setId_morada(int id_morada) {this.id_morada = id_morada; }

    public int getId_mesa() {return id_mesa; }

    public void setId_mesa(int id_mesa) {this.id_mesa = id_mesa; }
}
