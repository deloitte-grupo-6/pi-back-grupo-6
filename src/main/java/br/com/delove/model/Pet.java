package br.com.delove.model;

import java.time.LocalDate;
import java.util.List;


public class Pet {

    private Long id;
    private String nome;
    private Usuario tutor;
    private boolean disponivel;
    private String especie;
    private String raca;
    private String sexo;
    private LocalDate dataNascimento;
    private String descricao;
    private List<Usuario> filaInteressados;

    public Pet(String nome, Usuario tutor, boolean disponivel, String especie, String raca, String sexo, LocalDate dataNascimento, String descricao) {
        this.nome = nome;
        this.tutor = tutor;
        this.disponivel = disponivel;
        this.especie = especie;
        this.raca = raca;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Usuario getTutor() {
        return tutor;
    }

    public void setTutor(Usuario tutor) {
        this.tutor = tutor;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Usuario> getFilaInteressados() {
        return filaInteressados;
    }

    public void setFilaInteressados(List<Usuario> filaInteressados) {
        this.filaInteressados = filaInteressados;
    }
}
