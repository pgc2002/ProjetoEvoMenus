package amsi.dei.estg.ipleiria.evo_menu.Model;

import java.io.Serializable;
import java.util.Date;

public class Users implements Serializable
{
    private int id, id_restaurante, id_mesa, id_morada;
    private String username, auth_key, pass_hash, email, verify_token, telemovel, nif, tipo, nome;
    private short status; /*se der merda mudar para int, apagar antes de enviar!!!!!*/
    private Long data_criacao, data_update;


    public Users(int id, String username, String auth_key, String pass_hash, String email, String telemovel, String nif, String nome, int id_morada) {
        this.id = id;
        this.username = username;
        this.auth_key = auth_key;
        this.pass_hash = pass_hash;
        this.email = email;
        this.status = 10;
        this.data_criacao = (System.currentTimeMillis());
        this.data_update = (System.currentTimeMillis());
        this.verify_token = null;
        this.telemovel = telemovel;
        this.nif = nif;
        this.tipo = "Cliente";
        this.nome = nome;
        this.id_restaurante = Integer.parseInt(null);
        this.id_morada = id_morada;
        this.id_mesa = Integer.parseInt(null);
    }


    public int getId() {return id; }

    public void setId(int id) {this.id = id; }

    public String getUsername() {return username; }

    public void setUsername(String username) {this.username = username; }

    public String getAuth_key() {return auth_key; }

    public void setAuth_key(String auth_key) {this.auth_key = auth_key; }

    public String getPass_hash() {return pass_hash; }

    public void setPass_hash(String pass_hash) {this.pass_hash = pass_hash; }

    public String getEmail() {return email; }

    public void setEmail(String email) {this.email = email; }

    public short getStatus() {return status; }

    public void setStatus(short status) {this.status = status; }

    public Long getData_criacao() {return data_criacao; }

    public void setData_criacao(Long data_criacao) {this.data_criacao = data_criacao; }

    public Long getData_update() {return data_update; }

    public void setData_update(Long data_update) {this.data_update = data_update; }

    public String getVerify_token() {return verify_token; }

    public void setVerify_token(String verify_token) {this.verify_token = verify_token; }

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
