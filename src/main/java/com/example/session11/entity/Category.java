package com.example.session11.entity;

public class Category {
    private Integer CateId;
    private String CateName;
    private String Description;
    private Integer Status;

    public Category() {
    }

    public Category(Integer cateId, String cateName, String description, Integer status) {
        CateId = cateId;
        CateName = cateName;
        Description = description;
        Status = status;
    }

    public Category(String cateName, String description, Integer status) {
        CateName = cateName;
        Description = description;
        Status = status;
    }

    public Integer getCateId() {
        return CateId;
    }

    public void setCateId(Integer cateId) {
        CateId = cateId;
    }

    public String getCateName() {
        return CateName;
    }

    public void setCateName(String cateName) {
        CateName = cateName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer status) {
        Status = status;
    }
}
