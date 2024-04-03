/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.OrderDetail;

/**
 *
 * @author Admin
 */
public class OrderDetailEntity {
    String orderDetailId, orderId, productId, sizeId, quantity, price;

    public OrderDetailEntity() {
    }

    public OrderDetailEntity(String orderId, String productId, String sizeId, String quantity, String price) {
        this.orderId = orderId;
        this.productId = productId;
        this.sizeId = sizeId;
        this.quantity = quantity;
        this.price = price;
    }
    
    public OrderDetailEntity(String orderDetailId, String orderId, String productId, String sizeId, String quantity, String price) {
        this.orderDetailId = orderDetailId;
        this.orderId = orderId;
        this.productId = productId;
        this.sizeId = sizeId;
        this.quantity = quantity;
        this.price = price;
    }

    public String getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(String orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSizeId() {
        return sizeId;
    }

    public void setSizeId(String sizeId) {
        this.sizeId = sizeId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderDetail{" + "orderDetailId=" + orderDetailId + ", orderId=" + orderId + ", productId=" + productId + ", sizeId=" + sizeId + ", quantity=" + quantity + ", price=" + price + '}';
    }
    
}
