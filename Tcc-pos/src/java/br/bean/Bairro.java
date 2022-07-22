package br.bean;

import java.io.Serializable;

public class Bairro implements Serializable{
    private int idBairro;
    private String descricao;

    public Bairro() {
    }

    public int getIdBairro() {
        return idBairro;
    }

    public void setIdBairro(int idBairro) {
        this.idBairro = idBairro;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
}
