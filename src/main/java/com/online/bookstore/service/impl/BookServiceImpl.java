package com.online.bookstore.service.impl;

import com.online.bookstore.constant.BookType;
import com.online.bookstore.entity.Book;
import com.online.bookstore.repository.BookRepository;
import com.online.bookstore.service.BookService;
import com.online.bookstore.service.dto.BookDTO;
import com.online.bookstore.service.exception.BookServiceException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    public BookServiceImpl(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addNewBook(BookDTO bookDto) {
        if(bookDto.getId() != null){
            Optional<Book> bookById = bookRepository.findById(bookDto.getId());
            bookById.ifPresent(book -> {
                throw new BookServiceException("Book with same id present.");
            });
        }
        Book book = modelMapper.map(bookDto, Book.class);
        bookRepository.save(book);
    }

    @Override
    public void updateBook(Long id, BookDTO bookDto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookServiceException("Book with id:" + id + " is not found."));
        book.setAuthor(bookDto.getAuthor());
        book.setDescription(bookDto.getDescription());
        book.setIsbn(bookDto.getIsbn());
        book.setName(bookDto.getName());
        book.setPrice(bookDto.getPrice());
        book.setType(bookDto.getType());
        bookRepository.save(book);
    }

    @Override
    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return convertToBookDTOList(books);
    }

    @Override
    public BookDTO getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookServiceException("Book with id:" + id + " is not found."));
        return modelMapper.map(book, BookDTO.class);
    }

    @Override
    public BookDTO getBookByIsbn(String isbn) {
        Book book = bookRepository.findOneByIsbn(isbn)
                .orElseThrow(() -> new BookServiceException("Book with ISBN:" + isbn + " is not found."));
        return modelMapper.map(book, BookDTO.class);
    }

    @Override
    public void deleteBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookServiceException("Book with id:" + id + " is not found."));
        bookRepository.deleteById(book.getId());
    }

    @Override
    public Integer getTotalBookPrice(List<Long> books, String promotionCode) {
        AtomicReference<Integer> finalPrice = new AtomicReference<>(0);
        books.forEach(id -> {
            Optional<Book> ids = bookRepository.findById(id);
            ids.ifPresent(book -> {
                AtomicReference<Integer> discount = new AtomicReference<>(0);
                if(null != promotionCode && promotionCode.length() > 0){
                    discount.updateAndGet(v -> v + (book.getPrice() * BookType.discountPercentage(book.getType())) / 100);
                    finalPrice.updateAndGet(v -> v + (book.getPrice() - discount.get()));
                    log.info("*************Discount Block*****************");
                    log.info("Book Name {}", book.getName());
                    log.info("Book Price {}", book.getPrice());
                    log.info("Book Type {}", book.getType());
                    log.info("Book Type Promotion Percentage {}", BookType.discountPercentage(book.getType()));
                    log.info("Discount Amount {}", discount.get());
                    log.info("finalPrice after discount {}", (book.getPrice() - discount.get()));
                    log.info("******************************");
                } else {
                    log.info("******************************");
                    log.info("Book Name {}", book.getName());
                    log.info("Book Price {}", book.getPrice());
                    log.info("Book Type {}", book.getType());
                    log.info("******************************");
                    finalPrice.updateAndGet(v -> v + (book.getPrice()));
                }
            });
        });
        log.info("finalPriceAfterDiscount {}", finalPrice.get());
        return finalPrice.get();
    }

    private List<BookDTO> convertToBookDTOList(List<Book> books) {
        return books.stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }
}
