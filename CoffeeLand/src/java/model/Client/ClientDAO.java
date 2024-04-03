/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Client;

import jakarta.servlet.http.HttpSession;
import java.sql.PreparedStatement;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.DBContext;

/**
 *
 * @author Admin
 */
public class ClientDAO extends DBContext {

    public User getUser(String user, String password) {
        String sql = "select * from users where (phoneNumber = ? OR Email = ?) AND password = ? AND roleID = 4";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user);
            st.setString(2, user);
            st.setString(3, password);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                User u = new User(rs.getString("name"), rs.getString("phoneNumber"),
                        rs.getString("email"), rs.getString("password"), rs.getString("address"), rs.getString("avatar"), rs.getInt("roleID"), rs.getInt("statusID"));
                return u;
            }
        } catch (Exception e) {

        }
        return null;
    }

    public User checkNewPassword(int userId, String password) {
        String sql = "select * from users where userId = ? and password = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userId);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                User u = new User(rs.getString("name"), rs.getString("phoneNumber"),
                        rs.getString("email"), rs.getString("password"), rs.getString("address"), rs.getString("avatar"), rs.getInt("roleID"), rs.getInt("statusID"));
                return u;
            }
        } catch (Exception e) {

        }
        return null;
    }

    public int getUserIdByEmail(String user) {
        String sql = "SELECT userId FROM users WHERE (phoneNumber = ? OR Email = ?) AND roleID = 4";
        int userId = 0;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user);
            st.setString(2, user);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                userId = rs.getInt("userId");
            }
        } catch (Exception e) {

        }
        return userId;
    }

    public User insert(User u) {
        String sql = "INSERT INTO [dbo].[Users]\n"
                + "           ([userName]\n"
                + "           ,[name]\n"
                + "           ,[phoneNumber]\n"
                + "           ,[email]\n"
                + "           ,[password]\n"
                + "           ,[address]\n"
                + "           ,[avatar]\n"
                + "           ,[roleID]\n"
                + "           ,[statusID])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, ""); // Leave username blank for now
            st.setString(2, u.getName());
            st.setString(3, u.getPhoneNumber());
            st.setString(4, u.getEmail());
            st.setString(5, u.getPassword());
            st.setString(6, u.getAddress());
            st.setString(7, u.getAvatar());
            st.setInt(8, u.getRoleID());
            st.setInt(9, u.getStatusID());

            int affectedRows = st.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Inserting user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int userId = generatedKeys.getInt(1);
                    String username = "CU0" + userId;

                    // Update username
                    updateUsername(userId, username);
                } else {
                    throw new SQLException("Inserting user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return u;
    }

    private void updateUsername(int userId, String username) throws SQLException {
        String updateSql = "UPDATE [dbo].[Users] SET userName = ? WHERE userId = ?";
        try (PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {
            updateStatement.setString(1, username);
            updateStatement.setInt(2, userId);
            updateStatement.executeUpdate();
        }
    }

    public boolean checkUniqueEmailPhone(String email, String phone) {
        String sql = "SELECT * FROM [dbo].[Users] WHERE (email = ? OR phoneNumber = ?) AND roleID=4";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            st.setString(2, phone);
            ResultSet rs = st.executeQuery();

            // Nếu rs.next() trả về true, tức là đã có người dùng có cùng email hoặc số điện thoại
            return !rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean checkUniqueEmail(String email) {
        String sql = "SELECT * FROM [dbo].[Users] WHERE email = ? and roleID=4";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();

            // Nếu rs.next() trả về true, tức là đã có người dùng có cùng email hoặc số điện thoại
            return !rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Category> getAll() {
        List<Category> list = new ArrayList<>();
        String sql = "select * from Category";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Category c = new Category(rs.getString("categoryName"), rs.getInt("categoryId"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Category getCategoryById(int id) {
        String sql = "select * from Category where categoryId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Category c = new Category(rs.getString("categoryName"), rs.getInt("categoryId"));
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public Status getStatusById(int id) {
        String sql = "select * from Status where statusId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Status ps = new Status(rs.getInt("statusId"), rs.getString("statusName"));
                return ps;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public User getUserById(int id) {
        String sql = "select * from Users where userId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                User u = new User(rs.getString("name"),
                        rs.getString("phoneNumber"), rs.getString("email"), rs.getString("password"),
                        rs.getString("address"), rs.getString("avatar"), rs.getInt("roleID"), rs.getInt("statusID"));
                return u;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public static void main(String[] args) {
        ClientDAO d = new ClientDAO();
        System.out.println(d.getProductByCid(0));
    }

    public List<Product> getProduct() {
        List<Product> list = new ArrayList<>();
        String sql = """
                     SELECT p.productId, p.productName, p.description, p.categoryId, p.statusId, pi.image, ps.sizeId, ps.price, s.sizeName
                     FROM Products p, ProductImages pi, ProductSize ps, Size s
                     WHERE p.productId = pi.productId
                     AND p.productId = ps.productId
                     AND ps.sizeId = s.sizeId""";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("productId"));
                p.setProductName(rs.getString("productName"));
                p.setDescription(rs.getString("description"));
                Category c = getCategoryById(rs.getInt("categoryId"));
                p.setCategoryID(c);
                Status s = getStatusById(rs.getInt("statusId"));
                p.setStatusID(s);
                List<ProductImage> imageList = getImageListByProductId(p.getProductID());
                p.setProductImg(imageList);
                List<Size> sizeList = getSizeList(p.getProductID());
                p.setSizeName(sizeList);

                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Product> getProductByCid(int cid) {
        List<Product> list = new ArrayList<>();
        String sql = """
                     SELECT p.productId, p.productName, p.description, p.categoryId, p.statusId, pi.image, ps.sizeId, ps.price, s.sizeName
                     FROM Products p, ProductImages pi, ProductSize ps, Size s
                     WHERE p.productId = pi.productId
                     AND p.productId = ps.productId
                     AND ps.sizeId = s.sizeId
                     AND 1=1""";
        if (cid != 0) {
            sql += " AND p.categoryId = ?";
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            if (cid != 0) {
                st.setInt(1, cid);
            }
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("productId"));
                p.setProductName(rs.getString("productName"));
                p.setDescription(rs.getString("description"));
                Category c = getCategoryById(rs.getInt("categoryId"));
                p.setCategoryID(c);
                Status s = getStatusById(rs.getInt("statusId"));
                p.setStatusID(s);
                List<ProductImage> imageList = getImageListByProductId(p.getProductID());
                p.setProductImg(imageList);
                List<Size> sizeList = getSizeList(p.getProductID());
                p.setSizeName(sizeList);

                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<ProductSize> getProductSizeListByResultSet(int productId, int sizeId) {
        String sql = "SELECT price FROM ProductSize WHERE productId = ? AND sizeId = ?";
        List<ProductSize> productSizeList = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, productId);
            st.setInt(2, sizeId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ProductSize ps = new ProductSize();
                ps.setProductID(productId);
                ps.setSizeID(sizeId);
                ps.setPrice(rs.getDouble("price"));
                productSizeList.add(ps);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception
        }
        return productSizeList;
    }

    public List<Size> getSizeList(int productId) throws SQLException {
        String sql = "SELECT s.sizeId, s.sizeName FROM Size s JOIN ProductSize ps ON s.sizeId = ps.sizeId WHERE ps.productId = ?";
        List<Size> sizeList = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, productId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Size size = new Size();
                size.setSizeID(rs.getInt("sizeId"));
                size.setSizeName(rs.getString("sizeName"));
                int sizeId = rs.getInt("sizeId");
                List<ProductSize> psList = getProductSizeListByResultSet(productId, sizeId);
                size.setPrice(psList);
                sizeList.add(size);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception
        }
        return sizeList;
    }

    public List<ProductImage> getImageListByProductId(int productId) throws SQLException {
        List<ProductImage> imageList = new ArrayList<>();
        String sql = "SELECT image FROM ProductImages WHERE productId = ?";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1, productId);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            ProductImage pi = new ProductImage();
            pi.setImage(rs.getString("image"));
            pi.setProductId(productId);
            imageList.add(pi);
        }
        return imageList;
    }

    public Product getProductbyID(int productId) {
        String sql = "select * from products where productId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, productId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("productId"));
                p.setProductName(rs.getString("productName"));
                p.setDescription(rs.getString("description"));
                Category c = getCategoryById(rs.getInt("categoryId"));
                p.setCategoryID(c);
                Status s = getStatusById(rs.getInt("statusId"));
                p.setStatusID(s);
                List<ProductImage> imageList = getImageListByProductId(p.getProductID());
                p.setProductImg(imageList);
                List<Size> sizeList = getSizeList(p.getProductID());
                p.setSizeName(sizeList);

                return p;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<Order> searchOrder(String key, int statusID, int userID) {
        List<Order> listO = new ArrayList<>();
        String sql = "SELECT * FROM Orders WHERE userId = ?";
        if (key != null && !key.isEmpty()) {
            sql += " AND (orderCode LIKE ? OR receiverName LIKE ? OR receiverAddress LIKE ? OR receiverPhone LIKE ?)";
        }
        if (statusID != -1) {
            sql += " AND statusId = ?";
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            int parameterIndex = 1; // Chỉ số tham số bắt đầu từ 1
            st.setInt(parameterIndex++, userID);

            if (key != null && !key.isEmpty()) {
                st.setString(parameterIndex++, "%" + key + "%");
                st.setString(parameterIndex++, "%" + key + "%");
                st.setString(parameterIndex++, "%" + key + "%");
                st.setString(parameterIndex++, "%" + key + "%");
            }
            if (statusID != -1) {
                st.setInt(parameterIndex, statusID);
            }

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Order o = new Order();
                o.setOrderID(rs.getInt("orderId"));
                User u = getUserById(rs.getInt("userId"));
                o.setUserId(u);
                o.setOrderTime(rs.getTimestamp("orderTime"));
                o.setTotalAmount(rs.getDouble("totalAmount"));
                OrderStatus os = getOrderStatusByID(rs.getInt("statusId"));
                o.setStatusId(os);
                o.setReceiverName(rs.getString("receiverName"));
                o.setReceiverAddress(rs.getString("receiverAddress"));
                o.setReceiverPhone(rs.getString("receiverPhone"));
                PaymentMethod pm = getPaymentMethodByID(rs.getInt("paymentMethodId"));
                o.setPaymentMethodId(pm);
                o.setGrandTotal(rs.getDouble("grandTotal"));
                listO.add(o);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listO;
    }

    public List<Product> search(String key) {
        List<Product> list = new ArrayList<>();
        String sql = "Select * from Products where 1=1";
        if (key != null && !key.isEmpty()) {
            sql += " and productName LIKE ? OR description LIKE ?";
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            if (key != null && !key.isEmpty()) {
                st.setString(1, "%" + key + "%");
                st.setString(2, "%" + key + "%");
            }
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("productId"));
                p.setProductName(rs.getString("productName"));
                p.setDescription(rs.getString("description"));
                Category c = getCategoryById(rs.getInt("categoryId"));
                p.setCategoryID(c);
                Status s = getStatusById(rs.getInt("statusId"));
                p.setStatusID(s);
                List<ProductImage> imageList = getImageListByProductId(p.getProductID());
                p.setProductImg(imageList);
                List<Size> sizeList = getSizeList(p.getProductID());
                p.setSizeName(sizeList);

                list.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Order> getOrderInRange(double min, double max, int userId) {
        String sql = "select * from Orders where userId = ? AND grandTotal BETWEEN ? AND ?";
        List<Order> listOS = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userId);
            st.setDouble(2, min);
            st.setDouble(3, max);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Order o = new Order();
                o.setOrderID(rs.getInt("orderId"));
                User u = getUserById(rs.getInt("userId"));
                o.setUserId(u);
                o.setOrderTime(rs.getTimestamp("orderTime"));
                o.setTotalAmount(rs.getDouble("totalAmount"));
                OrderStatus os = getOrderStatusByID(rs.getInt("statusId"));
                o.setStatusId(os);
                o.setReceiverName(rs.getString("receiverName"));
                o.setReceiverAddress(rs.getString("receiverAddress"));
                o.setReceiverPhone(rs.getString("receiverPhone"));
                PaymentMethod pm = getPaymentMethodByID(rs.getInt("paymentMethodId"));
                o.setPaymentMethodId(pm);
                o.setGrandTotal(rs.getDouble("grandTotal"));
                listOS.add(o);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOS;
    }

    public List<Product> getProductInRange(double min, double max) {
        String sql = "SELECT p.productId, p.productName, p.description, p.categoryId, p.statusId, pi.image, ps.sizeId, ps.price, s.sizeName\n"
                + "FROM Products p\n"
                + "INNER JOIN ProductImages pi ON p.productId = pi.productId\n"
                + "INNER JOIN ProductSize ps ON p.productId = ps.productId\n"
                + "INNER JOIN Size s ON ps.sizeId = s.sizeId\n"
                + "WHERE ps.price BETWEEN ? AND ?";
        List<Product> listP = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setDouble(1, min);
            st.setDouble(2, max);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("productId"));
                p.setProductName(rs.getString("productName"));
                p.setDescription(rs.getString("description"));
                Category c = getCategoryById(rs.getInt("categoryId"));
                p.setCategoryID(c);
                Status s = getStatusById(rs.getInt("statusId"));
                p.setStatusID(s);
                List<ProductImage> imageList = getImageListByProductId(p.getProductID());
                p.setProductImg(imageList);
                List<Size> sizeList = getSizeList(p.getProductID());
                p.setSizeName(sizeList);

                listP.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listP;
    }

    public List<Product> getAllProducts() {
        String sql = "select * from Products";
        List<Product> listP = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("productId"));

                p.setProductName(rs.getString("productName"));
                p.setDescription(rs.getString("description"));
                Category c = getCategoryById(rs.getInt("categoryId"));
                p.setCategoryID(c);
                Status s = getStatusById(rs.getInt("statusId"));
                p.setStatusID(s);
                listP.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listP;
    }

    public void updatePassword(String email, String newpass) {
        String sql = "UPDATE Users SET password = ? WHERE email = ? and roleId=4";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, newpass);
            st.setString(2, email);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePassword1(int userID, String newpass) {
        String sql = "UPDATE Users SET password = ? WHERE userId =?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, newpass);
            st.setInt(2, userID);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addToCart(int userId, int productId, int sizeId, int quantity, double price, double totalPrice) {
        String sql = "INSERT INTO [dbo].[Cart]\n"
                + "           ([userId]\n"
                + "           ,[productId]\n"
                + "           ,[sizeId]\n"
                + "           ,[quantity]\n"
                + "           ,[price]\n"
                + "           ,[totalPrice])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userId);
            st.setInt(2, productId);
            st.setInt(3, sizeId);
            st.setInt(4, quantity);
            st.setDouble(5, price);
            st.setDouble(6, totalPrice);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public int getSizeIdFromSizeName(String sizename) {
        int sizeId = 0;
        String sql = "SELECT sizeId from Size where sizeName = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, sizename);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                sizeId = rs.getInt("sizeId");
            }
        } catch (Exception e) {
        }
        return sizeId;
    }

    public String getProductNameFromProductID(int productId) {
        String productName = "";
        String sql = "SELECT productName FROM Products WHERE productId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, productId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                productName = rs.getString("productName");
            }
        } catch (SQLException e) {
            // Xử lý ngoại lệ: in ra hoặc ghi nhật ký lỗi
            e.printStackTrace();
        }
        return productName;
    }

    public int getProductIDFromProductName(String productName) {
        int productId = 0;
        String sql = "SELECT productId FROM Products WHERE productName = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, productName);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                productId = rs.getInt("productId");
            }
        } catch (SQLException e) {
            // Xử lý ngoại lệ: in ra hoặc ghi nhật ký lỗi
            e.printStackTrace();
        }
        return productId;
    }

    public Size getSizeFromSizeID(int productId, int sizeId) {
        String sql = "select * from Size where sizeId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, sizeId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Size s = new Size();
                s.setSizeID(sizeId);
                s.setSizeName(rs.getString("sizeName"));
                s.setPrice(getProductSizeListByResultSet(productId, sizeId));
                return s;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public double getPriceByPidSid(int productId, int sizeId) {
        String sql = "SELECT price FROM ProductSize WHERE productId = ? AND sizeId = ?";
        double price = 0.0;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, productId);
            st.setInt(2, sizeId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                price = rs.getDouble("price");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception
        }
        return price;
    }

    public List<Cart> getAllCart(int userId) {
        List<Cart> listC = new ArrayList<>();
        String sql = "SELECT userId,productId,sizeId,quantity,price,totalPrice FROM Cart where userId=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Cart c = new Cart();
                User u = getUserById(rs.getInt("userId"));
                c.setUserId(u);
                Product p = getProductbyID(rs.getInt("productId"));
                c.setProductId(p);
                Size s = getSizeFromSizeID(rs.getInt("productId"), rs.getInt("sizeId"));
                c.setSizeId(s);
                c.setQuatity(rs.getInt("quantity"));
                c.setPrice(rs.getDouble("price"));
                c.setTotalPrice(rs.getDouble("totalPrice"));
                listC.add(c);
            }
            // Đóng ResultSet sau khi sử dụng

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listC;
    }

    public void deleteCart(int productId, int sizeId) {
        String sql = "DELETE FROM Cart WHERE productId = ? AND sizeId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, productId);
            st.setInt(2, sizeId);
            // Thực thi câu lệnh SQL để xóa cart
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getPaymentMethodIdByPaymentMethodName(String paymentMethodName) {
        String sql = "select PaymentMethodId from PaymentMethod where paymentMethodName = ?";
        int paymentMethodId = 0;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, paymentMethodName);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                paymentMethodId = rs.getInt("PaymentMethodId");
            }
        } catch (Exception e) {
        }
        return paymentMethodId;
    }

    /*public void addOrder(int userId, Timestamp orderTime, double totalAmount, String receiverName, String receiverAddress, String receiverPhone, int paymentMethodId, double grandTotal) {
        String sql = "INSERT INTO [dbo].[Orders]\n"
                + "           ([orderCode]\n"
                + "           ,[userId]\n"
                + "           ,[salerId]\n"
                + "           ,[orderTime]\n"
                + "           ,[totalAmount]\n"
                + "           ,[statusId]\n"
                + "           ,[receiverName]\n"
                + "           ,[receiverAddress]\n"
                + "           ,[receiverPhone]\n"
                + "           ,[paymentMethodId]\n"
                + "           ,[shippingFee]\n"
                + "           ,[discountAmount]\n"
                + "           ,[grandTotal])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, "");
            st.setInt(2, userId);
            st.setInt(3, 1);
            st.setTimestamp(4, orderTime);
            st.setDouble(5, totalAmount);
            st.setInt(6, 5);
            st.setString(7, receiverName);
            st.setString(8, receiverAddress);
            st.setString(9, receiverPhone);
            st.setInt(10, paymentMethodId);
            st.setInt(11, 2);
            st.setDouble(12, 0.0);
            st.setDouble(13, grandTotal);
            int affectedRows = st.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Inserting user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int orderId = generatedKeys.getInt(1);
                    
                    String orderCode = "ORD0" + orderId;

                    // Update username
                    updateOrderCode(orderId, orderCode);
                } else {
                    throw new SQLException("Inserting user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

    }*/
    public void addOrder(Order order, int userId) {
        String sql = "INSERT INTO [dbo].[Orders]\n"
                + "           ([orderCode]\n"
                + "           ,[userId]\n"
                + "           ,[salerId]\n"
                + "           ,[orderTime]\n"
                + "           ,[totalAmount]\n"
                + "           ,[statusId]\n"
                + "           ,[receiverName]\n"
                + "           ,[receiverAddress]\n"
                + "           ,[receiverPhone]\n"
                + "           ,[paymentMethodId]\n"
                + "           ,[shippingFee]\n"
                + "           ,[discountAmount]\n"
                + "           ,[grandTotal])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, "");
            st.setInt(2, userId);
            st.setInt(3, 1);
            st.setTimestamp(4, order.getOrderTime());
            st.setDouble(5, order.getTotalAmount());
            st.setInt(6, 1);
            st.setString(7, order.getReceiverName());
            st.setString(8, order.getReceiverAddress());
            st.setString(9, order.getReceiverPhone());
            st.setInt(10, order.paymentMethodId.getPaymentMethodId());
            st.setInt(11, 2);
            st.setDouble(12, 0.0);
            st.setDouble(13, order.getGrandTotal());
            int affectedRows = st.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Inserting user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int orderId = generatedKeys.getInt(1);
                    order.setOrderID(orderId);
                    String orderCode = "ORD0" + orderId;

                    // Update username
                    updateOrderCode(orderId, orderCode);

                } else {
                    throw new SQLException("Inserting user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    private void updateOrderCode(int orderId, String orderCode) throws SQLException {
        String updateSql = "UPDATE [dbo].[Orders] SET orderCode = ? WHERE orderId = ?";
        try (PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {
            updateStatement.setString(1, orderCode);
            updateStatement.setInt(2, orderId);
            updateStatement.executeUpdate();
        }
    }

    public int getMostRecentOrderIdByUserId(int userId) {
        String sql = "SELECT TOP 1 orderId FROM Orders WHERE userId = ? ORDER BY orderTime DESC";
        int orderId = 0;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                orderId = rs.getInt("orderId");
            }
        } catch (Exception e) {
        }
        return orderId;
    }

    public int getOrderIDByUserIDTime(int userId, Timestamp orderTime) {
        String sql = "SELECT OrderId from Orders where userId = ? and orderTime = ?";
        int orderId = 0;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userId);
            st.setTimestamp(2, orderTime);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                orderId = rs.getInt("orderId");
            }
        } catch (Exception e) {
        }
        return orderId;
    }

    public void addToOrderDetailDirect(int orderId, int productId, int sizeId, int quantity, double price) {
        String sql = "INSERT INTO [dbo].[OrderDetails]\n"
                + "           ([orderId]\n"
                + "           ,[productId]\n"
                + "           ,[sizeId]\n"
                + "           ,[quantity]\n"
                + "           ,[price])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, orderId);
            st.setInt(2, productId);
            st.setInt(3, sizeId);
            st.setInt(4, quantity);
            st.setDouble(5, price);
            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public OrderStatus getOrderStatusByID(int orderStatusId) {
        String sql = "select * from OrderStatus where statusId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, orderStatusId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                OrderStatus os = new OrderStatus(rs.getInt("statusId"), rs.getString("statusName"));
                return os;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;

    }

    public PaymentMethod getPaymentMethodByID(int paymentMethodId) {
        String sql = "select * from PaymentMethod where paymentMethodId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, paymentMethodId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                PaymentMethod pm = new PaymentMethod(rs.getInt("paymentMethodId"), rs.getString("paymentMethodName"));
                return pm;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Order> getAllListOrderbyUserID(int userId) {
        List<Order> listO = new ArrayList<>();
        String sql = "select * from Orders where userId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Order o = new Order();
                o.setOrderID(rs.getInt("orderId"));
                User u = getUserById(rs.getInt("userId"));
                o.setUserId(u);
                o.setOrderTime(rs.getTimestamp("orderTime"));
                o.setTotalAmount(rs.getDouble("totalAmount"));
                OrderStatus os = getOrderStatusByID(rs.getInt("statusId"));
                o.setStatusId(os);
                o.setReceiverName(rs.getString("receiverName"));
                o.setReceiverAddress(rs.getString("receiverAddress"));
                o.setReceiverPhone(rs.getString("receiverPhone"));
                PaymentMethod pm = getPaymentMethodByID(rs.getInt("paymentMethodId"));
                o.setPaymentMethodId(pm);
                o.setGrandTotal(rs.getDouble("grandTotal"));
                listO.add(o);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listO;
    }

    public Order getOrderByID(int orderID) {
        String sql = "select * from Orders where orderId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, orderID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                User u = getUserById(rs.getInt("userId"));
                OrderStatus os = getOrderStatusByID(rs.getInt("statusId"));
                PaymentMethod pm = getPaymentMethodByID(rs.getInt("paymentMethodId"));
                Order o = new Order(rs.getInt("orderId"), u, rs.getTimestamp("orderTime"), rs.getDouble("totalAmount"), os, rs.getString("receiverName"), rs.getString("receiverAddress"), rs.getString("receiverPhone"), pm, rs.getDouble("grandTotal"));
                return o;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<OrderDetail> getAllOrderDetailByOID(int orderID) {
        List<OrderDetail> orderDetailsList = new ArrayList<>();
        String sql = "select * from OrderDetails where orderId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, orderID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                OrderDetail od = new OrderDetail();
                Order o = getOrderByID(rs.getInt("orderId"));
                od.setOrderId(o);
                Product p = getProductbyID(rs.getInt("productId"));
                od.setProductId(p);
                Size s = getSizeFromSizeID(rs.getInt("productId"), rs.getInt("sizeId"));
                od.setSizeId(s);
                od.setQuantity(rs.getInt("quantity"));
                od.setPrice(rs.getDouble("price"));
                orderDetailsList.add(od);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderDetailsList;
    }

    public double getTotalPriceFromCart(int userId, int productId, int sizeId) {
        double price = 0.0;
        String sql = "select totalPrice from Cart where userId = ? AND productId = ? AND sizeId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userId);
            st.setInt(2, productId);
            st.setInt(3, sizeId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                price = rs.getDouble("totalPrice");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return price;
    }

    public void updateCartItem(int userId, int productId, int sizeId, int newquantity, double price, double newToTalPrice) {
        String sql = "Update Cart SET quantity = quantity + ?, price = ?, totalPrice=? WHERE userId = ? AND productId = ? AND sizeId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, newquantity);
            st.setDouble(2, price);
            st.setDouble(3, newToTalPrice);
            st.setInt(4, userId);
            st.setInt(5, productId);
            st.setInt(6, sizeId);
            st.executeUpdate();
            // Thực thi câu lệnh SQL để cập nhật số lượng và giá của sản phẩm trong giỏ hàngst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkCartExist(int userId, int productId, int sizeId) {
        String sql = "SELECT COUNT(*) FROM Cart WHERE userId = ? AND productId = ? AND sizeId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userId);
            st.setInt(2, productId);
            st.setInt(3, sizeId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void updateOrder(int orderID, String receiverName, String receiverAddress, String receiverPhone) {
        String sql = "UPDATE Orders\n"
                + "SET receiverName = ?,\n"
                + "    receiverAddress = ?,\n"
                + "    receiverPhone = ?\n"
                + "WHERE orderId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, receiverName);
            st.setString(2, receiverAddress);
            st.setString(3, receiverPhone);
            st.setInt(4, orderID);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getOrderStatusIDByOrderID(int orderId) {
        String sql = "Select statusId from Orders where orderId=?";
        int statusId = 0;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, orderId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                statusId = rs.getInt("statusId");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statusId;
    }

    public void updateOrderStatus(int orderID) {
        String sql = "UPDATE Orders SET statusId = 5 WHERE orderId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, orderID);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<OrderStatus> getAllOrderStatus() {
        List<OrderStatus> listOrderStatus = new ArrayList<>();
        String sql = "select * from OrderStatus";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                OrderStatus os = new OrderStatus();
                os.setStatusId(rs.getInt("statusId"));
                os.setStatusName(rs.getString("statusName"));
                listOrderStatus.add(os);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOrderStatus;
    }

    public List<model.Product.Product> getProductTop3() {
        List<model.Product.Product> list = new ArrayList<>();
        String sql = "/****** Script for SelectTopNRows command from SSMS  ******/\n"
                + "SELECT TOP 3 \n"
                + "    p.*,\n"
                + "    ps.price AS Size1Price,\n"
                + "    TotalQuantity\n"
                + "FROM \n"
                + "    (\n"
                + "        SELECT \n"
                + "            od.productId,\n"
                + "            p.productName,\n"
                + "            SUM(od.quantity) AS TotalQuantity\n"
                + "        FROM \n"
                + "            Orders o\n"
                + "        INNER JOIN \n"
                + "            OrderDetails od ON o.orderId = od.orderId\n"
                + "        INNER JOIN \n"
                + "            Products p ON od.productId = p.productId\n"
                + "        GROUP BY \n"
                + "            od.productId, p.productName\n"
                + "    ) AS TopProducts\n"
                + "INNER JOIN \n"
                + "    Products p ON TopProducts.productId = p.productId\n"
                + "INNER JOIN \n"
                + "    ProductSize ps ON p.productId = ps.productId\n"
                + "WHERE \n"
                + "    ps.sizeId = 1\n"
                + "ORDER BY \n"
                + "    TotalQuantity DESC;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int xId = rs.getInt("productId");
                User u = getUserById(rs.getInt("userId"));
                String xProductName = rs.getString("productName");
                String xDescription = rs.getString("description");
                Category c = getCategoryById(rs.getInt("categoryId"));
                Status s = getStatusById(rs.getInt("statusId"));

                String xThumbnail = rs.getString("thumbnail");
                String xPrice = rs.getString("Size1Price");
                model.Product.Product x = new model.Product.Product(xId, u.getName(), xProductName, xDescription, c.getCategoryName(), s.getStatusName(), xThumbnail, xPrice);

                list.add(x);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
