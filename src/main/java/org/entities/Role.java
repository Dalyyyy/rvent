package org.entities;

public enum Role {
    ADMIN,
    CLIENT,
    ORGANIZER;
    @Override
    public String toString() {
        // Customize this as needed
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}
