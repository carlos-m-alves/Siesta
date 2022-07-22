package br.bean;

import java.io.Serializable;
import java.util.Date;

public class Parceiro extends Usuario implements Serializable {

    private int idHotel;
    private String nomeHospedagem;
    
    public Parceiro() {
    }

    public Parceiro(int id, String nome, Date datanascimento, String email, char tipo) {
        super(id, nome, datanascimento, email, tipo);
    }

    public int getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(int idHotel) {
        this.idHotel = idHotel;
    }

    public String getNomeHospedagem() {
        return nomeHospedagem;
    }

    public void setNomeHospedagem(String nomeHospedagem) {
        this.nomeHospedagem = nomeHospedagem;
    }
    
    
}
