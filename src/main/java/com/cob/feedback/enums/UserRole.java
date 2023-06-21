package com.cob.feedback.enums;

public enum UserRole {
    ADMIN("ADMIN"),
    USER("USER");
    public final String label;

    private UserRole(String label) {
        this.label = label;
    }
}
