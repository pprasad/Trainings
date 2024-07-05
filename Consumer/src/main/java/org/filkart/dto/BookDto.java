package org.filkart.dto;

import java.io.Serializable;
import java.time.LocalDate;

//@Data
public class BookDto implements Serializable {
    private int id;
    private String title;
    private String author;
    private double price;
    private LocalDate orderDate;
    private String name;
    private String email;
    private String mobile;

    public BookDto(){}

    public BookDto(int id, String title, String author){
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public BookDto(int id, String title, String author,double price,LocalDate orderDate, String name, String email, String mobile) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.orderDate = orderDate;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", orderDate=" + orderDate +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}
