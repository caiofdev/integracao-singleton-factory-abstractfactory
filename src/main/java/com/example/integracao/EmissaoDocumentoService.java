package com.example.integracao;

import com.example.integracao.singleton.Parametros;

import com.example.integracao.abstractfactory.DocumentoFactory;
import com.example.integracao.abstractfactory.DocumentoFundamentalFactory;
import com.example.integracao.abstractfactory.DocumentoMedioFactory;
import com.example.integracao.abstractfactory.Declaracao;
import com.example.integracao.abstractfactory.Historico;

import com.example.integracao.factory.IEnvio;
import com.example.integracao.factory.EnvioFactory;

public class EmissaoDocumentoService {

    /**
     * Emite a Declaração e o Histórico corretos para o nível de ensino informado
     * (Abstract Factory), identifica o aluno usando o contexto global guardado no
     * Singleton (Parametros) e dispara a notificação através do tipo de envio
     * escolhido em tempo de execução (Factory Method).
     */
    public ResultadoEmissao emitir(String nivelEnsino, String tipoNotificacao) {

        Parametros parametros = Parametros.getInstance();
        String usuario = parametros.getUsuarioLogado();
        String escola = parametros.getNomeEscola();

        DocumentoFactory documentoFactory = obterDocumentoFactory(nivelEnsino);
        Declaracao declaracao = documentoFactory.criarDeclaracao();
        Historico historico = documentoFactory.criarHistorico();

        String textoDeclaracao = escola + " - " + declaracao.emitir() + " (aluno: " + usuario + ")";
        String textoHistorico = escola + " - " + historico.emitir() + " (aluno: " + usuario + ")";

        IEnvio envio = EnvioFactory.obterEnvio(tipoNotificacao);
        String confirmacao = envio.enviar();

        return new ResultadoEmissao(textoDeclaracao, textoHistorico, confirmacao);
    }

    private DocumentoFactory obterDocumentoFactory(String nivelEnsino) {
        if ("Fundamental".equalsIgnoreCase(nivelEnsino)) {
            return new DocumentoFundamentalFactory();
        }
        if ("Medio".equalsIgnoreCase(nivelEnsino)) {
            return new DocumentoMedioFactory();
        }
        throw new IllegalArgumentException("Nível de ensino inexistente");
    }
}