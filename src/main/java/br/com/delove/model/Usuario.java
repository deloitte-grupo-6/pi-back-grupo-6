package br.com.delove.model;

import java.util.List;

public abstract class Usuario {

    private Long id;
    private String nome;
    private String email;
    private String senha;
    private String contato;
    private String cidade;
    private List<Pet> petsDoados;

    public Usuario() {
    }

    public Usuario(String nome, String email, String senha, String contato, String cidade) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.contato = contato;
        this.cidade = cidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public List<Pet> getPetsDoados() {
        return petsDoados;
    }

    public void setPetsDoados(List<Pet> petsDoados) {
        this.petsDoados = petsDoados;
    }
}
