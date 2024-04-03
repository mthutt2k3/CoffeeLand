/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.CartClient;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.MyDAO;

/**
 *
 * @author Admin
 */
public class CartClientDAO extends MyDAO {

    public static void main(String[] args) {
        CartClientDAO x = new CartClientDAO();
        x.deleteFromCart("5", "2", "2");

    }
    
    public int countCartById(String Id) {
        List<CartClient> t = new ArrayList<>();
        int x=0;
        xSql = """
               SELECT *
               FROM Cart
               where userId = ?
              """;
        
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, Id);
            rs = ps.executeQuery();
            while (rs.next()) {
                String price = rs.getString("price");
                x++;

            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;
    }

    public void addToCart(String userId, String productId, String sizeId, String quantity, String price, String totalPrice) {
        xSql = """
               INSERT INTO Cart (userId, productId, sizeId, quantity, price, totalPrice) VALUES
               (?, ?, ?, ?, ?, ?);
               """;

        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, userId);
            ps.setString(2, productId);
            ps.setString(3, sizeId);
            ps.setString(4, quantity);
            ps.setString(5, price);
            ps.setString(6, totalPrice);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateCheckout(String userId, String productId, String sizeId, String quantity, String price, String totalPrice) {
        xSql = """
               UPDATE Cart 
               SET quantity = ?, 
                   price = ?, 
                   totalPrice = ? 
               WHERE userId = ? AND productId = ? AND sizeId = ?;
               """;

        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, quantity);
            ps.setString(2, price);
            ps.setString(3, totalPrice);
            ps.setString(4, userId);
            ps.setString(5, productId);
            ps.setString(6, sizeId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFromCart(String userId, String ProductId, String SizeId) {
        xSql = """
               DELETE FROM Cart
               WHERE userId = ? AND productId = ? AND sizeId= ?
               """;

        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, userId);
            ps.setString(2, ProductId);
            ps.setString(3, SizeId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getPrice(String Id, String size) {
        xSql = """
               Select* From ProductSize
               where productId= ? AND sizeId=?
              """;

        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, Id);
            ps.setString(2, size);
            rs = ps.executeQuery();
            while (rs.next()) {
                String price = rs.getString("price");
                return Integer.parseInt(price);

            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<CartClient> getProductInCartById(String xString) {
        List<CartClient> t = new ArrayList<>();
        xSql = """
               SELECT Cart.cartId,Products.productId, Products.productName, Size.sizeName, ProductSize.price, Cart.quantity, Cart.totalPrice, Products.thumbnail
               FROM     Cart INNER JOIN
                                 Products ON Cart.productId = Products.productId INNER JOIN
                                 ProductSize ON Cart.productId = ProductSize.productId AND Cart.sizeId = ProductSize.sizeId AND Products.productId = ProductSize.productId INNER JOIN
                                 Size ON ProductSize.sizeId = Size.sizeId
               				  where Cart.userId =? AND Products.statusId = 1
               ORDER BY Cart.cartId DESC
               """;

        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, xString);
            rs = ps.executeQuery();
            while (rs.next()) {
                String cartId = rs.getString("cartId");
                String productName = rs.getString("productName");
                String productId = rs.getString("productId");
                String sizeName = rs.getString("sizeName");
                String price = rs.getString("price");
                String quantity = rs.getString("quantity");
                String totalPrice = rs.getString("totalPrice");
                String thumbnail = rs.getString("thumbnail");

                CartClient x = new CartClient(cartId, productId, productName, sizeName, price, quantity, totalPrice, thumbnail);

                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public CartClient getProductInCartByUserIdProductId(String UserId, String ProductId, String SizeId) {
        CartClient x = null;
        xSql = """
               SELECT Cart.cartId,Products.productId, Products.productName, Size.sizeName, ProductSize.price, Cart.quantity, Cart.totalPrice, Products.thumbnail
               FROM     Cart INNER JOIN
                                 Products ON Cart.productId = Products.productId INNER JOIN
                                 ProductSize ON Cart.productId = ProductSize.productId AND Cart.sizeId = ProductSize.sizeId AND Products.productId = ProductSize.productId INNER JOIN
                                 Size ON ProductSize.sizeId = Size.sizeId
               				  where Cart.userId =? AND Products.productId = ? AND Size.sizeName = ?
               """;

        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, UserId);
            ps.setString(2, ProductId);
            ps.setString(3, SizeId);
            rs = ps.executeQuery();
            while (rs.next()) {
                String cartId = rs.getString("cartId");
                String productName = rs.getString("productName");
                String productId = rs.getString("productId");
                String sizeName = rs.getString("sizeName");
                String price = rs.getString("price");
                String quantity = rs.getString("quantity");
                String totalPrice = rs.getString("totalPrice");
                String thumbnail = rs.getString("thumbnail");

                x = new CartClient(cartId, productId, productName, sizeName, price, quantity, totalPrice, thumbnail);
                return x;
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (x);
    }

}
