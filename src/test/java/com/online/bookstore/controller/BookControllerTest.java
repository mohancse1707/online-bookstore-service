package com.online.bookstore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.online.bookstore.constant.BookType;
import com.online.bookstore.service.BookService;
import com.online.bookstore.service.dto.BookDTO;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.Arrays;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.doNothing;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    private final Long id = 2134L;
    private final String name = "name";
    private final String author = "author";
    private final String isbn = "1234";
    private final String description = "description";
    private final Integer price = 250;
    private final String type = BookType.FICTION.name();
    private static ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;


    @Test
    public void getAllBookTest() throws Exception {

        List<BookDTO> bookDTOList = Arrays.asList(getBookDTO());

        Mockito.when(bookService.getAllBooks()).thenReturn(bookDTOList);

        mockMvc.perform(get("/api/book-list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].name", Matchers.is("name")));
    }

    @Test
    public void addNewBookTest() throws Exception {
        doNothing().when(bookService).addNewBook(ArgumentMatchers.any());
        String json = mapper.writeValueAsString(getBookDTO());
        mockMvc.perform(post("/api/add-new-book").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

    }

    @Test
    public void updateBookTest() throws Exception {
        doNothing().when(bookService)
                .updateBook(ArgumentMatchers.any(), ArgumentMatchers.any());
        String json = mapper.writeValueAsString(getBookDTO());
        mockMvc.perform(put("/api/update-book").param("id", "1").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

    }

    @Test
    public void deleteBookTest() throws Exception {
        doNothing().when(bookService)
                .deleteBookById(ArgumentMatchers.any());
        mockMvc.perform(delete("/api/delete-book").param("id", "1").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

    }

    @Test
    public void checkoutBookTest() throws Exception {
        Mockito.when(bookService.getTotalBookPrice(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(price);

        mockMvc.perform(get("/api/checkout").param("books", "1")
                .param("promotionCode", "OCT2021-01")
                .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

    }


    private BookDTO getBookDTO(){
        BookDTO bookDTO = BookDTO.builder()
                .name(name)
                .author(author)
                .description(description)
                .isbn(isbn)
                .price(price)
                .type(type).build();
        return bookDTO;
    }

}
