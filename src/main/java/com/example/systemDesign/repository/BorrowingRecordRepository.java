package com.example.systemDesign.repository;

import com.example.systemDesign.model.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Long> {

    BorrowingRecord findByBookIdAndPatronId(Long bookId, Long patronId);

    //JPQL
    @Query("SELECT br FROM BorrowingRecord br WHERE br.book.id = :bookId AND br.patron.id = :patronId")
    BorrowingRecord findByBookIdAndPatronIdJPQL(@Param("bookId") Long bookId, @Param("patronId") Long patronId);
    @Query(value = "SELECT * FROM BorrowingRecord WHERE book_id = :bookId AND patron_id = :patronId", nativeQuery = true)
    BorrowingRecord findByBookIdAndPatronIdNative(@Param("bookId") Long bookId, @Param("patronId") Long patronId);
}
