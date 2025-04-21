package br.com.invictus.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum DegreeENUM {

    SEM_GRAU(0, "Sem Grau"),
    UM(1, "Um"),
    DOIS(2, "Dois"),
    TRES(3, "Três"),
    QUATRO(4, "Quatro"),
    CINCO(5, "Cinco"),
    SEIS(6, "Seis"),
    SETE(7, "Sete"),
    OITO(8, "Oito"),
    NOVE(9, "Nove"),
    DEZ(10, "Dez");

    private final Integer id;
    private final String description;

    DegreeENUM(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public static String getById(Integer id) {
        for (DegreeENUM degreeENUM : DegreeENUM.values()) {
            if (degreeENUM.getId().equals(id)) {
                return degreeENUM.getDescription();
            }
        }
        throw new IllegalArgumentException("Degree not found with id: " + id);
    }

    @Override
    public String toString() {
        return description;
    }

    @JsonCreator
    public static DegreeENUM fromValue(String value) {
        if (value == null) return null;

        // Tenta bater com o nome exato do enum (SEM_GRAU, UM, etc.)
        for (DegreeENUM degree : DegreeENUM.values()) {
            if (degree.name().equalsIgnoreCase(value.replace(" ", "_"))) {
                return degree;
            }
        }

        // Ou tenta bater com a descrição (Sem Grau, Dois, Três...)
        for (DegreeENUM degree : DegreeENUM.values()) {
            if (degree.getDescription().equalsIgnoreCase(value)) {
                return degree;
            }
        }

        throw new IllegalArgumentException("Valor inválido para DegreeENUM: " + value);
    }

    @JsonValue
    public String toJson() {
        return name(); // ou getDescription(), se preferir retornar "Sem Grau", "Um", etc.
    }
}
