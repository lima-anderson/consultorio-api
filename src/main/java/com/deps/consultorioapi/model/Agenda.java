package com.deps.consultorioapi.model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class Agenda implements Serializable {

    private static final long serialVerionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String NomeMedico;

    private String diaAtendimento;
    private String horarioAtendimento;

    public Agenda(){}

    public Agenda(Medico medico, String diaAtendimento, String horarioAtendimento){
        this.NomeMedico = medico.getNome();
        this.diaAtendimento = diaAtendimento;
        this.horarioAtendimento = horarioAtendimento;

    }

    public Long getId() {
        return id;
    }

    public String getNomeMedico() {
        return this.NomeMedico;
    }

    public void setMedico(Medico medico) {
        this.NomeMedico = medico.getNome();
    }

    public String getDiaAtendimento() {
        return diaAtendimento;
    }

    public void setDiaAtendimento(String diaAtendimento) {
        this.diaAtendimento = diaAtendimento;
    }

    public String getHorarioAtendimento() {
        return horarioAtendimento;
    }

    public void setHorarioAtendimento(String horarioAtendimento) {
        this.horarioAtendimento = horarioAtendimento;
    }
}
