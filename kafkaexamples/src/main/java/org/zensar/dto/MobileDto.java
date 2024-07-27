package org.zensar.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MobileDto {
    private int id;
    private String vendor;
    private String model;
    private double price;
    private LocalDate orderPlacementDate;
    private String name;
    private String email;
    private String mobile;
}
