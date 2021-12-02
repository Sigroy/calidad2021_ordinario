package com.anahuac.calidad.app;

public class Calculadora {
	
    private double ultimoResultado;

    public double suma(double primerSumando, double segundoSumando) {
        return ultimoResultado = primerSumando + segundoSumando;
    }

    public double resta(double minuendo, double sustraendo) {
        return ultimoResultado = minuendo - sustraendo;
    }

    public double multiplicacion(double primerFactor, double segundoFactor) {
        return ultimoResultado = primerFactor * segundoFactor;
    }

    public double division(double dividendo, double divisor) {
        ultimoResultado = dividendo / divisor;
        return ultimoResultado = dividendo / divisor;
    }

    public int divisionEntera(int dividendo, int divisor) {
        return dividendo / divisor;
    }

    public double getUltimoResultado() {
        return ultimoResultado;
    }
}