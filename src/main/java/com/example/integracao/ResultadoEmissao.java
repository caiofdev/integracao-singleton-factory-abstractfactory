package com.example.integracao;

public class ResultadoEmissao {

    private final String declaracao;
    private final String historico;
    private final String confirmacaoEnvio;

    public ResultadoEmissao(String declaracao, String historico, String confirmacaoEnvio) {
        this.declaracao = declaracao;
        this.historico = historico;
        this.confirmacaoEnvio = confirmacaoEnvio;
    }

    public String getDeclaracao() {
        return declaracao;
    }

    public String getHistorico() {
        return historico;
    }

    public String getConfirmacaoEnvio() {
        return confirmacaoEnvio;
    }
}