package com.example.systemDesign.service.borrowingRecordService;

import com.example.systemDesign.dto.BorrowingRecordDto;
import com.example.systemDesign.model.Book;
import com.example.systemDesign.model.BorrowingRecord;
import com.example.systemDesign.model.Patron;
import com.example.systemDesign.repository.BorrowingRecordRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BorrowingRecordServiceImpl implements BorrowingRecordService {

    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<BorrowingRecord> getAllBorrowingRecords() {
        return borrowingRecordRepository.findAll();
    }

    @Override
    public BorrowingRecord getBorrowingRecordById(Long id) {
        Optional<BorrowingRecord> optionalBorrowingRecord = borrowingRecordRepository.findById(id);
        return optionalBorrowingRecord.orElse(null);
    }

    @Override
    @Transactional
    public BorrowingRecord addBorrowingRecord(BorrowingRecordDto borrowingRecord) {
        var temp = modelMapper.map(borrowingRecord, BorrowingRecord.class);
        temp.setId(0L);
        temp.setBook(Book.builder().id(borrowingRecord.getBookId()).build());
        temp.setPatron(Patron.builder().id(borrowingRecord.getPatronId()).build());
        return borrowingRecordRepository.save(temp);
    }

    @Override
    @Transactional
    public BorrowingRecord updateBorrowingRecord(Long id, BorrowingRecordDto borrowingRecord) {
        var temp = modelMapper.map(borrowingRecord, BorrowingRecord.class);
        if (borrowingRecordRepository.existsById(id)) {
            temp.setId(id);
            temp.setBook(Book.builder().id(borrowingRecord.getBookId()).build());
            temp.setPatron(Patron.builder().id(borrowingRecord.getPatronId()).build());
            return borrowingRecordRepository.save(temp);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public void deleteBorrowingRecord(Long id) {
        borrowingRecordRepository.deleteById(id);
    }
    @Override
    public BorrowingRecord getBorrowingRecordByBookIdAndPatronId(Long bookId, Long patronId) {
        return borrowingRecordRepository.findByBookIdAndPatronId(bookId, patronId);
    }
}