/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.NewsClient;

public class NewsCmt {
    private int commentId;
    private int newsId;
    private int userId;
    private String message;
    private String status;
    private String time;

    public NewsCmt() {
    }

    public NewsCmt(int commentId, int newsId, int userId, String message, String status, String time) {
        this.commentId = commentId;
        this.newsId = newsId;
        this.userId = userId;
        this.message = message;
        this.status = status;
        this.time = time;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "NewsComment{" + "commentId=" + commentId + ", newsId=" + newsId + ", userId=" + userId + ", message=" + message + ", status=" + status + ", time=" + time + '}';
    }

    
    
}
