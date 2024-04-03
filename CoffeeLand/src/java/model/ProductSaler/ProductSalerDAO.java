/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.ProductSaler;
import java.sql.SQLException;
import model.MyDAO;

/**
 *
 * @author Admin
 */
public class ProductSalerDAO extends MyDAO{
    public static void main(String[] args) {
        ProductSalerDAO x= new ProductSalerDAO();
    }

    public ProductSaler getProductById(String productId, String orderId) {
        xSql = """
               SELECT Products.productId, Products.productName, Size.sizeName, OrderDetails.quantity, Products.thumbnail 
                              FROM    OrderDetails  
               			   INNER JOIN  Orders ON Orders.orderId = OrderDetails.orderId 
                              INNER JOIN  Products ON OrderDetails.productId = Products.productId 
                              INNER JOIN ProductSize ON OrderDetails.productId = ProductSize.productId AND OrderDetails.sizeId = ProductSize.sizeId AND Products.productId = ProductSize.productId 
                              INNER JOIN Size ON ProductSize.sizeId = Size.sizeId 
                              where Products.productId = ? AND OrderDetails.orderId =?
               """;
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, productId);
            ps.setString(2, orderId);
            rs = ps.executeQuery();
            while (rs.next()) {
                String xproductId = rs.getString("productId");
                String productName = rs.getString("productName");
                String sizeName = rs.getString("sizeName");
                String quantity = rs.getString("quantity");
                String thumbnail = rs.getString("thumbnail");
                ProductSaler x = new ProductSaler(xproductId, productName, sizeName, quantity, thumbnail);
                return x;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
        }
        return null;
    }
    
}
