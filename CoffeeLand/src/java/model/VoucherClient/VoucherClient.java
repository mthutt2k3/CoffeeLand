/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.VoucherClient;

/**
 *
 * @author Admin
 */
public class VoucherClient {
    
    int voucherId;
    String voucherName;
    String voucherCode;
    String voucherImage;
    String discount;
    String startedDate;
    String expirationDate;
    String condition;
    String user;

    public VoucherClient(int voucherId, String voucherName, String voucherCode, String voucherImage, String discount, String startedDate, String expirationDate, String condition, String user) {
        this.voucherId = voucherId;
        this.voucherName = voucherName;
        this.voucherCode = voucherCode;
        this.voucherImage = voucherImage;
        this.discount = discount;
        this.startedDate = startedDate;
        this.expirationDate = expirationDate;
        this.condition = condition;
        this.user = user;
    }
    

    public VoucherClient() {
    }
    public VoucherClient( String voucherName, String voucherCode, String voucherImage, String discount, String startedDate, String expirationDate, String condition, String user) {
        this.voucherName = voucherName;
        this.voucherCode = voucherCode;
        this.voucherImage = voucherImage;
        this.discount = discount;
        this.startedDate = startedDate;
        this.expirationDate = expirationDate;
        this.condition = condition;
        this.user = user;
    }
    

    

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
    

    public int getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(int voucherId) {
        this.voucherId = voucherId;
    }

    public String getVoucherName() {
        return voucherName;
    }

    public void setVoucherName(String voucherName) {
        this.voucherName = voucherName;
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

    public String getStartedDate() {
        return startedDate;
    }

    public void setStartedDate(String startedDate) {
        this.startedDate = startedDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    
    
}
