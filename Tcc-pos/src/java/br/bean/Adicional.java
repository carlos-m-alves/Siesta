package br.bean;

public class Adicional {
    private int id;
    private String descricao;

    public Adicional() {
    }
    
    public Adicional(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
