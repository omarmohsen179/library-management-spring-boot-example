package com.example.systemDesign.service.patronService;

import com.example.systemDesign.model.Patron;

import java.util.List;

public interface PatronService {
    List<Patron> getAllPatrons();
    Patron getPatronById(Long id);
    Patron addPatron(Patron patron);
    Patron updatePatron(Long id, Patron patron);
    void deletePatron(Long id);
}
