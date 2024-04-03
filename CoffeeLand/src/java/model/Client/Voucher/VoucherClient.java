/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Client.Voucher;

/**
 *
 * @author NguyenDucTruong
 */
public class VoucherClient {
    private String voucherId;
    private String userId;
    private String voucherName;
    private String description;
    private String voucherCode;
    private String voucherImage;
    private String discount;
    private String startDate;
    private String expirationDate;
    private String condition;

    public VoucherClient() {
    }

    public VoucherClient(String voucherId, String userId, String voucherName, String description, String voucherCode, String voucherImage, String discount, String startDate, String expirationDate, String condition) {
        this.voucherId = voucherId;
        this.userId = userId;
        this.voucherName = voucherName;
        this.description = description;
        this.voucherCode = voucherCode;
        this.voucherImage = voucherImage;
        this.discount = discount;
        this.startDate = startDate;
        this.expirationDate = expirationDate;
        this.condition = condition;
    }

    public String getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(String voucherId) {
        this.voucherId = voucherId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVoucherName() {
        return voucherName;
    }

    public void setVoucherName(String voucherName) {
        this.voucherName = voucherName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    public String getVoucherImage() {
        return voucherImage;
    }

    public void setVoucherImage(String voucherImage) {
        this.voucherImage = voucherImage;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "VoucherClient{" + "voucherId=" + voucherId + ", userId=" + userId + ", voucherName=" + voucherName + ", description=" + description + ", voucherCode=" + voucherCode + ", voucherImage=" + voucherImage + ", discount=" + discount + ", startDate=" + startDate + ", expirationDate=" + expirationDate + ", condition=" + condition + '}';
    }

    
    
    
    
}
