package com.imc.servicio;

public class UtilIMC {
    public static double calcularIMC(double masaKg, double estaturaM) {
        if (estaturaM <= 0) throw new IllegalArgumentException("La estatura debe ser mayor que 0");
        return Math.round((masaKg / (estaturaM * estaturaM)) * 100.0) / 100.0;
    }
    public static String clasificacion(double imc) {
        if (imc < 18.5) return "Bajo peso";
        if (imc < 25.0) return "Normal";
        if (imc < 30.0) return "Sobrepeso";
        return "Obesidad";
    }
}
