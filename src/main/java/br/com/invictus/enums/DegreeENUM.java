package br.com.invictus.enums;

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

    public static DegreeENUM fromDescription(String description) {
        for (DegreeENUM degree : DegreeENUM.values()) {
            if (degree.getDescription().equalsIgnoreCase(description)) {
                return degree;
            }
        }
        throw new IllegalArgumentException("Unknown degree description: " + description);
    }
}