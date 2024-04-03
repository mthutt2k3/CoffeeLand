/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Slider;

import java.sql.PreparedStatement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.DBContext;

/**
 *
 * @author Admin
 */
public class SliderDAO extends DBContext {

    public SliderStatus getSliderStatusByID(int statusID) {
        String sql = "select * from Status where statusId=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, statusID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                SliderStatus sliderst = new SliderStatus(rs.getInt("statusId"), rs.getString("statusName"));
                return sliderst;
            }
        } catch (Exception e) {

        }
        return null;
    }

    public List<Slider> getAllSliderActive() {
        String sql = "SELECT * FROM Slider WHERE status = 1 ORDER BY [order] ASC";
        List<Slider> listSlider = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Slider slider = new Slider();
                slider.setSliderID(rs.getInt("sliderId"));
                slider.setImage(rs.getString("image"));
                slider.setLink(rs.getString("link"));
                slider.setOrder(rs.getInt("order"));
                SliderStatus sliderSt = getSliderStatusByID(rs.getInt("status"));
                slider.setStatus(sliderSt);
                listSlider.add(slider);
            }
        } catch (Exception e) {

        }
        return listSlider;
    }

    public List<Slider> getAllSlider() {
        String sql = "SELECT * FROM Slider";
        List<Slider> listSlider = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Slider slider = new Slider();
                slider.setSliderID(rs.getInt("sliderId"));
                slider.setImage(rs.getString("image"));
                slider.setLink(rs.getString("link"));
                slider.setOrder(rs.getInt("order"));
                SliderStatus sliderSt = getSliderStatusByID(rs.getInt("status"));
                slider.setStatus(sliderSt);
                listSlider.add(slider);
            }
        } catch (Exception e) {

        }
        return listSlider;
    }

    public void updateSliderStatus(int sliderId, int newStatusId) {
        String sql = "UPDATE Slider SET status = ? WHERE sliderId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, newStatusId);
            st.setInt(2, sliderId);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý hoặc ghi log lỗi ở đây
        }
    }

    public void addNewSlider(Slider slider) {
        String sql = "INSERT INTO [dbo].[Slider]\n"
                + "           ([image]\n"
                + "           ,[order]\n"
                + "           ,[link]\n"
                + "           ,[status])\n"
                + "     VALUES\n"
                + "           (?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, slider.getImage());
            st.setInt(2, slider.getOrder());
            st.setString(3, slider.getLink());
            st.setInt(4, 1);
            int affectedRows = st.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Inserting user failed, no rows affected.");
            }
            try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int sliderId = generatedKeys.getInt(1);
                    slider.setSliderID(sliderId);

                } else {
                    throw new SQLException("Inserting user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateSlider(Slider slider) {
        String sql = "UPDATE [dbo].[Slider] SET image=?, [order]=?, link=? WHERE sliderId=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, slider.getImage());
            st.setInt(2, slider.getOrder());
            st.setString(3, slider.getLink());
            st.setInt(4, slider.getSliderID());

            int rowsUpdated = st.executeUpdate();
            if (rowsUpdated == 0) {
                throw new SQLException("Updating slider failed, no rows affected.");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public List<Integer> getOrderOfSlide(){
        String sql = "SELECT DISTINCT [order] FROM Slider";
        List<Integer> orderOptions = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            List<Integer> existingOrders = new ArrayList<>();
            while (rs.next()) {
                existingOrders.add(rs.getInt("order"));
            }
            for (int i = 1; i <= 100; i++) {
                orderOptions.add(i);
            }
             orderOptions.removeAll(existingOrders);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderOptions;
    }
    
}
