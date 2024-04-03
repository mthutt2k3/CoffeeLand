/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.CartClient;

/**
 *
 * @author Admin
 */
public class CartClient {
    String cartId, userId, productId, name, size, price, quantity, total, thumbnail;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    
    public String getCartId() {
        return cartId;
    }

    public CartClient(String productId, String size, String quantity) {
        this.quantity = quantity;
        this.productId = productId;
        this.size = size;
    }
    
    public CartClient(String cartId, String productId, String name, String size, String price, String quantity, String total, String thumbnail) {
        this.cartId = cartId;
        this.productId = productId;
        this.name = name;
        this.size = size;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
        this.thumbnail = thumbnail;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public CartClient() {
    }

    public CartClient(String cartId, String name, String size, String price, String quantity, String total, String thumbnail) {
        this.cartId = cartId;
        this.name = name;
        this.size = size;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
        this.thumbnail = thumbnail;
    }

    public CartClient(String name, String size, String price, String quantity, String total, String thumbnail) {
        this.name = name;
        this.size = size;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
    
}
