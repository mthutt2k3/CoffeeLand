/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Information;

/**
 *
 * @author acer
 */
public class Informations {
    int informationId;
    String user;
    String description;
    String nameStore;
    String image;
    String address;
    String contactPhone;
    String contactEmail;
    String contactFacebook;

    public Informations() {
    }
    
    public Informations(String image) {
        this.image = image;
    }
    
    public Informations(int informationId, String user, String description, String nameStore, String address, String contactPhone, String contactEmail, String contactFacebook) {
        this.informationId = informationId;
        this.user = user;
        this.description = description;
        this.nameStore = nameStore;
        this.address = address;
        this.contactPhone = contactPhone;
        this.contactEmail = contactEmail;
        this.contactFacebook = contactFacebook;
    }
    

    public Informations(int informationId, String user, String description, String nameStore, String image, String address, String contactPhone, String contactEmail, String contactFacebook) {
        this.informationId = informationId;
        this.user = user;
        this.description = description;
        this.nameStore = nameStore;
        this.image = image;
        this.address = address;
        this.contactPhone = contactPhone;
        this.contactEmail = contactEmail;
        this.contactFacebook = contactFacebook;
    }

    public int getInformationId() {
        return informationId;
    }

    public void setInformationId(int informationId) {
        this.informationId = informationId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNameStore() {
        return nameStore;
    }

    public void setNameStore(String nameStore) {
        this.nameStore = nameStore;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactFacebook() {
        return contactFacebook;
    }

    public void setContactFacebook(String contactFacebook) {
        this.contactFacebook = contactFacebook;
    }

    
    
}
