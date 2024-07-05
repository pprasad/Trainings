package org.filkart.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class CarDto implements Serializable {
    private int id;
    private String vendor;
    private String model;
    private String color;
    private double price;
    private LocalDate orderDate;
    private String name;
    private String email;
    private String mobile;

    public CarDto() {}

    public CarDto(int id, String vendor, String model, String color, double price, LocalDate orderDate, String name, String email, String mobile) {
        this.id = id;
        this.vendor = vendor;
        this.model = model;
        this.color = color;
        this.price = price;
        this.orderDate = orderDate;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
