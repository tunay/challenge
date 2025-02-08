package org.challenge.enums;

public enum CancelReason {
    PERDA("PERDA"),
    ROUBO("ROUBO"),
    DANO("DANO");

    private final String description;

    // Constructor
    CancelReason(String description) {
        this.description = description;
    }

    // Getter method to retrieve the description
    public String getDescription() {
        return description;
    }
}
