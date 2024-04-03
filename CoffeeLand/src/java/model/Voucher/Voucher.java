package model.Voucher;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author acer
 */
public class Voucher {
    
    int voucherId;
    String voucherName;
    String voucherCode;
    String voucherImage;
    String discount;
    String description;
    String startedDate;
    String expirationDate;
    String user;
    String condition;
    

    public Voucher() {
    }
    
    
    public Voucher(String voucherName, String voucherCode, String voucherImage, String discount, String description, String startedDate, String expirationDate, String user, String condition) {
        this.voucherName = voucherName;
        this.voucherCode = voucherCode;
        this.voucherImage = voucherImage;
        this.discount = discount;
        this.description = description;
        this.startedDate = startedDate;
        this.expirationDate = expirationDate;
        this.user = user;
        this.condition = condition;
    }

    public Voucher(int voucherId, String voucherName, String voucherCode, String voucherImage, String discount, String description, String startedDate, String expirationDate, String user, String condition) {
        this.voucherId = voucherId;
        this.voucherName = voucherName;
        this.voucherCode = voucherCode;
        this.voucherImage = voucherImage;
        this.discount = discount;
        this.description = description;
        this.startedDate = startedDate;
        this.expirationDate = expirationDate;
        this.user = user;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
            return "Voucher{" + 
            "voucherId=" + voucherId + 
            ", voucherName='" + voucherName + '\'' + 
            ", voucherCode='" + voucherCode + '\'' + 
            ", voucherImage='" + voucherImage + '\'' + 
            ", discount='" + discount + '\'' + 
            ", description='" + description + '\'' + 
            ", startedDate='" + startedDate + '\'' + 
            ", expirationDate='" + expirationDate + '\'' + 
            ", user='" + user + '\'' + 
            ", condition='" + condition + '\'' + 
            "}";
    }
    
}
