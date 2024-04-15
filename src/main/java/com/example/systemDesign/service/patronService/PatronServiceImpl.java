package com.example.systemDesign.service.patronService;

import com.example.systemDesign.model.Patron;
import com.example.systemDesign.repository.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatronServiceImpl implements PatronService {

    @Autowired
    private PatronRepository patronRepository;

    @Override
    public List<Patron> getAllPatrons() {
        return patronRepository.findAll();
    }

    @Override
    public Patron getPatronById(Long id) {
        Optional<Patron> optionalPatron = patronRepository.findById(id);
        return optionalPatron.orElse(null);
    }

    @Override
    public Patron addPatron(Patron patron) {
        patron.setId(0L);
        return patronRepository.save(patron);
    }

    @Override
    public Patron updatePatron(Long id, Patron patron) {
        if (patronRepository.existsById(id)) {
            patron.setId(id);
            return patronRepository.save(patron);
        } else {
            return null;
        }
    }

    @Override
    public void deletePatron(Long id) {
        patronRepository.deleteById(id);
    }
}
