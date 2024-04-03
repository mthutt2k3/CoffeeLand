/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.News;

/**
 *
 * @author acer
 */
public class News {
    int newsId;
    String userName;
    String title;
    String image;
    String content;
    String postTime;
    String priorityName;

    public News() {
    }
    public News(int newsId,String image) {
        this.newsId = newsId;
        this.image = image;
    }
    public News(String userName, String title, String image, String content, String priorityName) {
        this.userName = userName;
        this.title = title;
        this.image = image;
        this.content = content;
        this.priorityName = priorityName;
    }
//    public News(int newsId, String userName, String title, String image, String content, String priorityName) {
//        this.newsId = newsId;
//        this.userName = userName;
//        this.title = title;
//        this.image = image;
//        this.content = content;
//        this.priorityName = priorityName;
//    }
    public News(int newsId, String userName, String title, String content, String priorityName) {
        this.newsId = newsId;
        this.userName = userName;
        this.title = title;
        this.content = content;
        this.priorityName = priorityName;
    }
    public News(int newsId, String userName, String title, String image, String content, String postTime, String priorityName) {
        this.newsId = newsId;
        this.userName = userName;
        this.title = title;
        this.image = image;
        this.content = content;
        this.postTime = postTime;
        this.priorityName = priorityName;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public String getPriorityName() {
        return priorityName;
    }

    public void setPriorityName(String priorityName) {
        this.priorityName = priorityName;
    }

    
    
}
