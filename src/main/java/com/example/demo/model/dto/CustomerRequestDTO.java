package com.example.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRequestDTO {
    private String name;
    private String surname;
    private String gsmNumber;
    private Double balance;
    private LocalDate birthDate;
}
