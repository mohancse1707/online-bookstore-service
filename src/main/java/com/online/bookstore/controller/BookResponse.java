package com.online.bookstore.controller;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class BookResponse<T> {

//    private String status;
    private String message;
//    private T data;

}