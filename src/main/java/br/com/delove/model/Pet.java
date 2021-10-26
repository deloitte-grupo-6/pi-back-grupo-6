package br.com.delove.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Pet implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    private String nome;

    @NotNull
    @ManyToOne
    @JsonIgnoreProperties("petsEmDoacao")
    private Usuario doador;

    @NotNull
    private boolean disponivel;

    @NotNull
    @Size(min=3, max=20)
    private String especie;

    @NotNull
    @Size(min=3, max=20)
    private String raca;

    @NotNull
    @Size(min=4, max=7)
    private String sexo;

    @NotNull
    private LocalDate dataNascimento;

//    @NotNull
//    private String imagemUrl;

    @Size(max=500)
    private String descricao;

    @ManyToMany(mappedBy = "petsInteressados")
    private List<Usuario> listaInteressados;


    public Pet(String nome, Usuario doador, String especie, String raca, String sexo, LocalDate dataNascimento, String descricao) {
        this.nome = nome;
        this.doador = doador;
        this.especie = especie;
        this.raca = raca;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.descricao = descricao;
    }

    public Pet(){}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Usuario getDoador() {
        return doador;
    }

    public void setDoador(Usuario doador) {
        this.doador = doador;
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
        return listaInteressados;
    }

    public void setFilaInteressados(List<Usuario> filaInteressados) {
        this.listaInteressados = filaInteressados;
    }
}
