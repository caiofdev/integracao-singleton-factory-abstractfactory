package com.example.integracao.abstractfactory;

public interface DocumentoFactory {
    Declaracao criarDeclaracao();
    Historico criarHistorico();
}