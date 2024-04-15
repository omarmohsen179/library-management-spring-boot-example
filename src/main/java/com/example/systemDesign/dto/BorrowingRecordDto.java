package com.example.systemDesign.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BorrowingRecordDto {

    private Long id;
    private Long bookId;
    private Long patronId;

    private LocalDate borrowDate;

    private LocalDate returnDate;
}
