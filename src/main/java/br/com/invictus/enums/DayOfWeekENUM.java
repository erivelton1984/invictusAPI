package br.com.invictus.enums;

public enum DayOfWeekENUM {

    SEGUNDA(1, "Segunda-feira"),
    TERCA(2, "Terça-feira"),
    QUARTA(3, "Quarta-feira"),
    QUINTA(4, "Quinta-feira"),
    SEXTA(5, "Sexta-feira"),
    SABADO(6, "Sábado"),
    DOMINGO(7, "Domingo");

    private final Integer id;
    private final String description;

    DayOfWeekENUM(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public static DayOfWeekENUM getById(Integer id) {
        for (DayOfWeekENUM dayOfWeekENUM : DayOfWeekENUM.values()) {
            if (dayOfWeekENUM.getId().equals(id)) {
                return dayOfWeekENUM;
            }
        }
        throw new IllegalArgumentException("Day of week not found with id: " + id);
    }

    @Override
    public String toString() {
        return description;
    }

}
