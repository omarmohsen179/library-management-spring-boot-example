package com.example.systemDesign.repository;

import com.example.systemDesign.model.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Long> {

    BorrowingRecord findByBookIdAndPatronId(Long bookId, Long patronId);
}
