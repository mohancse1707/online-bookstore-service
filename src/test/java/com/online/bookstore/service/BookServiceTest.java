package com.online.bookstore.service;

import com.online.bookstore.entity.Book;
import com.online.bookstore.repository.BookRepository;
import com.online.bookstore.service.dto.BookDTO;
import com.online.bookstore.service.impl.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookServiceTest {

    private final Long id = 1234L;
    private final int price = 250;
    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private ModelMapper modelMapper;

    @Autowired
    private BookService bookService;



    @Test
    public void testAddNewBook() {
        //Arrange
        BookDTO bookDto = mock(BookDTO.class);
        Book book = mock(Book.class);
        Mockito.when(bookDto.getId()).thenReturn(id);
        Mockito.when(bookRepository.findById(id)).thenReturn(Optional.empty());
        Mockito.when(modelMapper.map(bookDto, Book.class)).thenReturn(book);

        //Act
        bookService.addNewBook(bookDto);

        //Verify
        Mockito.verify(bookRepository).save(book);
    }

    @Test
    public void testGetAllBooks() {
        //Arrange
        Book book = mock(Book.class);
        List<Book> bookList = new ArrayList<>();
        bookList.add(book);

        BookDTO bookDto = mock(BookDTO.class);
        List<BookDTO> bookDtoList = new ArrayList<>();
        bookDtoList.add(bookDto);
        Mockito.when(bookRepository.findAll()).thenReturn(bookList);
        Mockito.when(modelMapper.map(book, BookDTO.class)).thenReturn(bookDto);

        //Act
        List<BookDTO> actualBookDto = bookService.getAllBooks();

        //Assert
        assertEquals(bookDtoList, actualBookDto);
    }

    @Test
    public void testUpdateBook() {
        //Arrange
        BookDTO bookDto = mock(BookDTO.class);
        Book book = mock(Book.class);
        Mockito.when(modelMapper.map(bookDto, Book.class)).thenReturn(book);
        Mockito.when(bookDto.getId()).thenReturn(id);
        Book bookFromRepo = mock(Book.class);
        Mockito.when(bookRepository.findById(id)).thenReturn(Optional.of(bookFromRepo));
        Mockito.when(bookFromRepo.getPrice()).thenReturn(price);

        //Act
        bookService.updateBook(id, bookDto);

        //Assert
        Mockito.verify(bookRepository).save(book);
    }

    @Test
    public void testDeleteBook() {
        BookDTO bookDto = mock(BookDTO.class);
        Book book = mock(Book.class);
        Mockito.when(bookDto.getId()).thenReturn(id);
        Book bookFromRepo = Book.builder().id(1234L).name("test").build();
        Mockito.when(bookRepository.findById(id)).thenReturn(Optional.of(bookFromRepo));

        //Act
        bookService.deleteBookById(id);

        //Assert
        Mockito.verify(bookRepository).deleteById(id);
    }
}
