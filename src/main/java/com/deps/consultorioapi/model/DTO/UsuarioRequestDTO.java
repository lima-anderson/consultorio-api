package com.deps.consultorioapi.model.DTO;

public class UsuarioRequestDTO {

    private String nome;
    private String username;
    private String password;
    private Boolean admin;

    public UsuarioRequestDTO(){}

    public UsuarioRequestDTO(String nome, String username, String password, Boolean admin) {
        this.nome = nome;
        this.username = username;
        this.password = password;
        this.admin = admin;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
}
