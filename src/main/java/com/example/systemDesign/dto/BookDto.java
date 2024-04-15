package com.example.systemDesign.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto {
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    private String author;

    private int publicationYear;

    @NotBlank(message = "ISBN is required")
    private String isbn;
}