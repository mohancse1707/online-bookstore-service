package com.online.bookstore.controller;

import com.online.bookstore.service.BookService;
import com.online.bookstore.service.dto.BookDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/book-list")
    public ResponseEntity<List<BookDTO>> getAllBook()  {
        List<BookDTO> bookList = bookService.getAllBooks();
        return new ResponseEntity<List<BookDTO>>(bookList, HttpStatus.OK);
    }

    @PostMapping("/add-new-book")
    public ResponseEntity<BookResponse> addNewBook(@Valid @RequestBody BookDTO bookDTO) {
        bookService.addNewBook(bookDTO);
        BookResponse response = new BookResponse();
        response.setMessage("New Book Added Successfully!");
        return new ResponseEntity<BookResponse>(response, HttpStatus.OK);
    }

    @PutMapping("/update-book")
    public ResponseEntity<BookResponse> updateBook(@RequestParam(value = "id") Long id, @Valid @RequestBody BookDTO bookDTO) {
        bookService.updateBook(id, bookDTO);
        BookResponse response = new BookResponse();
        response.setMessage("Book Updated Successfully!");
        return new ResponseEntity<BookResponse>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete-book")
    public ResponseEntity<BookResponse> deleteBookById(@RequestParam(value = "id") Long id) {
        bookService.deleteBookById(id);
        BookResponse response = new BookResponse();
        response.setMessage("Book Deleted Successfully!");
        return new ResponseEntity<BookResponse>(response, HttpStatus.OK);
    }

    @GetMapping("/checkout")
    public int getTotalBookPrice(@RequestParam("books") List<Long> books, @RequestParam(value = "promotionCode", required = false) String promotionCode) {
        return bookService.getTotalBookPrice(books, promotionCode);
    }
}