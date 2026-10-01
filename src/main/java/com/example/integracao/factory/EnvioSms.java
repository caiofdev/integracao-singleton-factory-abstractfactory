package com.example.integracao.factory;

public class EnvioSms implements IEnvio {

    public String enviar() {
        return "SMS enviado";
    }

    public String cancelar() {
        return "Envio de SMS cancelado";
    }
}