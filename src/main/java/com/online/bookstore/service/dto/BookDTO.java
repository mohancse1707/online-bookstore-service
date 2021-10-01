package com.online.bookstore.service.dto;

import com.online.bookstore.constant.BookType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private Long id;
    private String name;
    private String description;
    private String author;
    private String type;
    private Integer price;
    private String isbn;
}
