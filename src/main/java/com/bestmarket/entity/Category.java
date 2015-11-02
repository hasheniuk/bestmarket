package com.bestmarket.entity;

public enum Category {
    ALL(0, "ALL"),
    TABLETS(1, "TABLETS"),
    LAPTOPS(2, "LAPTOPS"),
    DESKTOPS(3, "DESKTOPS"),
    PHONES(4, "PHONES"),
    TV(5, "TV"),
    AUDIO(6, "AUDIO");

    private int id;
    private String name;

    Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static Category of(int id) {
        for (Category category : Category.values()) {
            if (category.getId() == id) {
                return category;
            }
        }
        throw new IllegalArgumentException("There is no product category with id : " + id);
    }

    public static Category of(String name) {
        for (Category category : Category.values()) {
            if (category.getName().equalsIgnoreCase(name)) {
                return category;
            }
        }
        throw new IllegalArgumentException("There is no product category with name : " + name);
    }
}