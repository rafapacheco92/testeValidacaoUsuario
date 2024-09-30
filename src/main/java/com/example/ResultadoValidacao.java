package com.example;

public class ResultadoValidacao {
    private boolean sucesso;
    private String mensagemErro;

    // Construtor para sucesso
    public ResultadoValidacao(boolean sucesso) {
        this.sucesso = sucesso;
        this.mensagemErro = null;
    }

    // Construtor para erro
    public ResultadoValidacao(boolean sucesso, String mensagemErro) {
        this.sucesso = sucesso;
        this.mensagemErro = mensagemErro;
    }

    public boolean isSucesso() {
        return sucesso;
    }

    public String getMensagemErro() {
        return mensagemErro;
    }
}
