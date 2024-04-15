package com.example.systemDesign.controller;

import com.example.systemDesign.dto.BorrowingRecordDto;
import com.example.systemDesign.model.BorrowingRecord;
import com.example.systemDesign.service.borrowingRecordService.BorrowingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/borrowing-records")
public class BorrowingRecordController {

    @Autowired
    private BorrowingRecordService borrowingRecordService;

    @GetMapping
    public ResponseEntity<List<BorrowingRecord>> getAllBorrowingRecords() {
        List<BorrowingRecord> borrowingRecords = borrowingRecordService.getAllBorrowingRecords();
        return ResponseEntity.ok(borrowingRecords);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BorrowingRecord> getBorrowingRecordById(@PathVariable Long id) {
        BorrowingRecord borrowingRecord = borrowingRecordService.getBorrowingRecordById(id);
        if (borrowingRecord != null) {
            return ResponseEntity.ok(borrowingRecord);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<BorrowingRecord> addBorrowingRecord(@Valid @RequestBody BorrowingRecordDto borrowingRecord) {
        BorrowingRecord addedBorrowingRecord = borrowingRecordService.addBorrowingRecord(borrowingRecord);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedBorrowingRecord);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BorrowingRecord> updateBorrowingRecord(@PathVariable Long id, @Valid @RequestBody BorrowingRecordDto borrowingRecord) {
        BorrowingRecord updatedBorrowingRecord = borrowingRecordService.updateBorrowingRecord(id, borrowingRecord);
        if (updatedBorrowingRecord != null) {
            return ResponseEntity.ok(updatedBorrowingRecord);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBorrowingRecord(@PathVariable Long id) {
        borrowingRecordService.deleteBorrowingRecord(id);
        return ResponseEntity.noContent().build();
    }
}
