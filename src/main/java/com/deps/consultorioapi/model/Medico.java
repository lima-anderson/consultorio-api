package com.deps.consultorioapi.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Medico implements Serializable {

    private static final long serialVerionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long crm;
    private String nome;
    private String especialidade;
    private String telefone;
    private String email;
    @OneToOne
    private Agenda agenda;

    public Medico(){}

    // Construtor sem agenda
    public Medico(Long crm, String nome, String especialidade, String telefone, String email){
        this.crm = crm;
        this.nome = nome;
        this.especialidade = especialidade;
        this.telefone = telefone;
        this.email = email;
    }

    // Construtor com agenda
    public Medico(Long crm, String nome, String especialidade, String telefone, String email, Agenda agenda){
        this.crm = crm;
        this.nome = nome;
        this.especialidade = especialidade;
        this.telefone = telefone;
        this.email = email;
        this.agenda = agenda;
    }

    public Long getId() {
        return id;
    }

    public Long getCrm() {
        return crm;
    }

    public void setCrm(Long crm) {
        this.crm = crm;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }
}
