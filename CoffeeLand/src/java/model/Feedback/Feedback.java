/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Feedback;

/**
 *
 * @author Admin
 */
public class Feedback {

    private String feedbackId;
    private String productName;
    private String username;
    private String ratedStar;
    private String image;
    private String message;
    private String statusId;
    private String feedbackTime;

    public Feedback() {
    }

    public Feedback(String feedbackId, String productName, String username, String ratedStar, String image, String message, String statusId, String feedbackTime) {
        this.feedbackId = feedbackId;
        this.productName = productName;
        this.username = username;
        this.ratedStar = ratedStar;
        this.image = image;
        this.message = message;
        this.statusId = statusId;
        this.feedbackTime = feedbackTime;
    }

    Feedback(String productName) {
        this.productName = productName;
    }

    public String getRatedStar() {
        return ratedStar;
    }

    // Các getters và setters cho các trường của Feedback
    public void setRatedStar(String ratedStar) {
        this.ratedStar = ratedStar;
    }

    public String getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getFeedbackTime() {
        return feedbackTime;
    }

    public void setFeedbackTime(String feedbackTime) {
        this.feedbackTime = feedbackTime;
    }

    @Override
    public String toString() {
        return "Feedback{" + "feedbackId=" + feedbackId + ", productName=" + productName + ", username=" + username + ", ratedStar=" + ratedStar + ", image=" + image + ", message=" + message + ", statusId=" + statusId + ", feedbackTime=" + feedbackTime + '}';
    }

}
