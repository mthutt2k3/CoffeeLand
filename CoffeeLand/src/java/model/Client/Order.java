/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Client;

import java.sql.Timestamp;

/**
 *
 * @author dell
 */
public class Order {
    public int OrderID;
    public User userId;
    public Timestamp orderTime;
    public double totalAmount;
    public OrderStatus statusId;
    public String receiverName;
    public String receiverAddress;
    public String receiverPhone;
    public PaymentMethod paymentMethodId;
    public double grandTotal;

    public Order() {
    }

    public Order(int OrderID, User userId, Timestamp orderTime, double totalAmount, OrderStatus statusId, String receiverName, String receiverAddress, String receiverPhone, PaymentMethod paymentMethodId, double grandTotal) {
        this.OrderID = OrderID;
        this.userId = userId;
        this.orderTime = orderTime;
        this.totalAmount = totalAmount;
        this.statusId = statusId;
        this.receiverName = receiverName;
        this.receiverAddress = receiverAddress;
        this.receiverPhone = receiverPhone;
        this.paymentMethodId = paymentMethodId;
        this.grandTotal = grandTotal;
    }

    

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public OrderStatus getStatusId() {
        return statusId;
    }

    public void setStatusId(OrderStatus statusId) {
        this.statusId = statusId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public PaymentMethod getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(PaymentMethod paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    @Override
    public String toString() {
        return "Order{" + "OrderID=" + OrderID + ", userId=" + userId + ", orderTime=" + orderTime + ", totalAmount=" + totalAmount + ", statusId=" + statusId + ", receiverName=" + receiverName + ", receiverAddress=" + receiverAddress + ", receiverPhone=" + receiverPhone + ", paymentMethodId=" + paymentMethodId + ", grandTotal=" + grandTotal + '}';
    }
    
}
