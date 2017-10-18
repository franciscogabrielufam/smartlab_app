package com.example.newpc.laboratory.dominio;

/**
 * Created by Francisco Gabriel on 17/10/2017.
 */

public class Dados {

    private int id;
    private double temperatura;
    private double umidade;
    private double luminosidade;

    public double getLuminosidade() {
        return luminosidade;
    }
    public void setLuminosidade(double luminosidade) {
        this.luminosidade = luminosidade;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public double getUmidade() {
        return umidade;
    }

    public void setUmidade(double umidade) {
        this.umidade = umidade;
    }
}
