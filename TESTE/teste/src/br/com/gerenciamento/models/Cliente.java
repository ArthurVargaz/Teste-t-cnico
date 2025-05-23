package br.com.gerenciamento.models;

public class Cliente {

    private int id;
    private String nome;
    private String email;
    private String cpf;
    private String senha;

    public Cliente(){}

    public Cliente(String nome, String email, String cpf, String senha){
        setCpf(cpf);
        setEmail(email);
        setNome(nome);
        setSenha(senha);
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
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
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }


}
