package br.com.invictus.enums;

public enum BeltENUM {

    BRANCA(1, "Branca"),
    CINZA(2, "Cinza"),
    AMARELA(3, "Amarela"),
    LARANJA(4, "Laranja"),
    VERDE(5, "Verde"),
    AZUL(6, "Azul"),
    ROXA(7, "Roxa"),
    MARROM(8, "Marrom"),
    PRETA(9, "Preta"),
    CORAL_BRANCA_VERMELHO(10, "Coral Branca Vermelha"),
    CORAL_PRETO_VERMELHO(11, "Coral Preto Vermelho"),
    VERMELHA(12, "Vermelha");

    private final Integer id;
    private final String description;

    BeltENUM(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public static BeltENUM getById(Integer id) {
        for (BeltENUM beltENUM : BeltENUM.values()) {
            if (beltENUM.getId().equals(id)) {
                return beltENUM;
            }
        }
        throw new IllegalArgumentException("Belt not found with id: " + id);
    }

    @Override
    public String toString() {
        return description;
    }

    public static BeltENUM fromDescription(String description) {
        for (BeltENUM belt : BeltENUM.values()) {
            if (belt.getDescription().equalsIgnoreCase(description)) {
                return belt;
            }
        }
        throw new IllegalArgumentException("Unknown belt description: " + description);
    }
}