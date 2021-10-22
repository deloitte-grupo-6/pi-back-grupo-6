package br.com.delove.model;

public class UsuarioPJ extends Usuario{

    private String cnpj;

    public UsuarioPJ(String nome, String email, String senha, String contato, String cidade, String cnpj) {
        super(nome, email, senha, contato, cidade);
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
