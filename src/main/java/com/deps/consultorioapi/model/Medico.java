package com.deps.consultorioapi.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Medico implements Serializable {

    private static final long serialVerionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long crm;
    private String nome;
    private Especialidade especialidade;
    private String telefone;
    private String email;
    @OneToOne
    private Agenda agenda;

    @OneToMany
    private List<Consulta> consultas = new ArrayList<>();

    public Medico(){}

    // Construtor sem agenda
    public Medico(Long crm, String nome, Especialidade especialidade, String telefone, String email){
        this.crm = crm;
        this.nome = nome;
        this.especialidade = especialidade;
        this.telefone = telefone;
        this.email = email;
    }

//    // Construtor com agenda
//    public Medico(Long crm, String nome, Especialidade especialidade, String telefone, String email, Agenda agenda){
//        this.crm = crm;
//        this.nome = nome;
//        this.especialidade = especialidade;
//        this.telefone = telefone;
//        this.email = email;
//        this.agenda = agenda;
//    }

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

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
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

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }

    public void addConsulta(Consulta consulta){
        this.consultas.add(consulta);
    }
}
