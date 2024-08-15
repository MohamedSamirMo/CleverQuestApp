package com.example.quizscoreapp.Models;

public class CategoryModel {
    private String categoryName, categoryImage , Key;
    int setNumber;

    public CategoryModel(String categoryName, String categoryImage, String key, int setNumber) {
        this.categoryName = categoryName;
        this.categoryImage = categoryImage;
        this.Key = key;
        this.setNumber = setNumber;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }

    public void setKey(String key) {
        Key = key;
    }

    public void setSetNumber(int setNumber) {
        this.setNumber = setNumber;
    }

    public CategoryModel() {
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public String getKey() {
        return Key;
    }

    public int getSetNumber() {
        return setNumber;
    }
}
