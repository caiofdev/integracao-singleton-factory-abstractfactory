package com.example.integracao.abstractfactory;

public class DocumentoMedioFactory implements DocumentoFactory {

    public Declaracao criarDeclaracao() {
        return new DeclaracaoMedio();
    }

    public Historico criarHistorico() {
        return new HistoricoMedio();
    }
}