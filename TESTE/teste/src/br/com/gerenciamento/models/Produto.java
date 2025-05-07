package br.com.gerenciamento.models;

import java.util.Date;

public class Produto {

    private int id;
    private String nome;
    private Date dataCadastro;
    private String estoque;
    private String valor;
    
    public Produto(){}

    public Produto(String nome, String estoque, String valor){
        setNome(nome);
        setEstoque(estoque);
        setValor(valor);

    }


    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Date getDataCadastro() {
        return dataCadastro;
    }
    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
    public String getEstoque() {
        return estoque;
    }
    public void setEstoque(String estoque) {
        this.estoque = estoque;
    }
    public String getValor() {
        return valor;
    }
    public void setValor(String valor) {
        this.valor = valor;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    
}
