package br.com.invictus.util;

public class CategoryUtils {

    public static String determineAgeCategory(int age) {
        if (age >= 4 && age <= 5) return "Mirim";
        if (age >= 6 && age <= 7) return "Infantil A";
        if (age >= 8 && age <= 9) return "Infantil B";
        if (age >= 10 && age <= 11) return "Infantil C";
        if (age >= 12 && age <= 13) return "Infanto-Juvenil A";
        if (age >= 14 && age <= 15) return "Infanto-Juvenil B";
        if (age >= 16 && age <= 17) return "Juvenil";
        if (age >= 18 && age <= 29) return "Adulto";
        if (age >= 30 && age <= 35) return "Master 1";
        if (age >= 36 && age <= 40) return "Master 2";
        if (age >= 41 && age <= 45) return "Master 3";
        if (age >= 46 && age <= 50) return "Master 4";
        if (age >= 51 && age <= 55) return "Master 5";
        if (age >= 56 && age <= 60) return "Master 6";
        if (age > 60) return "Master 7";
        return "Sem Categoria";
    }

    public static String determineWeightCategory(String weightStr, String gender, int age) {
        try {
            double weight = Double.parseDouble(weightStr);

            boolean isAdult = (age >= 18 && age <= 29);

            if ("MASCULINO".equalsIgnoreCase(gender)) {
                if (isAdult) {
                    if (weight <= 57.5) return "Galo";
                    if (weight <= 64.0) return "Pluma";
                    if (weight <= 70.0) return "Pena";
                    if (weight <= 76.0) return "Leve";
                    if (weight <= 82.3) return "Médio";
                    if (weight <= 88.3) return "Meio-Pesado";
                    if (weight <= 94.3) return "Pesado";
                    if (weight <= 100.5) return "Super Pesado";
                    return "Pesadíssimo";
                } else {
                    if (weight <= 53.5) return "Galo";
                    if (weight <= 58.5) return "Pluma";
                    if (weight <= 64.0) return "Pena";
                    if (weight <= 69.0) return "Leve";
                    if (weight <= 74.0) return "Médio";
                    if (weight <= 79.3) return "Meio-Pesado";
                    if (weight <= 84.3) return "Pesado";
                    if (weight <= 89.3) return "Super Pesado";
                    return "Pesadíssimo";
                }
            } else {
                if (isAdult) {
                    if (weight <= 48.5) return "Galo";
                    if (weight <= 53.5) return "Pluma";
                    if (weight <= 58.5) return "Pena";
                    if (weight <= 64.0) return "Leve";
                    if (weight <= 69.0) return "Médio";
                    if (weight <= 74.0) return "Meio-Pesado";
                    if (weight <= 79.3) return "Pesado";
                    return "Pesadíssimo";
                } else {
                    if (weight <= 44.3) return "Galo";
                    if (weight <= 48.5) return "Pluma";
                    if (weight <= 53.5) return "Pena";
                    if (weight <= 58.5) return "Leve";
                    if (weight <= 64.0) return "Médio";
                    if (weight <= 69.0) return "Meio-Pesado";
                    if (weight <= 74.0) return "Pesado";
                    return "Pesadíssimo";
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Erro ao converter peso: " + e.getMessage());
        }
        return "Sem Categoria";
    }
}