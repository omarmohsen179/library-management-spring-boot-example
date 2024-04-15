package com.example.systemDesign.controller;


import com.example.systemDesign.model.Patron;
import com.example.systemDesign.service.patronService.PatronService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class PatronControllerTest {

    @Mock
    private PatronService patronService;

    @InjectMocks
    private PatronController patronController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllPatrons() {
        List<Patron> patrons = new ArrayList<>();
        patrons.add(new Patron(1L, "John Doe", "john@example.com"));
        patrons.add(new Patron(2L, "Jane Smith", "jane@example.com"));

        when(patronService.getAllPatrons()).thenReturn(patrons);

        ResponseEntity<List<Patron>> response = patronController.getAllPatrons();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void testGetPatronById() {
        long patronId = 1L;
        Patron patron = new Patron(patronId, "John Doe", "john@example.com");

        when(patronService.getPatronById(patronId)).thenReturn(patron);

        ResponseEntity<Patron> response = patronController.getPatronById(patronId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(patron, response.getBody());
    }

    @Test
    public void testAddPatron() {
        Patron patronToAdd = new Patron(null, "John Doe", "john@example.com");
        Patron addedPatron = new Patron(1L, "John Doe", "john@example.com");

        when(patronService.addPatron(patronToAdd)).thenReturn(addedPatron);

        ResponseEntity<Patron> response = patronController.addPatron(patronToAdd);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(addedPatron.getId(), response.getBody().getId());
    }

    @Test
    public void testUpdatePatron() {
        long patronId = 1L;
        Patron patronToUpdate = new Patron(patronId, "John Doe", "john@example.com");
        Patron updatedPatron = new Patron(patronId, "John Smith", "john@example.com");

        when(patronService.updatePatron(patronId, patronToUpdate)).thenReturn(updatedPatron);

        ResponseEntity<Patron> response = patronController.updatePatron(patronId, patronToUpdate);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedPatron, response.getBody());
    }

    @Test
    public void testDeletePatron() {
        long patronId = 1L;
        ResponseEntity<Void> response = patronController.deletePatron(patronId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(patronService, times(1)).deletePatron(patronId);
    }
}
