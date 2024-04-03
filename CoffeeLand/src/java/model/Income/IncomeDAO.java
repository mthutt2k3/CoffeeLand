package model.Income;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Client.Order;
import model.DBContext;
import model.Orders.Orders;
import model.Users.Users;

public class IncomeDAO extends DBContext {

    
    public List<Orders> getOrderBySalerID(int salerID) {
        String sql = "SELECT *\n" + "FROM [dbo].[Orders]\n" + "WHERE [salerId] = ?";
        List<Orders> listOrder = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, salerID);
            ResultSet rs = st.executeQuery();
            listOrder.clear();
            while (rs.next()) {
                String xorderId = rs.getString("orderId");
                String xorderCode = rs.getString("orderCode");
                String xuserId = rs.getString("userId");
                String xsalerId = rs.getString("salerId");
                String xorderTime = rs.getString("orderTime");
                String xtotalAmount = rs.getString("totalAmount");
                String xstatusId = rs.getString("statusId");
                String xreceiverName = rs.getString("receiverName");
                String xreceiverAddress = rs.getString("receiverAddress");
                String xreceiverPhone = rs.getString("receiverPhone");
                String xvoucherId = rs.getString("voucherId");
                String xdiscountAmount = rs.getString("discountAmount");
                String xgrandTotal = rs.getString("grandTotal");
                Orders x = new Orders(xorderId, xorderCode, xuserId, xsalerId, xorderTime, xtotalAmount, xstatusId, xreceiverName, xreceiverAddress, xreceiverPhone, xvoucherId, xdiscountAmount, xgrandTotal);
                listOrder.add(x);
            }
        } catch (SQLException e) {
            // Xử lý ngoại lệ: in ra thông báo lỗi hoặc ghi log
            e.printStackTrace();
        }
        return listOrder;
    }
    public List<Orders> getOrderBySalerIDandDay(int salerID, Date day) {
        String sql = "SELECT *\n"
                + "FROM [dbo].[Orders]\n"
                + "WHERE CAST([orderTime] AS DATE) = ? AND [salerId] = ?";
        List<Orders> listOrder = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDate(1, day);
            st.setInt(2, salerID);
            ResultSet rs = st.executeQuery();
            listOrder.clear();
            while (rs.next()) {
                String xorderId = rs.getString("orderId");
                String xorderCode = rs.getString("orderCode");
                String xuserId = rs.getString("userId");
                String xsalerId = rs.getString("salerId");
                String xorderTime = rs.getString("orderTime");
                String xtotalAmount = rs.getString("totalAmount");
                String xstatusId = rs.getString("statusId");
                String xreceiverName = rs.getString("receiverName");
                String xreceiverAddress = rs.getString("receiverAddress");
                String xreceiverPhone = rs.getString("receiverPhone");
                String xvoucherId = rs.getString("voucherId");
                String xdiscountAmount = rs.getString("discountAmount");
                String xgrandTotal = rs.getString("grandTotal");
                Orders x = new Orders(xorderId, xorderCode, xuserId, xsalerId, xorderTime, xtotalAmount, xstatusId, xreceiverName, xreceiverAddress, xreceiverPhone, xvoucherId, xdiscountAmount, xgrandTotal);
                listOrder.add(x);
            }
        } catch (SQLException e) {
            // Xử lý ngoại lệ: in ra thông báo lỗi hoặc ghi log
            e.printStackTrace();
        }
        return listOrder;
    }
    public List<Orders> getOrderByMonthandYear(int salerID, Date day) {
        String sql = "SELECT * FROM [dbo].[Orders] \n"
                + "WHERE salerId = ? \n"
                + "AND MONTH([orderTime]) = MONTH(?) AND YEAR([orderTime]) = YEAR(?)";
        List<Orders> listOrder = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, salerID);
            st.setDate(2, day);
            st.setDate(3, day);
            ResultSet rs = st.executeQuery();
            listOrder.clear();
            while (rs.next()) {
                String xorderId = rs.getString("orderId");
                String xorderCode = rs.getString("orderCode");
                String xuserId = rs.getString("userId");
                String xsalerId = rs.getString("salerId");
                String xorderTime = rs.getString("orderTime");
                String xtotalAmount = rs.getString("totalAmount");
                String xstatusId = rs.getString("statusId");
                String xreceiverName = rs.getString("receiverName");
                String xreceiverAddress = rs.getString("receiverAddress");
                String xreceiverPhone = rs.getString("receiverPhone");
                String xvoucherId = rs.getString("voucherId");
                String xdiscountAmount = rs.getString("discountAmount");
                String xgrandTotal = rs.getString("grandTotal");
                Orders x = new Orders(xorderId, xorderCode, xuserId, xsalerId, xorderTime, xtotalAmount, xstatusId, xreceiverName, xreceiverAddress, xreceiverPhone, xvoucherId, xdiscountAmount, xgrandTotal);
                listOrder.add(x);
            }
        } catch (SQLException e) {
            // Xử lý ngoại lệ: in ra thông báo lỗi hoặc ghi log
            e.printStackTrace();
        }
        return listOrder;
    }

    

public List<Orders> getOrderByYear(int salerID, Date day) {
        String sql = "SELECT * FROM [dbo].[Orders] \n"
                + "WHERE salerId = ? \n"
                + "AND YEAR([orderTime]) = YEAR(?)";
        List<Orders> listOrder = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, salerID);
            st.setDate(2, day);
            ResultSet rs = st.executeQuery();
            listOrder.clear();
            while (rs.next()) {
                String xorderId = rs.getString("orderId");
                String xorderCode = rs.getString("orderCode");
                String xuserId = rs.getString("userId");
                String xsalerId = rs.getString("salerId");
                String xorderTime = rs.getString("orderTime");
                String xtotalAmount = rs.getString("totalAmount");
                String xstatusId = rs.getString("statusId");
                String xreceiverName = rs.getString("receiverName");
                String xreceiverAddress = rs.getString("receiverAddress");
                String xreceiverPhone = rs.getString("receiverPhone");
                String xvoucherId = rs.getString("voucherId");
                String xdiscountAmount = rs.getString("discountAmount");
                String xgrandTotal = rs.getString("grandTotal");
                Orders x = new Orders(xorderId, xorderCode, xuserId, xsalerId, xorderTime, xtotalAmount, xstatusId, xreceiverName, xreceiverAddress, xreceiverPhone, xvoucherId, xdiscountAmount, xgrandTotal);
                listOrder.add(x);
            }
        } catch (SQLException e) {
            // Xử lý ngoại lệ: in ra thông báo lỗi hoặc ghi log
            e.printStackTrace();
        }
        return listOrder;
    }

    public List<Users> getSalerNameByID() {
        String sql = "SELECT * from Users where roleID=3";
        List<Users> listSaler = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            while (rs.next()) {
                String xId = rs.getString("userId");
                String xName = rs.getString("name");
                String xUserName = rs.getString("userName");
                String xPhone = rs.getString("phoneNumber");
                String xEmail = rs.getString("email");
                String xPassword = rs.getString("password");
                String xAddress = rs.getString("address");
                String xAvatar = rs.getString("avatar");
                String xRole = rs.getString("roleID");
                String xStates = rs.getString("statusID");
                Users x = new Users(xId, xUserName, xName, xPhone, xEmail, xPassword, xAddress, xAvatar, xRole, xStates);
                listSaler.add(x);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSaler;
    }

    public int getSalerIdBySalerName(String salerName) {
        String sql = "SELECT userId from Users where name = ?";
        int salerId=0;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, salerName);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                salerId = rs.getInt("userId");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return salerId;
    }
}
