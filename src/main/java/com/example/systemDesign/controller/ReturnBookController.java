package com.example.systemDesign.controller;

import com.example.systemDesign.config.ResourceNotFoundException;
import com.example.systemDesign.dto.BorrowingRecordDto;
import com.example.systemDesign.model.BorrowingRecord;
import com.example.systemDesign.service.borrowingRecordService.BorrowingRecordService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class ReturnBookController {

    @Autowired
    private BorrowingRecordService borrowingRecordService;
    @Autowired
    private ModelMapper modelMapper;
    @PutMapping("/api/return/{bookId}/patron/{patronId}")
    public ResponseEntity<String> recordBookReturn(@PathVariable Long bookId, @PathVariable Long patronId) {
        // Retrieve the borrowing record for the given bookId and patronId
        BorrowingRecord temp = borrowingRecordService.getBorrowingRecordByBookIdAndPatronId(bookId, patronId);

        if (temp == null) {
           throw new ResourceNotFoundException("can't find borrowing record");
        }
        var borrowingRecord = modelMapper.map(temp, BorrowingRecordDto.class);
        // Update the return date of the borrowing record
        borrowingRecord.setReturnDate(LocalDate.now());

        // Save the updated borrowing record
        borrowingRecordService.updateBorrowingRecord(borrowingRecord.getId(), borrowingRecord);

        // Return a success response
        return ResponseEntity.status(HttpStatus.OK).body("Book return recorded successfully.");
    }
}