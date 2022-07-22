package br.bean;

import java.io.Serializable;

public class Pagamento implements Serializable{
    private int idFormaPagamento;
    private int idReserva;
    private Double preco;

    public Pagamento() {
    }

    public int getIdFormaPagamento() {
        return idFormaPagamento;
    }

    public void setIdFormaPagamento(int idFormaPagamento) {
        this.idFormaPagamento = idFormaPagamento;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
