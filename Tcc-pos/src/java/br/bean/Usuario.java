package br.bean;

import java.io.Serializable;
import java.util.Date;

public class Usuario implements Serializable{
    private int id;
    private String nome;
    private String cpf;
    private Date datanascimento;
    private String email;
    private String senha;
    private char tipo;       

    public Usuario() {
    }

    public Usuario(int id, String nome, Date datanascimento, String email, char tipo) {
        this.id = id;
        this.nome = nome;
        this.datanascimento = datanascimento;
        this.email = email;
        this.tipo = tipo;
    }

    public Usuario(String nome, Date datanascimento, String email, char tipo) {
        this.nome = nome;
        this.datanascimento = datanascimento;
        this.email = email;
        this.tipo = tipo;
    }

    public Usuario(int id, String nome, String cpf, Date datanascimento, String email, String senha, char tipo) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.datanascimento = datanascimento;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
    }

    public Usuario(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public Date getDatanascimento() {
        return datanascimento;
    }

    public void setDatanascimento(Date datanascimento) {
        this.datanascimento = datanascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }  
}