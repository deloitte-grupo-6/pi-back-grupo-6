package br.com.delove.model;

public class UsuarioPF extends Usuario{

    private String cpf;

    public UsuarioPF(String nome, String email, String senha, String contato, String cidade, String cpf) {
        super(nome, email, senha, contato, cidade);
        this.cpf = cpf;
    }



    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
