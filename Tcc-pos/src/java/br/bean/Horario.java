package br.bean;

import java.io.Serializable;
import java.util.Date;

public class Horario implements Serializable{
    private int idHorario;
    private String disponibilidade;
    private Date horario;

    public Horario() {
    }

    public Horario(String disponibilidade, Date horario) {
        this.disponibilidade = disponibilidade;
        this.horario = horario;
    }

    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    public String getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(String disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public Date getHorario() {
        return horario;
    }

    public void setHorario(Date horario) {
        this.horario = horario;
    }
}
