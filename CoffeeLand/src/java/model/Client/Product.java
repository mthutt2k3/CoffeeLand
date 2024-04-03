/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Client;

import java.util.List;

/**
 *
 * @author Admin
 */
public class Product {

    /*
    [productId] [int] IDENTITY(1,1) NOT NULL,
	[userId] [int] NOT NULL,
	[productName] [nvarchar](255) NOT NULL,
	[description] [nvarchar](max) NOT NULL,
	[categoryId] [int] NOT NULL,
	[statusId] [int] NOT NULL,*/
    public int productID;
    public String productName;
    public String description;
    public Category categoryID;
    public Status statusID;
    public List<ProductImage> productImg;
    public List<Size> sizeName;


    public Product(int productID, String productName, String description, Category categoryID, Status statusID, List<ProductImage> productImg, List<Size> sizeName) {
        this.productID = productID;
        this.productName = productName;
        this.description = description;
        this.categoryID = categoryID;
        this.statusID = statusID;
        this.productImg = productImg;
        this.sizeName = sizeName;

    }

    public Product() {
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
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


    public Category getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Category categoryID) {
        this.categoryID = categoryID;
    }

    public Status getStatusID() {
        return statusID;
    }

    public void setStatusID(Status statusID) {
        this.statusID = statusID;
    }

    public List<ProductImage> getProductImg() {
        return productImg;
    }

    public void setProductImg(List<ProductImage> productImg) {
        this.productImg = productImg;
    }

    public List<Size> getSizeName() {
        return sizeName;
    }

    public void setSizeName(List<Size> sizeName) {
        this.sizeName = sizeName;
    }

    @Override
    public String toString() {
        return "Product{" + "productID=" + productID + ", productName=" + productName + ", description=" + description + ", categoryID=" + categoryID + ", statusID=" + statusID + ", productImg=" + productImg + ", sizeName=" + sizeName + '}';
    }



    
}
