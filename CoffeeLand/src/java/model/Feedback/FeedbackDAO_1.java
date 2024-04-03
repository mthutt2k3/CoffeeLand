/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Feedback;

import java.sql.PreparedStatement;
import java.sql.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.MyDAO;
import model.Slider.Slider;
import model.Slider.SliderStatus;

/**
 *
 * @author Admin
 */
public class FeedbackDAO_1 extends MyDAO {

    public void addFeedback(Feedback_1 fb) {
        String sql = "INSERT INTO [dbo].[Feedbacks]\n"
                + "           ([productId]\n"
                + "           ,[userId]\n"
                + "           ,[ratedStar]\n"
                + "           ,[image]\n"
                + "           ,[message]\n"
                + "           ,[statusId]\n"
                + "           ,[feedbackTime])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, fb.getProductid());
            st.setInt(2, fb.getUserid());
            st.setInt(3, fb.getRatedStar());
            st.setString(4, fb.getImage());
            st.setString(5, fb.getMessage());
            st.setInt(6, 1);
            st.setTimestamp(7, fb.getFeedbackTime());
            int affectedRows = st.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Inserting user failed, no rows affected.");
            }
            try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int feedbackId = generatedKeys.getInt(1);
                    fb.setFeedbackid(feedbackId);

                } else {
                    throw new SQLException("Inserting user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<Feedback_1> getAllFeedbackByUIDPID(int userId, int productId) {
        String sql = "SELECT * FROM Feedbacks WHERE userId = ? AND productId = ? AND statusId=1";
        List<Feedback_1> listFeedback = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userId);
            st.setInt(2, productId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Feedback_1 feedback = new Feedback_1();
                feedback.setFeedbackTime(rs.getTimestamp("feedbackTime"));
                feedback.setFeedbackid(rs.getInt("feedbackId"));
                feedback.setImage(rs.getString("image"));
                feedback.setProductid(rs.getInt("productId"));
                feedback.setRatedStar(rs.getInt("ratedStar"));
                feedback.setUserid(rs.getInt("userId"));
                feedback.setMessage(rs.getString("message"));
                feedback.setStatusId(rs.getInt("statusId"));
                listFeedback.add(feedback);
            }
        } catch (Exception e) {

        }
        return listFeedback;
    }

    public void editFeedback(int userId, int productId, int rate, String image, String message, Timestamp feedbackTime) {
        String sql = "UPDATE [dbo].[Feedbacks]\n"
                + "   SET [ratedStar] = ?,[image] =?,[message] = ?,[feedbackTime] = ?\n"
                + " WHERE userId=? and productId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, rate);
            st.setString(2, image);
            st.setString(3, message);
            st.setTimestamp(4, feedbackTime);
            st.setInt(5, userId);
            st.setInt(6, productId);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public void deleteFeedback(int userId, int productId) {
        String sql = "UPDATE [dbo].[Feedbacks]\n"
                + "   SET [statusId] = 2\n"
                + " WHERE userId=? and productId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, userId);
            st.setInt(2, productId);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public List<Feedback_1> getAllFeedbackPID(int productId){
        String sql = "SELECT * FROM Feedbacks WHERE statusId=1 and productId=?";
        List<Feedback_1> listFeedback = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, productId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Feedback_1 feedback = new Feedback_1();
                feedback.setFeedbackTime(rs.getTimestamp("feedbackTime"));
                feedback.setFeedbackid(rs.getInt("feedbackId"));
                feedback.setImage(rs.getString("image"));
                feedback.setProductid(rs.getInt("productId"));
                feedback.setRatedStar(rs.getInt("ratedStar"));
                feedback.setUserid(rs.getInt("userId"));
                feedback.setMessage(rs.getString("message"));
                feedback.setStatusId(rs.getInt("statusId"));
                listFeedback.add(feedback);
            }
        } catch (Exception e) {

        }
        return listFeedback;
    }
    public List<Feedback_1> getAllFeedbackTOP3PID(int productId){
        String sql = "SELECT TOP 3 * FROM Feedbacks WHERE statusId = 1 AND productId = ? ORDER BY feedbackTime DESC;";
        List<Feedback_1> listFeedback = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, productId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Feedback_1 feedback = new Feedback_1();
                feedback.setFeedbackTime(rs.getTimestamp("feedbackTime"));
                feedback.setFeedbackid(rs.getInt("feedbackId"));
                feedback.setImage(rs.getString("image"));
                feedback.setProductid(rs.getInt("productId"));
                feedback.setRatedStar(rs.getInt("ratedStar"));
                feedback.setUserid(rs.getInt("userId"));
                feedback.setMessage(rs.getString("message"));
                feedback.setStatusId(rs.getInt("statusId"));
                listFeedback.add(feedback);
            }
        } catch (Exception e) {

        }
        return listFeedback;
    }
    public String getNameFromUserID(int userId){
        String sql = "Select name from Users where userId=?";
        String name=null;
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                name=rs.getString("name");
            }
        }catch(Exception e){
            
        }
        return name;
    }
    public String getImageByUserId(int userId){
        String sql = "Select avatar from Users where userId=?";
        String image=null;
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                image=rs.getString("avatar");
            }
        }catch(Exception e){
            
        }
        return image;
    }
}
