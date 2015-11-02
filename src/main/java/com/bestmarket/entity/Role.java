package com.bestmarket.entity;

import org.codehaus.jackson.annotate.*;

public enum Role {
    ADMIN(1, "ADMIN"),
    USER(2, "USER");

    private int id;
    private String name;

    Role(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId () {
        return id;
    }

    @JsonValue
    public String getName() {
        return name;
    }

    public static Role of(int id) {
        for (Role role : Role.values()) {
            if (role.getId() == id) {
                return role;
            }
        }
        throw new UnsupportedOperationException("Threre is no role with id: " + id);
    }

    @JsonCreator
    public static Role of(String name) {
        for (Role role : Role.values()) {
            if (role.getName().equals(name)) {
                return role;
            }
        }
        throw new UnsupportedOperationException("Threre is no role with name: " + name);
    }
}
