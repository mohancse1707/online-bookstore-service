package com.online.bookstore.constant;

import java.util.HashMap;
import java.util.Map;

public enum BookType {
    FICTION(15),
    COMIC(0),
    ACTION(10),
    THRILLER(10),
    TECHNOLOGY(0),
    DRAMA(5),
    POETRY(20),
    MEDIA(25),
    OTHERS(30);

    private int discount;
    private static Map<String, Integer> map = new HashMap<>();

    private BookType(int discount) {
        this.discount = discount;
    }

    static {
        for (BookType bookType : BookType.values()) {
            map.put(bookType.name(), bookType.discount);
        }
    }

    public static Integer discountPercentage(String category) {
        return (Integer) map.get(category);
    }

}