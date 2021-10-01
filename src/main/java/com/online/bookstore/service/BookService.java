package com.online.bookstore.service;

import com.online.bookstore.service.dto.BookDTO;

import java.util.List;

public interface BookService {

    void addNewBook(BookDTO bookDto);
    void updateBook(Long id, BookDTO bookDto);
    List<BookDTO> getAllBooks();
    BookDTO getBookById(Long id);
    BookDTO getBookByIsbn(String isbn);
    void deleteBookById(Long id);
    Integer getTotalBookPrice(List<Long> books, String promotionCode);
}
