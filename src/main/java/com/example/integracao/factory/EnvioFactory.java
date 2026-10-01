package com.example.integracao.factory;

public class EnvioFactory {

    public static IEnvio obterEnvio(String tipo) {
        Class classe = null;
        Object objeto = null;
        try {
            classe = Class.forName("com.example.integracao.factory.Envio" + tipo);
            objeto = classe.newInstance();
        } catch (Exception ex) {
            throw new IllegalArgumentException("Tipo de envio inexistente");
        }
        if (!(objeto instanceof IEnvio)) {
            throw new IllegalArgumentException("Tipo de envio inválido");
        }
        return (IEnvio) objeto;
    }
}