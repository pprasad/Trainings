package org.zensar.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CarDto {
    private int id;
    private String vendor;
    private String model;
    private String color;
    private double price;
    private LocalDate orderDate;
    private String name;
    private String email;
    private String mobile;
}
