package com.deps.consultorioapi.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Agenda implements Serializable {

    private static final long serialVerionUID = 1L;

    @Id
    private Long id;
    @OneToOne
    private Medico medico;

    private String diaAtendimento;
    private String horarioAtendimento;

    public Agenda(){}

    public Agenda(Medico medico, String diaAtendimento, String horarioAtendimento){
        this.medico = medico;
        this.diaAtendimento = diaAtendimento;
        this.horarioAtendimento = horarioAtendimento;

    }

    public Long getId() {
        return id;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
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
