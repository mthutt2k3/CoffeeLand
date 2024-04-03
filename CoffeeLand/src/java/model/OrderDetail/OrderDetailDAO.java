package model.OrderDetail;

import model.NewsClient.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.MyDAO;

public class OrderDetailDAO extends MyDAO {

    public static void main(String[] args) {
        OrderDetailDAO x= new OrderDetailDAO();
        x.addToOrderDetail("8", "4", "1", "1", "30000");
    }
    
    public void addToOrderDetail(String orderId, String productId, String sizeId, String quantity, String price) {
        xSql = """
               INSERT INTO OrderDetails (orderId, productId, sizeId, quantity, price) VALUES
               (?, ?, ?, ?, ?);
               """;

        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, orderId);
            ps.setString(2, productId);
            ps.setString(3, sizeId);
            ps.setString(4, quantity);
            ps.setString(5, price);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<OrderDetailEntity> getListProductByOrderId(String id) {
        List<OrderDetailEntity> t = new ArrayList<>();
        xSql = """
               select * from OrderDetails where orderId =?
               """;
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                String orderDetailId = rs.getString("orderDetailId");
                String orderId = rs.getString("orderId");
                String productId = rs.getString("productId");
                String sizeId = rs.getString("sizeId");
                String quantity = rs.getString("quantity");
                String price = rs.getString("price");
                OrderDetailEntity x = new OrderDetailEntity(orderDetailId, orderId, productId, sizeId, quantity, price);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
        }
        return (t);
    }

    
}
