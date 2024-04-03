/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.NewsClient;



/**
 *
 * @author NguyenDucTruong
 */
public class News {

    private int newsId;
    private int userId;
    private String title;
    private String image;
    private String content;
    private String postedTime;
    private int priorityId;

    public News() {
    }

    public News(int newsId, int userId, String title, String img, String content, String postedTime, int priorityId) {
        this.newsId = newsId;
        this.userId = userId;
        this.title = title;
        this.image = img;
        this.content = content;
        this.postedTime = postedTime;
        this.priorityId = priorityId;
    }



   public News(int userId, String image, String title, String postedTime) {
        this.userId = userId;
        this.image = image;
        this.title = title;
        this.postedTime = postedTime;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPostedTime() {
        return postedTime;
    }

    public void setPostedTime(String postedTime) {
        this.postedTime = postedTime;
    }

    public int getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(int priorityId) {
        this.priorityId = priorityId;
    }

    @Override
    public String toString() {
        return "News{" + "newsId=" + newsId + ", userId=" + userId + ", title=" + title + ", image=" + image + ", content=" + content + ", postedTime=" + postedTime + ", priorityId=" + priorityId + '}';
    }



 


    

}
