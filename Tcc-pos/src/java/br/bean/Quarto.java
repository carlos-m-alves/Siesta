package br.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Quarto implements Serializable {
    private int id;
    private int idHospedagem;
    private String nome;
    private double classificacao;
    private double preco;
    private Date dtCadastro;
    private Date dtRevisao;
    private String descricao;
    
    private List<Horario> listaHorarios;
    private List<Adicional> listaAdicionais;
    private List<String> listaImagemFirebase;
    
    public Quarto() {
    }

    public Quarto(int id, int idHospedagem, String nome, double classificacao, double preco) {
        this.id = id;
        this.idHospedagem = idHospedagem;
        this.nome = nome;
        this.classificacao = classificacao;
        this.preco = preco;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdHospedagem() {
        return idHospedagem;
    }

    public void setIdHospedagem(int idHospedagem) {
        this.idHospedagem = idHospedagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(double classificacao) {
        this.classificacao = classificacao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
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

    public List<Horario> getListaHorarios() {
        return listaHorarios;
    }

    public void setListaHorarios(List<Horario> listaHorarios) {
        this.listaHorarios = listaHorarios;
    }

    public List<Adicional> getListaAdicionais() {
        return listaAdicionais;
    }

    public void setListaAdicionais(List<Adicional> listaAdicionais) {
        this.listaAdicionais = listaAdicionais;
    }

    public List<String> getListaImagemFirebase() {
        return listaImagemFirebase;
    }

    public void setListaImagemFirebase(List<String> listaImagemFirebase) {
        this.listaImagemFirebase = listaImagemFirebase;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
}
