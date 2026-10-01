package com.example.integracao.factory;

public class EnvioPush implements IEnvio {

    public String enviar() {
        return "Notificação push enviada";
    }

    public String cancelar() {
        return "Notificação push cancelada";
    }
}