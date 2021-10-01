package com.online.bookstore.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book")
@DynamicUpdate
public class Book {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private String author;
    private String type;
    private Integer price;
    private String isbn;
}
