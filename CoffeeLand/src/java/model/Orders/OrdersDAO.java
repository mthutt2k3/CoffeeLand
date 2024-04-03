/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Orders;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.MyDAO;
import static model.Users.UsersDAO.removeAccent;

/**
 *
 * @author Admin
 */
public class OrdersDAO extends MyDAO {
    
    public static void main(String[] args) {
        OrdersDAO x = new OrdersDAO();
        System.out.println(x.searchListOrdersByUserId("12", "Truong"));
    }
    
    public void UpdateOrderForCus(String id, String name, String address, String phoneNumber) {
        xSql = "UPDATE Orders\n" +
"               SET receiverName = N'" + name + "',\n" +
"                   receiverAddress = N'" + address + "',\n" +
"                   receiverPhone = '" + phoneNumber + "'\n" +
"               WHERE orderId = " + id;
        try {
            ps = con.prepareStatement(xSql);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }
    
    public void OrderCancel(String orderId) {
        xSql = """
               UPDATE Orders
               SET statusId = 5
               WHERE orderId = ?;
               """;

        try {
            ps = con.prepareStatement(xSql);
            
            ps.setString(1, orderId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    
    public int getLastOrderId() {
        xSql = """
               SELECT MAX(orderId) AS lastOrderId FROM Orders;
               """;
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int xorderId = rs.getInt("lastOrderId");
                return xorderId;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
        }
        return 0;
    }
    
    public void addToOrder(String orderCode, String userId, String orderTime, String statusId, String receiverName, String receiverAddress, String receiverPhone, String voucherId, int totalAmount, int discountAmount, int grandTotal) {
        xSql = """
               INSERT INTO Orders (orderCode, userId, salerId, orderTime, statusId, receiverName, receiverAddress, receiverPhone, voucherId, totalAmount, discountAmount, grandTotal) VALUES
               (?, ?, 4, ?, ?, ?, ?, ?, ?, ?, ?, ?);
               """;

        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, orderCode);
            ps.setString(2, userId);
            ps.setString(3, orderTime);
            ps.setString(4, statusId);
            ps.setString(5, receiverName);
            ps.setString(6, receiverAddress);
            ps.setString(7, receiverPhone);
            ps.setString(8, voucherId);
            ps.setInt(9, totalAmount);
            ps.setInt(10, discountAmount);
            ps.setInt(11, grandTotal);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int CountOrder(String id){
        xSql = """
               select Count(*) as x from Orders where = ?""";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                int xId = rs.getInt("x");
                return xId;}
        }catch(SQLException e){
        }
        return 0;
    }
    
    public int CountOrderSaler(String id){
        xSql = """
               select Count(*) as x from Orders where salerId = ?""";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                int xId = rs.getInt("x");
                return xId;}
        }catch(SQLException e){
        }
        return 0;
    }
    public int CountOrderWaiting(){
        xSql = """
               select Count(*) as x from Orders where statusId= 1""";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            if (rs.next()) {
                int xId = rs.getInt("x");
                return xId;}
        }catch(SQLException e){
        }
        return 0;
    }
    
    public int CountOrderSalerWaiting(String id){
        xSql = """
               select Count(*) as x from Orders where statusId= 1 AND salerId = ?""";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                int xId = rs.getInt("x");
                return xId;}
        }catch(SQLException e){
        }
        return 0;
    }
    
    public List<Orders> getListOrders(int page, int PPP) {
        List<Orders> t = new ArrayList<>();
        int start= PPP*(page-1)+1;
        int end= PPP*page;
        xSql = """
               WITH x AS(SELECT ROW_NUMBER() OVER(ORDER BY orderId ASC) AS r
                              ,* FROM Orders)
                              SELECT *
                                             FROM     x
                             where r between ? and ? 
               """;
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, start);
            ps.setInt(2, end);
            rs = ps.executeQuery();
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
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
        }
        return (t);
    }                                                                       
    
    public List<Orders> getListOrdersBySalerId(int page, int PPP, String id) {
        List<Orders> t = new ArrayList<>();
        int start= PPP*(page-1)+1;
        int end= PPP*page;
        xSql = """
               WITH x AS(SELECT ROW_NUMBER() OVER(ORDER BY orderId ASC) AS r
                              ,* FROM Orders where salerId = ?)
                              SELECT *
                                             FROM     x
               """;
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, id);
            
            rs = ps.executeQuery();
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
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
        }
        return (t);
    }
    
    public List<Orders> getListOrdersBySalerIdWaiting(int page, int PPP, String id) {
        List<Orders> t = new ArrayList<>();
        int start= PPP*(page-1)+1;
        int end= PPP*page;
        xSql = """
               WITH x AS(SELECT ROW_NUMBER() OVER(ORDER BY orderId ASC) AS r
                              ,* FROM Orders where salerId = ? AND statusId= 1)
                              SELECT *
                                             FROM     x
                             where r between ? and ? 
               """;
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, id);
            ps.setInt(2, start);
            ps.setInt(3, end);
            
            rs = ps.executeQuery();
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
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
        }
        return (t);
    }
    
    public List<Orders> searchOrderHistory(String input, String id) {
        List<Orders> t = new ArrayList<>();
        String q = removeAccent(input);
        
        xSql = "select * from Orders where userId ="+id+" AND (orderCode LIKE N'%"+input+"%' OR receiverName LIKE N'%"+input+"%' OR receiverName LIKE '%"+q+"%')";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
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
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
        }
        return (t);
    }
    
    public List<Orders> getListOrdersByUserId( String id) {
        List<Orders> t = new ArrayList<>();
        xSql = """
               WITH x AS(SELECT ROW_NUMBER() OVER(ORDER BY orderId DESC) AS r
                              ,* FROM Orders where userId = ?)
                              SELECT *
                                             FROM     x
               """;
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, id);
            
            rs = ps.executeQuery();
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
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
        }
        return (t);
    }
    
    public List<Orders> searchListOrdersByUserId( String id, String text) {
        List<Orders> t = new ArrayList<>();
        String xinput= text.replaceAll("\\s+","%");
        String q = removeAccent(xinput);
        
        xSql = "WITH x AS(SELECT ROW_NUMBER() OVER(ORDER BY orderId DESC) AS r\n" +
"                              ,* FROM Orders where userId = ? AND (receiverName LIKE N'%"+xinput+"%' OR receiverPhone LIKE N'%"+xinput+"%' OR receiverAddress LIKE N'%"+xinput+"%' OR receiverName LIKE N'%"+q+"%' OR receiverPhone LIKE N'%"+q+"%' OR receiverAddress LIKE N'%"+q+"%'))\n" +
"                              SELECT *\n" +
"                                             FROM     x";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, id);
            
            rs = ps.executeQuery();
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
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
        }
        return (t);
    }
    
    
    public List<Orders> getListOrdersByStatus(int page, int PPP, String status) {
        List<Orders> t = new ArrayList<>();
        int start= PPP*(page-1)+1;
        int end= PPP*page;
        xSql = """
               WITH x AS(SELECT ROW_NUMBER() OVER(ORDER BY orderId ASC) AS r
                              ,* FROM Orders where statusId = ?)
                              SELECT *
                                             FROM     x
               """;
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, status);
            
            rs = ps.executeQuery();
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
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
        }
        return (t);
    }
    
    public void UpdateStatus(String id, String status) {
        xSql = """
               UPDATE Orders
               SET     statusId = ?
               WHERE orderId = ?;
               """;
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, status);
            ps.setString(2, id);
            rs = ps.executeQuery();

        } catch (SQLException e) {
        }
    }
    
    public void UpdateSalerOrder(String id, String saleID) {
        xSql = """
               UPDATE Orders
               SET     salerId = ?
               WHERE orderId = ?;
               """;
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, saleID);
            ps.setString(2, id);
            rs = ps.executeQuery();

        } catch (SQLException e) {
        }
    }

    public List<Orders> searchOrder(String input, String id) {
        List<Orders> t = new ArrayList<>();
        String xinput= input.replaceAll("\\s+","%");
        String q = removeAccent(xinput);
        xSql = "select * from Orders where (orderCode LIKE N'%"+xinput+"%' OR receiverName LIKE N'%"+xinput+"%' OR receiverName LIKE '%"+q+"%') AND salerID = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, id);
            rs = ps.executeQuery();
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
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
        }
        return (t);
    }

    

    public Orders getOrdersById(String id) {
        Orders x = new Orders();
        xSql = """
               SELECT Orders.orderId, Orders.orderCode,Orders.userId, Users.userName , Orders.orderTime, Orders.totalAmount, OrderStatus.statusName, Orders.receiverName, Orders.receiverAddress, Orders.receiverPhone, 
                                 Orders.voucherId, Orders.discountAmount, Orders.grandTotal
               FROM     OrderDetails INNER JOIN
                                 Orders ON OrderDetails.orderId = Orders.orderId INNER JOIN
                                 Users ON Orders.salerId = Users.userId INNER JOIN
                                 OrderStatus ON Orders.statusId = OrderStatus.statusId INNER JOIN
                                 Products ON OrderDetails.productId = Products.productId INNER JOIN
                                 ProductSize ON OrderDetails.productId = ProductSize.productId AND OrderDetails.sizeId = ProductSize.sizeId AND Products.productId = ProductSize.productId INNER JOIN
                                 Size ON ProductSize.sizeId = Size.sizeId
               where Orders.orderId = ?
               """;
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, id);

            rs = ps.executeQuery();
            while (rs.next()) {
                String xorderId = rs.getString("orderId");
                String xorderCode = rs.getString("orderCode");
                String xuserId = rs.getString("userId");
                String xsalerId = rs.getString("userName");
                String xorderTime = rs.getString("orderTime");
                String xtotalAmount = rs.getString("totalAmount");
                String xstatusId = rs.getString("statusName");
                String xreceiverName = rs.getString("receiverName");
                String xreceiverAddress = rs.getString("receiverAddress");
                String xreceiverPhone = rs.getString("receiverPhone");
                String voucherId = rs.getString("voucherId");
                String xdiscountAmount = rs.getString("discountAmount");
                String xgrandTotal = rs.getString("grandTotal");

                Orders y = new Orders(xorderId, xorderCode, xuserId, xsalerId, xorderTime, xtotalAmount, xstatusId, xreceiverName, xreceiverAddress, xreceiverPhone, voucherId, xdiscountAmount, xgrandTotal);
                x = y;
                return x;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
        }
        return x;
    }
