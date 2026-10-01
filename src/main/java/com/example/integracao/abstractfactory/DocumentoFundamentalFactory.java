package com.example.integracao.abstractfactory;

public class DocumentoFundamentalFactory implements DocumentoFactory {
	public Declaracao criarDeclaracao() {
		return new DeclaracaoFundamental();
	}

	public Historico criarHistorico() {
		return new HistoricoFundamental();
	}
}
