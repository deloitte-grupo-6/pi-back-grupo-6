package br.com.delove.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

//    @NotNull
//    @Size(min=3, max=20)
//    private String especie;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('CACHORRO', 'GATO', 'OUTRO')")
    private Especie especie;

    @NotNull
    @Size(min=3, max=20)
    private String raca;

//    @NotNull
//    @Size(min=4, max=7)
//    private String sexo;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @NotNull
    private LocalDate dataNascimento;

//    @NotNull
//    private String imagemUrl;

    @Size(max=500)
    private String descricao;

    @ManyToMany(mappedBy = "petsInteressados")
    @JsonIgnoreProperties("petsInteressados")
    private List<Usuario> listaInteressados;


    public Pet(String nome, Usuario doador, Especie especie, String raca, Sexo sexo, LocalDate dataNascimento, String descricao) {
        this.nome = nome;
        this.doador = doador;
        this.especie = especie;
        this.raca = raca;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.descricao = descricao;
    }

    public Pet(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Usuario> getListaInteressados() {
        return listaInteressados;
    }

    public void setListaInteressados(List<Usuario> listaInteressados) {
        this.listaInteressados = listaInteressados;
    }

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

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
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
