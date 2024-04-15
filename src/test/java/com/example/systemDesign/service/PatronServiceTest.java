package com.example.systemDesign.service;


import com.example.systemDesign.model.Patron;
import com.example.systemDesign.repository.PatronRepository;
import com.example.systemDesign.service.patronService.PatronServiceImpl;
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


public class PatronServiceTest {

    @Mock
    private PatronRepository patronRepository;

    @InjectMocks
    private PatronServiceImpl patronService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllPatrons() {
        List<Patron> patrons = new ArrayList<>();
        patrons.add(new Patron(1L, "John Doe", "john@example.com"));
        patrons.add(new Patron(2L, "Jane Smith", "jane@example.com"));

        when(patronRepository.findAll()).thenReturn(patrons);

        List<Patron> result = patronService.getAllPatrons();

        assertEquals(2, result.size());
        assertEquals(patrons, result);
    }

    @Test
    public void testGetPatronById() {
        long patronId = 1L;
        Patron patron = new Patron(patronId, "John Doe", "john@example.com");

        when(patronRepository.findById(patronId)).thenReturn(Optional.of(patron));

        Patron result = patronService.getPatronById(patronId);

        assertEquals(patron, result);
    }

    @Test
    public void testAddPatron() {
        Patron patronToAdd = new Patron(null, "John Doe", "john@example.com");
        Patron addedPatron = new Patron(1L, "John Doe", "john@example.com");

        when(patronRepository.save(patronToAdd)).thenReturn(addedPatron);

        Patron result = patronService.addPatron(patronToAdd);

        assertEquals(addedPatron, result);
    }

    @Test
    public void testUpdatePatron() {
        long patronId = 1L;
        Patron patronToUpdate = new Patron(patronId, "John Doe", "john@example.com");

        when(patronRepository.existsById(patronId)).thenReturn(true);
        when(patronRepository.save(patronToUpdate)).thenReturn(patronToUpdate);

        Patron result = patronService.updatePatron(patronId, patronToUpdate);

        assertEquals(patronToUpdate, result);
    }

    @Test
    public void testDeletePatron() {
        long patronId = 1L;

        patronService.deletePatron(patronId);

        verify(patronRepository, times(1)).deleteById(patronId);
    }
}
