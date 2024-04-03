/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Client;

import java.util.List;

public class Cart {
    public User userId;
    public Product productId;
    public Size sizeId;
    public int quatity;
    public double price;
    public double totalPrice;

    public Cart() {
    }

    public Cart(User userId, Product productId, Size sizeId, int quatity, double price, double totalPrice) {
        this.userId = userId;
        this.productId = productId;
        this.sizeId = sizeId;
        this.quatity = quatity;
        this.price = price;
        this.totalPrice = totalPrice;
    }

    


    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public Size getSizeId() {
        return sizeId;
    }

    public void setSizeId(Size sizeId) {
        this.sizeId = sizeId;
    }

    public int getQuatity() {
        return quatity;
    }

    public void setQuatity(int quatity) {
        this.quatity = quatity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
}
