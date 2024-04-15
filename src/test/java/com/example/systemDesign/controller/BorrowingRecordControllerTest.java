package com.example.systemDesign.controller;

import com.example.systemDesign.controller.BorrowingRecordController;
import com.example.systemDesign.dto.BorrowingRecordDto;
import com.example.systemDesign.model.BorrowingRecord;
import com.example.systemDesign.service.borrowingRecordService.BorrowingRecordService;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class BorrowingRecordControllerTest {

    @Mock
    private BorrowingRecordService borrowingRecordService;

    @InjectMocks
    private BorrowingRecordController borrowingRecordController;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    public void testGetAllBorrowingRecords() {
        List<BorrowingRecord> borrowingRecords = new ArrayList<>();
        borrowingRecords.add(new BorrowingRecord(1L, null, null, LocalDate.now(), null));
        borrowingRecords.add(new BorrowingRecord(2L, null, null, LocalDate.now(), null));

        when(borrowingRecordService.getAllBorrowingRecords()).thenReturn(borrowingRecords);

        ResponseEntity<List<BorrowingRecord>> response = borrowingRecordController.getAllBorrowingRecords();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void testGetBorrowingRecordById() {
        long borrowingRecordId = 1L;
        BorrowingRecord borrowingRecord = new BorrowingRecord(borrowingRecordId, null, null, LocalDate.now(), null);

        when(borrowingRecordService.getBorrowingRecordById(borrowingRecordId)).thenReturn(borrowingRecord);

        ResponseEntity<BorrowingRecord> response = borrowingRecordController.getBorrowingRecordById(borrowingRecordId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(borrowingRecord, response.getBody());
    }

    @Test
    public void testAddBorrowingRecord() {
        BorrowingRecord borrowingRecordToAdd = new BorrowingRecord(null, null, null, LocalDate.now(), null);
        BorrowingRecord addedBorrowingRecord = new BorrowingRecord(1L, null, null,  LocalDate.now(), null);

        when(borrowingRecordService.addBorrowingRecord(any())).thenReturn(addedBorrowingRecord);

        ResponseEntity<BorrowingRecord> response = borrowingRecordController.addBorrowingRecord(new BorrowingRecordDto());

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(addedBorrowingRecord.getId(), response.getBody().getId());
    }

    @Test
    public void testUpdateBorrowingRecord() {
        long borrowingRecordId = 1L;
        BorrowingRecordDto borrowingRecordToUpdate = new BorrowingRecordDto(borrowingRecordId, 1L, 1L, LocalDate.now(), null);
        BorrowingRecord updatedBorrowingRecord = new BorrowingRecord(1L, null, null,  LocalDate.now(), null);

        when(borrowingRecordService.updateBorrowingRecord(borrowingRecordId, borrowingRecordToUpdate)).thenReturn(updatedBorrowingRecord);

        ResponseEntity<BorrowingRecord> response = borrowingRecordController.updateBorrowingRecord(borrowingRecordId, borrowingRecordToUpdate);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedBorrowingRecord, response.getBody());
    }

    @Test
    public void testDeleteBorrowingRecord() {
        long borrowingRecordId = 1L;
        ResponseEntity<Void> response = borrowingRecordController.deleteBorrowingRecord(borrowingRecordId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(borrowingRecordService, times(1)).deleteBorrowingRecord(borrowingRecordId);
    }
}
