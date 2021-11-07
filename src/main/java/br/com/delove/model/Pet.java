package br.com.delove.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
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
    private Usuario doador;

    @NotNull
    private boolean disponivel;

    @NotNull
    @Size(min = 3, max = 20)
    private String especie;

    @NotNull
    @Size(min = 3, max = 20)
    private String raca;

    @NotNull
    @Size(min = 4, max = 7)
    private String sexo;

    @NotNull
    private LocalDate dataNascimento;

    @Lob
    private String imagemUrl;

    @Size(max = 500)
    private String descricao;

    @JsonIgnore
    @ManyToMany(mappedBy = "petsInteressados")
    @JsonIgnoreProperties("petsInteressados")
    private List<Usuario> listaInteressados;

    public Pet(String nome, Usuario doador, String especie, String raca, String sexo, LocalDate dataNascimento,
            String descricao, String imagemUrl) {
        this.nome = nome;
        this.doador = doador;
        this.especie = especie;
        this.raca = raca;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.descricao = descricao;
        this.imagemUrl = imagemUrl;
    }

    public Pet() {
    }

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

    // public List<Usuario> getFilaInteressados() {
    // return listaInteressados;
    // }

    // public void setFilaInteressados(List<Usuario> filaInteressados) {
    // this.listaInteressados = filaInteressados;
    // }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }
}
