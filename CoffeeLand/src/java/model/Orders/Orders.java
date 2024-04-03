/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Orders;

/**
 *
 * @author Admin
 */
public class Orders {

    String orderId;
    String orderCode;
    String userId;
    String salerId;
    String orderTime;
    String totalAmount;
    String statusId;
    String receiverName;
    String receiverAddress;
    String receiverPhone;
    String voucherId;
    String discountAmount;
    String grandTotal;

    public Orders() {
    }
    public Orders(String orderId ) {
        this.orderId = orderId;
    }

    public Orders(String orderId, String orderCode, String userId, String salerId, String orderTime, String totalAmount, String statusId, String receiverName, String receiverAddress, String receiverPhone , String voucherId, String discountAmount, String grandTotal) {
        this.orderId = orderId;
        this.orderCode = orderCode;
        this.userId = userId;
        this.salerId = salerId;
        this.orderTime = orderTime;
        this.totalAmount = totalAmount;
        this.statusId = statusId;
        this.receiverName = receiverName;
        this.receiverAddress = receiverAddress;
        this.receiverPhone = receiverPhone;
        this.voucherId = voucherId;
        this.discountAmount = discountAmount;
        this.grandTotal = grandTotal;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSalerId() {
        return salerId;
    }

    public void setSalerId(String salerId) {
        this.salerId = salerId;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
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

    public String getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(String voucherId) {
        this.voucherId = voucherId;
    }
    
    

    public String getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(String discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getGrandTotal() {
        return grandTotal;
    }
    
    public void setGrandTotal(String grandTotal) {
        this.grandTotal = grandTotal;
    }

    @Override
    public String toString() {
        return "Orders{" + "orderId=" + orderId + ", orderCode=" + orderCode + ", userId=" + userId + ", salerId=" + salerId + ", orderTime=" + orderTime + ", totalAmount=" + totalAmount + ", statusId=" + statusId + ", receiverName=" + receiverName + ", receiverAddress=" + receiverAddress + ", receiverPhone=" + receiverPhone  + ", voucherId=" + voucherId + ", discountAmount=" + discountAmount + ", grandTotal=" + grandTotal + '}';
    }

   
    
}
