/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Slider;

/**
 *
 * @author Admin
 */
public class Slider {
    int sliderID;
    String image;
    int order;
    String link;
    SliderStatus status;

    public Slider(int sliderID, String image, int order, String link, SliderStatus status) {
        this.sliderID = sliderID;
        this.image = image;
        this.order = order;
        this.link = link;
        this.status = status;
    }

    public Slider() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public SliderStatus getStatus() {
        return status;
    }

    public void setStatus(SliderStatus status) {
        this.status = status;
    }

    public int getSliderID() {
        return sliderID;
    }

    public void setSliderID(int sliderID) {
        this.sliderID = sliderID;
    }

    @Override
    public String toString() {
        return "Slider{" + "image=" + image + ", order=" + order + ", link=" + link + ", status=" + status + '}';
    }
    
}
