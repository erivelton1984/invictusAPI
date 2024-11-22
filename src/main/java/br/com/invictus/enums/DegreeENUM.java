package br.com.invictus.enums;

public enum DegreeENUM {

    UM(1, "1"),
    DOIS(2, "2"),
    TRES(3, "3"),
    QUATRO(4, "4"),
    CINCO(5, "5"),
    SEIS(6, "6"),
    SETE(7, "7"),
    OITO(8, "8"),
    NOVE(9, "9"),
    DEZ(10, "10");

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
}