/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Client;

import java.util.List;

public class Size {
    public int sizeID;
    public String sizeName;
    public List<ProductSize> price;

    public Size() {
    }

    public Size(int sizeID, String sizeName, List<ProductSize> price) {
        this.sizeID = sizeID;
        this.sizeName = sizeName;
        this.price = price;
    }

    

    

    public int getSizeID() {
        return sizeID;
    }

    public void setSizeID(int sizeID) {
        this.sizeID = sizeID;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public List<ProductSize> getPrice() {
        return price;
    }

    public void setPrice(List<ProductSize> price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Size{" + "sizeID=" + sizeID + ", sizeName=" + sizeName + ", price=" + price + '}';
    }


    
}
