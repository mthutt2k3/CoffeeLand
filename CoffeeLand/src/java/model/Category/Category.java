/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Category;

/**
 *
 * @author acer
 */
public class Category {
    
    int categoryId;
    String categoryName;
    String countProduct;

    public Category() {
    }
    public Category(String categoryName) {
        this.categoryName = categoryName;
    }
    public Category(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }
    public Category(String categoryName, String countProduct) {
        this.categoryName = categoryName;
        this.countProduct = countProduct;
    }

    public Category(int categoryId, String categoryName, String countProduct) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.countProduct = countProduct;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCountProduct() {
        return countProduct;
    }

    public void setCountProduct(String countProduct) {
        this.countProduct = countProduct;
    }
    
}
