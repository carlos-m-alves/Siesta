package br.bean;

import java.io.Serializable;

public class Avaliacao implements Serializable{
    private Double media;
    private int quantidade;
    private int idQuarto;
    private int idReserva;
    private int idUsuario;
    private Double avaliacao;

    public Avaliacao() {
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(Double media) {
        this.media = media;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getIdQuarto() {
        return idQuarto;
    }

    public void setIdQuarto(int idQuarto) {
        this.idQuarto = idQuarto;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }
    
    
}
