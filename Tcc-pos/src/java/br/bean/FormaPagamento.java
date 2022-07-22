package br.bean;

import java.io.Serializable;

public class FormaPagamento implements Serializable{
    private int idFormaPagamento;
    private String descPagamento;

    public FormaPagamento() {
    }

    public int getIdFormaPagamento() {
        return idFormaPagamento;
    }

    public void setIdFormaPagamento(int idFormaPagamento) {
        this.idFormaPagamento = idFormaPagamento;
    }

    public String getDescPagamento() {
        return descPagamento;
    }

    public void setDescPagamento(String descPagamento) {
        this.descPagamento = descPagamento;
    }
}
