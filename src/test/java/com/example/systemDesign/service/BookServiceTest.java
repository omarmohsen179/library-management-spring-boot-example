package com.example.systemDesign.service;

import com.example.systemDesign.model.Book;
import com.example.systemDesign.repository.BookRepository;
import com.example.systemDesign.service.bookService.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllBooks() {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1L, "Book 1", "Author 1", 2022, "ISBN-1234"));
        books.add(new Book(2L, "Book 2", "Author 2", 2023, "ISBN-5678"));

        when(bookRepository.findAll()).thenReturn(books);

        List<Book> result = bookService.getAllBooks();

        assertEquals(2, result.size());
        assertEquals(books, result);
    }

    @Test
    public void testGetBookById() {
        long bookId = 1L;
        Book book = new Book(bookId, "Book 1", "Author 1", 2022, "ISBN-1234");

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        Book result = bookService.getBookById(bookId);

        assertEquals(book, result);
    }

    @Test
    public void testAddBook() {
        Book bookToAdd = new Book(null, "Book 3", "Author 3", 2024, "ISBN-9999");
        Book addedBook = new Book(3L, "Book 3", "Author 3", 2024, "ISBN-9999");

        when(bookRepository.save(bookToAdd)).thenReturn(addedBook);

        Book result = bookService.addBook(bookToAdd);

        assertEquals(addedBook, result);
    }

    @Test
    public void testUpdateBook() {
        long bookId = 1L;
        Book bookToUpdate = new Book(bookId, "Updated Book", "Updated Author", 2023, "ISBN-9999");

        when(bookRepository.existsById(bookId)).thenReturn(true);
        when(bookRepository.save(bookToUpdate)).thenReturn(bookToUpdate);

        Book result = bookService.updateBook(bookId, bookToUpdate);

        assertEquals(bookToUpdate, result);
    }

    @Test
    public void testDeleteBook() {
        long bookId = 1L;

        bookService.deleteBook(bookId);

        verify(bookRepository, times(1)).deleteById(bookId);
    }
}
