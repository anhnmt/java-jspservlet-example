package com.example.session11.dao;

import com.example.session11.entity.Category;
import com.example.session11.entity.Product;

import java.util.List;

public interface IProduct {
    List<Product> findAll();

    List<Product> findByName(String name);

    List<Product> findByIdOrName(String name);

    boolean create(Product c);

    Product findByID(Integer id);

    boolean update(Product c);

    boolean delete(Integer id);

    boolean deleteByCateId(Integer id);
}
