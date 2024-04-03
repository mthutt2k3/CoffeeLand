/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.FeedbackClient;
import java.sql.*;

/**
 *
 * @author Admin
 */
public class Feedback {
    int feedbackid,productid,userid,ratedStar;
    String image,message;
    int statusId;
    Timestamp feedbackTime;

    public Feedback(int feedbackid, int productid, int userid, int ratedStar, String image, String message, int statusId, Timestamp feedbackTime) {
        this.feedbackid = feedbackid;
        this.productid = productid;
        this.userid = userid;
        this.ratedStar = ratedStar;
        this.image = image;
        this.message = message;
        this.statusId = statusId;
        this.feedbackTime = feedbackTime;
    }

    public int getFeedbackid() {
        return feedbackid;
    }

    public void setFeedbackid(int feedbackid) {
        this.feedbackid = feedbackid;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getRatedStar() {
        return ratedStar;
    }

    public void setRatedStar(int ratedStar) {
        this.ratedStar = ratedStar;
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

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public Timestamp getFeedbackTime() {
        return feedbackTime;
    }

    public void setFeedbackTime(Timestamp feedbackTime) {
        this.feedbackTime = feedbackTime;
    }

    public Feedback() {
    }

    @Override
    public String toString() {
        return "Feedback{" + "feedbackid=" + feedbackid + ", productid=" + productid + ", userid=" + userid + ", ratedStar=" + ratedStar + ", image=" + image + ", message=" + message + ", statusId=" + statusId + ", feedbackTime=" + feedbackTime + '}';
    }
    
}
