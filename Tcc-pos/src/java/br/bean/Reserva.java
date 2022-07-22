package br.bean;

import java.io.Serializable;
import java.util.Date;

public class Reserva implements Serializable{
    private int idReserva;
    private int idHotel;
    private int idQuarto;
    private int idPessoa;
    private int idHrChegada;
    private Date hrSaida;
    private boolean pago;
    private Double preco;
    private String nomePessoa;
    private String cpf;
    private String nomeQuarto;
    private String nomeHospedagem;
    private Date horarioEntrada;
    private Date dtReserva;
    private String disponibilidade;
    private Endereco endereco;
    private String bairro;
    private Avaliacao avaliacao;
    
    public Reserva() {
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public int getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(int idHotel) {
        this.idHotel = idHotel;
    }

    
    public int getIdQuarto() {
        return idQuarto;
    }

    public void setIdQuarto(int idQuarto) {
        this.idQuarto = idQuarto;
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public int getIdHrChegada() {
        return idHrChegada;
    }

    public void setIdHrChegada(int idHrChegada) {
        this.idHrChegada = idHrChegada;
    }

    public Date getHrSaida() {
        return hrSaida;
    }

    public void setHrSaida(Date hrSaida) {
        this.hrSaida = hrSaida;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public String getNomeQuarto() {
        return nomeQuarto;
    }

    public void setNomeQuarto(String nomeQuarto) {
        this.nomeQuarto = nomeQuarto;
    }

    public Date getHorarioEntrada() {
        return horarioEntrada;
    }

    public void setHorarioEntrada(Date horarioEntrada) {
        this.horarioEntrada = horarioEntrada;
    }

    public Date getDtReserva() {
        return dtReserva;
    }

    public void setDtReserva(Date dtReserva) {
        this.dtReserva = dtReserva;
    }        

    public String getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(String disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNomeHospedagem() {
        return nomeHospedagem;
    }

    public void setNomeHospedagem(String nomeHospedagem) {
        this.nomeHospedagem = nomeHospedagem;
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    
}
