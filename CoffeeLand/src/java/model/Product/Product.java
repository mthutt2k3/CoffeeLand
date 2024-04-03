/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Product;

/**
 *
 * @author acer
 */
public class Product{
    //productId, userName, productName, description, categoryName, statusName, thumbnail
    int productId;
    String userName;
    String productName;
    String description;
    String categoryName;
    String statusName;
    String thumbnail;
    
    //ProductSize.productId, productName, Size.sizeName, price
    String sizeName;
    String price;
    String ratedStar;

    public Product() {
    }
    
    public Product(int productId,String sizeName, String price) {
        this.productId = productId;
        this.sizeName = sizeName;
        this.price = price;
    }
    
    public Product(String sizeName, String price) {
        this.sizeName = sizeName;
        this.price = price;
    }

    public Product(int productId, String productName, String thumbnail, String price, String ratedStar) {
        this.productId = productId;
        this.productName = productName;
        this.thumbnail = thumbnail;
        this.price = price;
        this.ratedStar = ratedStar;
    }
    
    public Product(String userName, String productName, String description, String categoryName, String statusName, String thumbnail) {
        this.userName = userName;
        this.productName = productName;
        this.description = description;
        this.categoryName = categoryName;
        this.statusName = statusName;
        this.thumbnail = thumbnail;
    }
    
    
    public Product(int productId, String userName, String productName, String description, String categoryName, String statusName, String thumbnail) {
        this.productId = productId;
        this.userName = userName;
        this.productName = productName;
        this.description = description;
        this.categoryName = categoryName;
        this.statusName = statusName;
        this.thumbnail = thumbnail;
    }
    public Product(int productId, String userName, String productName, String description, String categoryName, String statusName, String thumbnail, String price) {
        this.productId = productId;
        this.userName = userName;
        this.productName = productName;
        this.description = description;
        this.categoryName = categoryName;
        this.statusName = statusName;
        this.thumbnail = thumbnail;
        this.price = price;
    }
    public Product(int productId, String userName, String productName, String description, String categoryName, String statusName, String thumbnail, String sizeName, String price) {
        this.productId = productId;
        this.userName = userName;
        this.productName = productName;
        this.description = description;
        this.categoryName = categoryName;
        this.statusName = statusName;
        this.thumbnail = thumbnail;
        this.sizeName = sizeName;
        this.price = price;
    }

    public Product(int productId, String userName, String productName, String description, String categoryName, String statusName, String thumbnail, String sizeName, String price, String ratedStar) {
        this.productId = productId;
        this.userName = userName;
        this.productName = productName;
        this.description = description;
        this.categoryName = categoryName;
        this.statusName = statusName;
        this.thumbnail = thumbnail;
        this.sizeName = sizeName;
        this.price = price;
        this.ratedStar = ratedStar;
    }
    

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRatedStar() {
        return ratedStar;
    }

    public void setRatedStar(String ratedStar) {
        this.ratedStar = ratedStar;
    }

    @Override
    public String toString() {
        return this.userName; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    
}
