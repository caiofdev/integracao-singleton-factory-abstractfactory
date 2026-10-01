package com.example.integracao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.integracao.singleton.Parametros;

import static org.junit.jupiter.api.Assertions.*;

class EmissaoDocumentoServiceTest {

    private final EmissaoDocumentoService service = new EmissaoDocumentoService();

    @BeforeEach
    public void configurarContextoGlobal() {
        Parametros.getInstance().setNomeEscola("Colégio Horizonte");
        Parametros.getInstance().setUsuarioLogado("Maria Silva");
    }

    @Test
    public void deveEmitirDocumentosDoEnsinoFundamentalENotificarPorEmail() {
        ResultadoEmissao resultado = service.emitir("Fundamental", "Email");

        assertTrue(resultado.getDeclaracao().contains("Colégio Horizonte"));
        assertTrue(resultado.getDeclaracao().contains("Ensino Fundamental"));
        assertTrue(resultado.getDeclaracao().contains("Maria Silva"));

        assertTrue(resultado.getHistorico().contains("Ensino Fundamental"));
        assertEquals("E-mail enviado", resultado.getConfirmacaoEnvio());
    }

    @Test
    public void deveEmitirDocumentosDoEnsinoMedioENotificarPorSms() {
        ResultadoEmissao resultado = service.emitir("Medio", "Sms");

        assertTrue(resultado.getDeclaracao().contains("Ensino Médio"));
        assertTrue(resultado.getHistorico().contains("Ensino Médio"));
        assertEquals("SMS enviado", resultado.getConfirmacaoEnvio());
    }

    @Test
    public void mesmoServicoDeveRefletirMudancaDeUsuarioNoSingletonEntreChamadas() {
        ResultadoEmissao primeiraEmissao = service.emitir("Fundamental", "Push");
        assertTrue(primeiraEmissao.getDeclaracao().contains("Maria Silva"));

        Parametros.getInstance().setUsuarioLogado("João Souza");

        ResultadoEmissao segundaEmissao = service.emitir("Fundamental", "Push");
        assertTrue(segundaEmissao.getDeclaracao().contains("João Souza"));
    }

    @Test
    public void deveLancarExcecaoParaNivelDeEnsinoInexistente() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> service.emitir("Infantil", "Email")
        );
        assertEquals("Nível de ensino inexistente", ex.getMessage());
    }

    @Test
    public void deveLancarExcecaoParaTipoDeNotificacaoInexistente() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> service.emitir("Fundamental", "Carta")
        );
        assertEquals("Tipo de envio inexistente", ex.getMessage());
    }
}