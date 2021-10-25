package br.com.delove.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Inheritance
@DiscriminatorColumn(name="tipo", discriminatorType = DiscriminatorType.STRING, length = 2)
public abstract class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min=2, max=100)
    private String nome;

    @NotNull
    @Email
    @Size(min=6, max=50)
    private String email;

    @NotNull
    @Size(min=6, max=20)
    private String senha;

    @NotNull
    @Size(min=12, max=14)
    private String contato;

    @NotNull
    @Size(min=2, max=50)
    private String cidade;

    @OneToMany(mappedBy = "tutor")
    private List<Pet> petsDoados;

    @ManyToMany
    @JoinTable(name = "filaInteressados",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "pet_id"))
    private List<Pet> petsInteressados;

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

    public List<Pet> getPetsInteressados() {
        return petsInteressados;
    }

    public void setPetsInteressados(List<Pet> petsInteressados) {
        this.petsInteressados = petsInteressados;
    }
}
