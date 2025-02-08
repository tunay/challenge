package org.challenge.enums;

public enum ActiveCard {
    ATIVO("ATIVO"),
    INATIVO("INATIVO");

    private final String description;

    ActiveCard (String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
