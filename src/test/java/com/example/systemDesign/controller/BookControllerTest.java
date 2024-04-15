package com.example.systemDesign.controller;

import com.example.systemDesign.model.Book;
import com.example.systemDesign.service.bookService.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllBooks() {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1L, "Book 1", "Author 1", 2022, "ISBN-1234"));
        books.add(new Book(2L, "Book 2", "Author 2", 2023, "ISBN-5678"));

        when(bookService.getAllBooks()).thenReturn(books);

        ResponseEntity<List<Book>> response = bookController.getAllBooks();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void testGetBookById() {
        long bookId = 1L;
        Book book = new Book(bookId, "Book 1", "Author 1", 2022, "ISBN-1234");

        when(bookService.getBookById(bookId)).thenReturn(book);

        ResponseEntity<Book> response = bookController.getBookById(bookId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(book, response.getBody());
    }

    @Test
    public void testAddBook() {
        Book bookToAdd = new Book(null, "New Book", "New Author", 2024, "ISBN-5678");
        Book addedBook = new Book(1L, "New Book", "New Author", 2024, "ISBN-5678");

        when(bookService.addBook(bookToAdd)).thenReturn(addedBook);

        ResponseEntity<Book> response = bookController.addBook(bookToAdd);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(addedBook.getId(), response.getBody().getId());
    }

    @Test
    public void testUpdateBook() {
        long bookId = 1L;
        Book bookToUpdate = new Book(bookId, "Updated Book", "Updated Author", 2023, "ISBN-9999");
        Book updatedBook = new Book(bookId, "Updated Book", "Updated Author", 2023, "ISBN-9999");

        when(bookService.updateBook(bookId, bookToUpdate)).thenReturn(updatedBook);

        ResponseEntity<Book> response = bookController.updateBook(bookId, bookToUpdate);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedBook, response.getBody());
    }

    @Test
    public void testDeleteBook() {
        long bookId = 1L;
        ResponseEntity<Void> response = bookController.deleteBook(bookId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(bookService, times(1)).deleteBook(bookId);
    }
}