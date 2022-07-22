package br.bean;

import java.io.Serializable;
import java.util.Date;

public class Hospedagem implements Serializable {
    private int id;
    private String nome;
    private String bairro;
    private Date dtCadastro;
    private Date dtRevisao;
    private String rua;
    private int numero;
    private String complemento;
    private int idBairro;
    private String cep;
    private int idParceiro;
    private int idEndereco;

    public Hospedagem() {
    }
    
    public Hospedagem(int id, String nome, String bairro) {
        this.id = id;
        this.nome = nome;
        this.bairro = bairro;
    }
    
    public Hospedagem(String nome, String bairro) {
        this.nome = nome;
        this.bairro = bairro;
    }

    public Hospedagem(int id, String nome, String bairro, Date dtCadastro, Date dtRevisao) {
        this.id = id;
        this.nome = nome;
        this.bairro = bairro;
        this.dtCadastro = dtCadastro;
        this.dtRevisao = dtRevisao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Date getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(Date dtCadastro) {
        this.dtCadastro = dtCadastro;
    }

    public Date getDtRevisao() {
        return dtRevisao;
    }

    public void setDtRevisao(Date dtRevisao) {
        this.dtRevisao = dtRevisao;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public int getIdBairro() {
        return idBairro;
    }

    public void setIdBairro(int idBairro) {
        this.idBairro = idBairro;
    }

    public int getIdParceiro() {
        return idParceiro;
    }

    public void setIdParceiro(int idParceiro) {
        this.idParceiro = idParceiro;
    }

    public int getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
    }
}
