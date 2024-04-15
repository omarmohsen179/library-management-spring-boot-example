package com.example.systemDesign.service;

import com.example.systemDesign.dto.BorrowingRecordDto;
import com.example.systemDesign.model.BorrowingRecord;
import com.example.systemDesign.repository.BorrowingRecordRepository;
import com.example.systemDesign.service.borrowingRecordService.BorrowingRecordServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class BorrowingRecordServiceTest {

    @Mock
    private BorrowingRecordRepository borrowingRecordRepository;
    @Mock
    private ModelMapper modelMapper;


    @InjectMocks
    private BorrowingRecordServiceImpl borrowingRecordService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllBorrowingRecords() {
        List<BorrowingRecord> borrowingRecords = new ArrayList<>();
        borrowingRecords.add(new BorrowingRecord(1L, null, null, LocalDate.now(), null));
        borrowingRecords.add(new BorrowingRecord(2L, null, null, LocalDate.now(), null));

        when(borrowingRecordRepository.findAll()).thenReturn(borrowingRecords);

        List<BorrowingRecord> result = borrowingRecordService.getAllBorrowingRecords();

        assertEquals(2, result.size());
        assertEquals(borrowingRecords, result);
    }

    @Test
    public void testGetBorrowingRecordById() {
        long borrowingRecordId = 1L;
        BorrowingRecord borrowingRecord = new BorrowingRecord(borrowingRecordId, null, null, LocalDate.now(), null);

        when(borrowingRecordRepository.findById(borrowingRecordId)).thenReturn(Optional.of(borrowingRecord));

        BorrowingRecord result = borrowingRecordService.getBorrowingRecordById(borrowingRecordId);

        assertEquals(borrowingRecord, result);
    }

    @Test
    public void testAddBorrowingRecord() {
        BorrowingRecordDto borrowingRecordToAdd = new BorrowingRecordDto(1L, 1L, 1L, LocalDate.now(), null);
        BorrowingRecord addedBorrowingRecord = new BorrowingRecord(1L, null, null, LocalDate.now(), null);
        when(modelMapper.map(borrowingRecordToAdd, BorrowingRecord.class)).thenReturn(addedBorrowingRecord);
        when(borrowingRecordRepository.save(any())).thenReturn(addedBorrowingRecord);
        BorrowingRecord result = borrowingRecordService.addBorrowingRecord(borrowingRecordToAdd);
        assertEquals(addedBorrowingRecord, result);
    }

    @Test
    public void testUpdateBorrowingRecord() {
        long borrowingRecordId = 1L;
        BorrowingRecordDto borrowingRecordToUpdate = new BorrowingRecordDto(borrowingRecordId, 1L, 1L, LocalDate.now(), LocalDate.now());
        BorrowingRecord res = new BorrowingRecord(1L, null, null, LocalDate.now(), LocalDate.now());
        when(borrowingRecordRepository.existsById(borrowingRecordId)).thenReturn(true);
        when(borrowingRecordRepository.save(any())).thenReturn(res);
        when(modelMapper.map(borrowingRecordToUpdate, BorrowingRecord.class)).thenReturn(new BorrowingRecord(1L, null, null, LocalDate.now(), null));
        BorrowingRecord result = borrowingRecordService.updateBorrowingRecord(borrowingRecordId, borrowingRecordToUpdate);

        assertEquals(res, result);
    }

    @Test
    public void testDeleteBorrowingRecord() {
        long borrowingRecordId = 1L;
        borrowingRecordService.deleteBorrowingRecord(borrowingRecordId);
        verify(borrowingRecordRepository, times(1)).deleteById(borrowingRecordId);
    }
}
