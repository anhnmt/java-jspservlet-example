package com.example.session11.dao;

import com.example.session11.entity.Category;

import java.util.List;

public interface ICategory {
    List<Category> findAll();

    List<Category> findByName(String name);

    boolean create(Category c);

    Category findByID(Integer id);

    boolean update(Category c);

    boolean delete(Integer id);
}
