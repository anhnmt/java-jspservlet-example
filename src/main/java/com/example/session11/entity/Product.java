package com.example.session11.entity;

import java.time.LocalDate;

public class Product {
    private Integer ProId;
    private String ProName;
    private Integer CateId;
    private String Producer;
    private Integer YearMaking;
    private LocalDate ExpireDate;
    private Integer Quantity;
    private Double Price;
    private Integer Status;

    public Product() {
    }

    public Product(Integer proId, String proName, Integer cateId, String producer, Integer yearMaking, LocalDate expireDate, Integer quantity, Double price, Integer status) {
        ProId = proId;
        ProName = proName;
        CateId = cateId;
        Producer = producer;
        YearMaking = yearMaking;
        ExpireDate = expireDate;
        Quantity = quantity;
        Price = price;
        Status = status;
    }

    public Product(String proName, Integer cateId, String producer, Integer yearMaking, LocalDate expireDate, Integer quantity, Double price, Integer status) {
        ProName = proName;
        CateId = cateId;
        Producer = producer;
        YearMaking = yearMaking;
        ExpireDate = expireDate;
        Quantity = quantity;
        Price = price;
        Status = status;
    }

    public Integer getProId() {
        return ProId;
    }

    public void setProId(Integer proId) {
        ProId = proId;
    }

    public String getProName() {
        return ProName;
    }

    public void setProName(String proName) {
        ProName = proName;
    }

    public Integer getCateId() {
        return CateId;
    }

    public void setCateId(Integer cateId) {
        CateId = cateId;
    }

    public String getProducer() {
        return Producer;
    }

    public void setProducer(String producer) {
        Producer = producer;
    }

    public Integer getYearMaking() {
        return YearMaking;
    }

    public void setYearMaking(Integer yearMaking) {
        YearMaking = yearMaking;
    }

    public LocalDate getExpireDate() {
        return ExpireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        ExpireDate = expireDate;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer status) {
        Status = status;
    }
}
