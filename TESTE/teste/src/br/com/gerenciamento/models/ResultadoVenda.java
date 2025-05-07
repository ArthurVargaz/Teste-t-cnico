package br.com.gerenciamento.models;

public class ResultadoVenda {

    private boolean sucesso;
    private String mensagem;

    public ResultadoVenda(boolean sucesso, String mensagem) {
        this.sucesso = sucesso;
        this.mensagem = mensagem;
    }

    public boolean isSucesso() {
        return sucesso;
    }

    public String getMensagem() {
        return mensagem;
    }
}
