package com.example.systemDesign.service.borrowingRecordService;

import com.example.systemDesign.dto.BorrowingRecordDto;
import com.example.systemDesign.model.BorrowingRecord;

import java.util.List;

public interface BorrowingRecordService {
    List<BorrowingRecord> getAllBorrowingRecords();
    BorrowingRecord getBorrowingRecordById(Long id);
    BorrowingRecord addBorrowingRecord(BorrowingRecordDto borrowingRecord);
    BorrowingRecord updateBorrowingRecord(Long id, BorrowingRecordDto borrowingRecord);
    void deleteBorrowingRecord(Long id);
    BorrowingRecord getBorrowingRecordByBookIdAndPatronId(Long bookId, Long patronId);
}
