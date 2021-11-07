package br.com.delove.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2, max = 100)
    private String nome;

    @NotNull
    @Email
    @Size(min = 6, max = 50)
    @Column(unique = true)
    private String email;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;

    @NotNull
    @Size(min = 12, max = 14)
    private String contato;

    @NotNull
    @Size(min = 11, max = 14)
    @Column(unique = true)
    private String documento;

    @NotNull
    @Size(min = 2, max = 50)
    private String cidade;

    @JsonIgnore
    @OneToMany(mappedBy = "doador")
    private List<Pet> petsEmDoacao;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "listaInteressados", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "pet_id"))
    private List<Pet> petsInteressados;

    public Usuario() {
    }

    public Usuario(String nome, String email, String senha, String contato, String cidade, String documento) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.contato = contato;
        this.cidade = cidade;
        this.documento = documento;
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

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public List<Pet> getPetsInteressados() {
        return petsInteressados;
    }

    public void setPetsInteressados(List<Pet> petsInteressados) {
        this.petsInteressados = petsInteressados;
    }

    public List<Pet> getPetsEmDoacao() {
        return petsEmDoacao;
    }

    public void setPetsEmDoacao(List<Pet> petsEmDoacao) {
        this.petsEmDoacao = petsEmDoacao;
    }
}
