package com.example.integracao.factory;

public class EnvioEmail implements IEnvio {

    public String enviar() {
        return "E-mail enviado";
    }

    public String cancelar() {
        return "Envio de e-mail cancelado";
    }
}