//    public List<Product> getListProduct() {
//        List<Product> t = new ArrayList<>();
//        xSql = "select productId, productName, originalPrice, retailPrice, image, description, categoryName from Products\n"
//                + "join Category on Category.categoryId = Products.categoryId\n";
//        try {
//            ps = con.prepareStatement(xSql);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                int xId = rs.getInt("productId");
//                String xproductName = rs.getString("productName");
//                String xoriginalPrice = rs.getString("originalPrice");
//                String xretailPrice = rs.getString("retailPrice");
//                String ximage = rs.getString("image");
//                String xdescription = rs.getString("description");
//                String xcategory = rs.getString("categoryName");
//                Product x = new Product(xId, xproductName, xoriginalPrice, xretailPrice, ximage, xdescription, xcategory);
//                t.add(x);
//            }
//            rs.close();
//            ps.close();
//        } catch (SQLException e) {
//        }
//        return (t);
//    }

    public List<Orders> sortOrder(String colName, String sortType, String Id) {
        List<Orders> t = new ArrayList<>();
        xSql = "select * from Orders\n" +
"                Where salerID = "+ Id+"             ORDER BY "+colName+" "+sortType+";";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
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
                String voucherId = rs.getString("voucherId");
                String xdiscountAmount = rs.getString("discountAmount");
                String xgrandTotal = rs.getString("grandTotal");
                Orders x = new Orders(xorderId, xorderCode, xuserId, xsalerId, xorderTime, xtotalAmount, xstatusId, xreceiverName, xreceiverAddress, xreceiverPhone, voucherId, xdiscountAmount, xgrandTotal);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
        }
        return (t);
    }
    
    public int grandTotalByMonth(int x, int y){
        int tt=0;
        xSql = "SELECT * FROM Orders"
                + " WHERE statusId = 4 AND YEAR(orderTime) = "+y+" AND MONTH(orderTime) = "+x;
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int a= rs.getInt("grandTotal");
                tt+=a;
            }
            rs.close();
            ps.close();
        }catch(SQLException e){
        }
        return tt;
    }
    
    public int grandTotalByMonthSaler(int x, int y, String z){
        int tt=0;
        xSql = "SELECT * FROM Orders"
                + " WHERE salerId = "+z+" AND statusId = 4 AND YEAR(orderTime) = "+y+" AND MONTH(orderTime) = "+x;
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int a= rs.getInt("grandTotal");
                tt+=a;
            }
            rs.close();
            ps.close();
        }catch(SQLException e){
        }
        return tt;
    }
    
    
    public int grandTotalByDay(int x, String y){
        int tt=0;
        xSql = "SELECT * FROM Orders\n" +
"WHERE salerId=? AND statusId = 4 AND YEAR(orderTime) = YEAR(GETDATE()) AND MONTH(orderTime) = MONTH(GETDATE()) AND DAY(orderTime)= ?;";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, y);
            ps.setInt(2, x);
            rs = ps.executeQuery();
            while (rs.next()) {
                int a= rs.getInt("grandTotal");
                tt+=a;
            }
            rs.close();
            ps.close();
        }catch(SQLException e){
        }
        return tt;
    }

}
