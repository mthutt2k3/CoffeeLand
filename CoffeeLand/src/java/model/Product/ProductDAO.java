/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.MyDAO;

/**
 *
 * @author acer
 */
public class ProductDAO extends MyDAO {
    
    public String[] getTopFeedBack() {
        String[] Product = new String[1000];
        int count=0;
        xSql = """
               SELECT p.productName, COUNT(f.feedbackId) AS feedbackCount
               FROM Products p
               JOIN Feedbacks f ON p.productId = f.productId
               GROUP BY p.productName
               ORDER BY feedbackCount DESC;
               """; 
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while(rs.next()){
                String productName = rs.getString("productName");
                String feedbackCount = rs.getString("feedbackCount");
                Product[count]= productName;
                count++;
            }
            rs.close();
            ps.close();
        } catch(SQLException e) {
            e.printStackTrace(); // In ra lỗi nếu có
        }
        return Product;
    }
    
    public int[] getTopFeedBackQuantity() {
        int[] Product = new int[1000];
        int count=0;
        xSql = """
               SELECT p.productName, COUNT(f.feedbackId) AS feedbackCount
               FROM Products p
               JOIN Feedbacks f ON p.productId = f.productId
               GROUP BY p.productName
               ORDER BY feedbackCount DESC;
               """; 
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while(rs.next()){
                String productName = rs.getString("productName");
                int feedbackCount = rs.getInt("feedbackCount");
                Product[count]= feedbackCount;
                count++;
            }
            rs.close();
            ps.close();
        } catch(SQLException e) {
            e.printStackTrace(); // In ra lỗi nếu có
        }
        return Product;
    }
    
    public String[] getTopCategory() {
        String[] Product = new String[1000];
        int count=0;
        xSql = """
               WITH ProductCategorySales AS (
                   SELECT 
                       P.categoryId,
                       SUM(OD.quantity) AS totalQuantitySold,
                       ROW_NUMBER() OVER (ORDER BY SUM(OD.quantity) DESC) AS Rank
                   FROM 
                       OrderDetails OD
                   JOIN 
                       Products P ON OD.productId = P.productId
                   GROUP BY 
                       P.categoryId
               )
               SELECT 
                   PC.categoryId,
                   C.categoryName,
                   PC.totalQuantitySold
               FROM 
                   ProductCategorySales PC
               JOIN 
                   Category C ON PC.categoryId = C.categoryId
               WHERE 
                   Rank <= 5;
               """; 
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while(rs.next()){
                String categoryName = rs.getString("categoryName");
                String totalQuantitySold = rs.getString("totalQuantitySold");
                Product[count]= categoryName;
                count++;
            }
            rs.close();
            ps.close();
        } catch(SQLException e) {
            e.printStackTrace(); // In ra lỗi nếu có
        }
        return Product;
    }
    
    public int[] getTopCategoryQuantity() {
        int[] Product = new int[1000];
        int count=0;
        xSql = """
               WITH ProductCategorySales AS (
                   SELECT 
                       P.categoryId,
                       SUM(OD.quantity) AS totalQuantitySold,
                       ROW_NUMBER() OVER (ORDER BY SUM(OD.quantity) DESC) AS Rank
                   FROM 
                       OrderDetails OD
                   JOIN 
                       Products P ON OD.productId = P.productId
                   GROUP BY 
                       P.categoryId
               )
               SELECT 
                   PC.categoryId,
                   C.categoryName,
                   PC.totalQuantitySold
               FROM 
                   ProductCategorySales PC
               JOIN 
                   Category C ON PC.categoryId = C.categoryId
               WHERE 
                   Rank <= 5;
               """; 
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while(rs.next()){
                String categoryName = rs.getString("categoryName");
                int totalQuantitySold = rs.getInt("totalQuantitySold");
                Product[count]= totalQuantitySold;
                count++;
            }
            rs.close();
            ps.close();
        } catch(SQLException e) {
            e.printStackTrace(); // In ra lỗi nếu có
        }
        return Product;
    }
    
    public String[] getTopProduct() {
        String[] Product = new String[1000];
        int count=0;
        xSql = """
               SELECT TOP 7
                   OD.productId,
                   P.productName,
                   SUM(OD.quantity) AS totalQuantitySold
               FROM 
                   OrderDetails OD
               JOIN 
                   Products P ON OD.productId = P.productId
               GROUP BY 
                   OD.productId, P.productName
               ORDER BY 
                   totalQuantitySold DESC;
               """; 
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while(rs.next()){
                String productName = rs.getString("productName");
                String totalQuantitySold = rs.getString("totalQuantitySold");
                Product[count]= productName;
                count++;
            }
            rs.close();
            ps.close();
        } catch(SQLException e) {
            e.printStackTrace(); // In ra lỗi nếu có
        }
        return Product;
    }
    
    public int[] getTopProductQuantity() {
        int[] Product = new int[1000];
        int count=0;
        xSql = """
               SELECT TOP 7
                   OD.productId,
                   P.productName,
                   SUM(OD.quantity) AS totalQuantitySold
               FROM 
                   OrderDetails OD
               JOIN 
                   Products P ON OD.productId = P.productId
               GROUP BY 
                   OD.productId, P.productName
               ORDER BY 
                   totalQuantitySold DESC;
               """; 
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while(rs.next()){
                String productName = rs.getString("productName");
                int totalQuantitySold = rs.getInt("totalQuantitySold");
                Product[count]= totalQuantitySold;
                count++;
            }
            rs.close();
            ps.close();
        } catch(SQLException e) {
            e.printStackTrace(); // In ra lỗi nếu có
        }
        return Product;
    }

    public List<Product> getListProduct() {
        List<Product> t = new ArrayList<>();
           xSql = "select Products.productId, userName, productName, description, categoryName, statusName, thumbnail, format(price, '#,###') as price from Products \n" +
"  join Category on Category.categoryId = Products.categoryId\n" +
"  join Status on Status.statusId = Products.statusId\n" +
"  join Users on Users.userId = Products.userId\n" +
"  join ProductSize on ProductSize.productId = Products.productId\n" +
"  where sizeId=1 and Products.statusId = 1 ";
           try {
             ps = con.prepareStatement(xSql);
             rs = ps.executeQuery();
             while(rs.next()) {
                int xId = rs.getInt("productId");
                String xUserName = rs.getString("userName");
                String xProductName = rs.getString("productName");
                String xDescription = rs.getString("description");
                String xCategoryName = rs.getString("categoryName");
                String xStatusName = rs.getString("statusName");
                String xThumbnail = rs.getString("thumbnail");
                String xPrice = rs.getString("price");
               Product  x = new Product(xId, xUserName, xProductName, xDescription, xCategoryName, xStatusName, xThumbnail, xPrice);
               t.add(x);
             }
             rs.close();
             ps.close();
            }
            catch(SQLException e) {
            }
        return(t);
    }
    
//    public List<Product> searchByCategory(String x) {
//        List<Product> t = new ArrayList<>();
//           xSql = "select Products.productId, userName, productName, description, categoryName, statusName, thumbnail, price from Products \n" +
//"  join Category on Category.categoryId = Products.categoryId\n" +
//"  join Status on Status.statusId = Products.statusId\n" +
//"  join Users on Users.userId = Products.userId\n" +
//"  join ProductSize on ProductSize.productId = Products.productId\n" +
//"  where sizeId=1 and Products.statusId = 1";
//           try {
//             ps = con.prepareStatement(xSql);
//             rs = ps.executeQuery();
//             while(rs.next()) {
//                int xId = rs.getInt("productId");
//                String xUserName = rs.getString("userName");
//                String xProductName = rs.getString("productName");
//                String xDescription = rs.getString("description");
//                String xCategoryName = rs.getString("categoryName");
//                String xStatusName = rs.getString("statusName");
//                String xThumbnail = rs.getString("thumbnail");
//                String xPrice = rs.getString("price");
//               Product  x = new Product(xId, xUserName, xProductName, xDescription, xCategoryName, xStatusName, xThumbnail, xPrice);
//               t.add(x);
//             }
//             rs.close();
//             ps.close();
//            }
//            catch(SQLException e) {
//            }
//        return(t);
//    }


    public String getTotalProduct() {
        xSql = "select count(productId) as totalProduct from Products where statusId=1";
        try {
          ps = con.prepareStatement(xSql);
          rs = ps.executeQuery();
          while(rs.next()) {
             String xtotalProduct = rs.getString("totalProduct");
            return xtotalProduct;
          }
          rs.close();
          ps.close();
         }
         catch(SQLException e) {
         }
        return null;
    }

    public String getMaxPrice() {
        xSql = "  select max(price) as maxPrice from ProductSize \n" +
"  join Products on Products.productId = ProductSize.productId where statusId = 1 and Products.statusId = 1";
        try {
          ps = con.prepareStatement(xSql);
          rs = ps.executeQuery();
          while(rs.next()) {
             String xmaxprice = rs.getString("maxPrice");
            return xmaxprice;
          }
          rs.close();
          ps.close();
         }
         catch(SQLException e) {
         }
        return null;
    }

    public List<Product> get5Product() {
        List<Product> t = new ArrayList<>();
           xSql = """
                    select top(5)Products.productId, productName, thumbnail, price, AVG(ratedStar) as ratedStar from Products
                    join Feedbacks on Feedbacks.productId = Products.productId
                    join ProductSize on ProductSize.productId = Products.productId
                    where sizeId = 1 and Products.statusId = 1
                    group by Products.productId, productName, thumbnail, price
                    order by ratedStar desc
                  """;
           try {
             ps = con.prepareStatement(xSql);
             rs = ps.executeQuery();
             while(rs.next()) {
                int xId = rs.getInt("productId");
                String xProductName = rs.getString("productName");
                String xThumbnail = rs.getString("thumbnail");
                String xPrice = rs.getString("price");
                String xratedStar = rs.getString("ratedStar");
                if(xThumbnail == null){
                    xratedStar = "0";
                }
               Product  x = new Product(xId, xProductName, xThumbnail, xPrice, xratedStar);
               t.add(x);
             }
             rs.close();
             ps.close();
            }
            catch(SQLException e) {
            }
        return(t);
    }
    public Product getProduct(int xId) {
        Product t = null;
        xSql = """
               select distinct Products.productId, userName, productName, description, categoryName, statusName, thumbnail, AVG(ratedStar) as ratedStar from Products 
               join Category on Category.categoryId = Products.categoryId
               join Status on Status.statusId = Products.statusId
               join Users on Users.userId = Products.userId
               join ProductSize on ProductSize.productId = Products.productId
               join Size on Size.sizeId = ProductSize.sizeId
               left join Feedbacks on Feedbacks.productId = Products.productId
               where Products.productId = ?
               group by Products.productId, userName, productName, description, categoryName, statusName, thumbnail"""; 
        try {
         ps = con.prepareStatement(xSql);
         ps.setInt(1, xId);
         rs = ps.executeQuery();
         if(rs.next()){
                String xUserName = rs.getString("userName");
                String xProductName = rs.getString("productName");
                String xDescription = rs.getString("description");
                String xCategoryName = rs.getString("categoryName");
                String xStatusName = rs.getString("statusName");
                String xThumbnail = rs.getString("thumbnail");
                String xRatedStar = rs.getString("ratedStar");
                if(xRatedStar == null){
                    xRatedStar = "0";
                }
                t = new Product(xId, xUserName, xProductName, xDescription, xCategoryName, xStatusName, xThumbnail, "", "", xRatedStar);
         }
         rs.close();
         ps.close();
        }     
        catch(SQLException e) {
        }
        return t;
    }
    public Product getProductServer(int xId) {
        Product t = null;
        xSql = """
               select distinct Products.productId, userName, productName, description, Products.categoryId, Products.statusId, thumbnail, ratedStar from Products 
                 join Category on Category.categoryId = Products.categoryId
                 join Status on Status.statusId = Products.statusId
                 join Users on Users.userId = Products.userId
                 join ProductSize on ProductSize.productId = Products.productId
                 join Size on Size.sizeId = ProductSize.sizeId
                 left join Feedbacks on Feedbacks.productId = Products.productId
                 where Products.productId = ?"""; 
        try {
         ps = con.prepareStatement(xSql);
         ps.setInt(1, xId);
         rs = ps.executeQuery();
         if(rs.next()){
                String xUserName = rs.getString("userName");
                String xProductName = rs.getString("productName");
                String xDescription = rs.getString("description");
                String xCategoryName = rs.getString("categoryId");
                String xStatusName = rs.getString("statusId");
                String xThumbnail = rs.getString("thumbnail");
                String xRatedStar = rs.getString("ratedStar");
                if(xRatedStar == null){
                    xRatedStar = "0";
                }
                t = new Product(xId, xUserName, xProductName, xDescription, xCategoryName, xStatusName, xThumbnail, "", "", xRatedStar);
         }
         rs.close();
         ps.close();
        }     
        catch(SQLException e) {
        }
        return t;
    }
    

    public List<Product> getSizePriceByProductId(int xId) {
        List<Product> t = new ArrayList<>();
        xSql = """
               SELECT distinct Size.sizeName, format(ProductSize.price, '#,###') as price
               FROM Products
               JOIN ProductSize ON ProductSize.productId = Products.productId
               JOIN Size ON Size.sizeId = ProductSize.sizeId
               WHERE Products.productId = ? and price > 0
               order by price;
               """; 
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, xId);
            rs = ps.executeQuery();
            while(rs.next()){
                String xSizeName = rs.getString("sizeName");
                String xPrice = rs.getString("price");
                Product x = new Product(xId, xSizeName, xPrice);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch(SQLException e) {
            e.printStackTrace(); // In ra lỗi nếu có
        }
        return t;
    }
    
    
    
    public List<Product> getSizePriceByProductIdUpdate(int xId) {
        List<Product> t = new ArrayList<>();
        xSql = """
               SELECT distinct Size.sizeName, price
               FROM Products
               JOIN ProductSize ON ProductSize.productId = Products.productId
               JOIN Size ON Size.sizeId = ProductSize.sizeId
               WHERE Products.productId = ?
               order by price;
               """; 
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, xId);
            rs = ps.executeQuery();
            while(rs.next()){
                String xSizeName = rs.getString("sizeName");
                String xPrice = rs.getString("price");
                Product x = new Product(xId, xSizeName, xPrice);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch(SQLException e) {
            e.printStackTrace(); // In ra lỗi nếu có
        }
        return t;
    }

    

    public List<Product> getRelatedProductsByProductId(int categoryId) {
        List<Product> t = new ArrayList<>();
        xSql = """
               select Products.productId, userName, productName, description, categoryName, statusName, thumbnail, price from Products 
               join Category on Category.categoryId = Products.categoryId
               join Status on Status.statusId = Products.statusId
               join Users on Users.userId = Products.userId
               join ProductSize on ProductSize.productId = Products.productId
               where sizeId=1 and Products.statusId = 1 and Products.categoryId = ?"""; 
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, categoryId);
            rs = ps.executeQuery();
            while(rs.next()){
                int xId = rs.getInt("productId");
                String xUserName = rs.getString("userName");
                String xProductName = rs.getString("productName");
                String xDescription = rs.getString("description");
                String xCategoryName = rs.getString("categoryName");
                String xStatusName = rs.getString("statusName");
                String xThumbnail = rs.getString("thumbnail");
                String xPrice = rs.getString("price");
                Product  x = new Product(xId, xUserName, xProductName, xDescription, xCategoryName, xStatusName, xThumbnail, xPrice);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch(SQLException e) {
            e.printStackTrace(); // In ra lỗi nếu có
        }
        return t;
    }

    public int getCategoryIdByProductId(int xId) {
        int t = 0;
        xSql = "select categoryId from Products\n" +
"where productId = ?"; 
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, xId);
            rs = ps.executeQuery();
            while(rs.next()){
                String xcategoryId = rs.getString("categoryId");
                t = Integer.parseInt(xcategoryId);
            }
            rs.close();
            ps.close();
        } catch(SQLException e) {
            e.printStackTrace(); // In ra lỗi nếu có
        }
        return t;
    }
    

    public List<Product> getListProductByCategory(String categoryId) {
        List<Product> t = new ArrayList<>();
           xSql = "select Products.productId, userName, productName, description, categoryName, statusName, thumbnail, price from Products \n" +
"  join Category on Category.categoryId = Products.categoryId\n" +
"  join Status on Status.statusId = Products.statusId\n" +
"  join Users on Users.userId = Products.userId\n" +
"  join ProductSize on ProductSize.productId = Products.productId\n" +
"  where sizeId=1 and Products.statusId = 1 and Category.categoryId=? ";
           try {
             ps = con.prepareStatement(xSql);
             ps.setString(1, categoryId);
             rs = ps.executeQuery();
             while(rs.next()) {
                int xId = rs.getInt("productId");
                String xUserName = rs.getString("userName");
                String xProductName = rs.getString("productName");
                String xDescription = rs.getString("description");
                String xCategoryName = rs.getString("categoryName");
                String xStatusName = rs.getString("statusName");
                String xThumbnail = rs.getString("thumbnail");
                String xPrice = rs.getString("price");
               Product  x = new Product(xId, xUserName, xProductName, xDescription, xCategoryName, xStatusName, xThumbnail, xPrice);
               t.add(x);
             }
             rs.close();
             ps.close();
            }
            catch(SQLException e) {
                
            }
        return(t);
    }

    public List<Product> getSortListProduct(String col, String type) {
        List<Product> t = new ArrayList<>();
           xSql = """
                  select Products.productId, userName, productName, description, categoryName, statusName, thumbnail, ProductSize.price, count(Orders.statusId) as count from Products 
                  join Category on Category.categoryId = Products.categoryId
                  join Status on Status.statusId = Products.statusId
                  join Users on Users.userId = Products.userId
                  join ProductSize on ProductSize.productId = Products.productId
                  left join OrderDetails on OrderDetails.productId = Products.productId
                  left join Orders on OrderDetails.orderId = Orders.orderId
                  where ProductSize.sizeId=1 and Products.statusId = 1
                  group by Products.productId, userName, productName, description, categoryName, statusName, thumbnail, ProductSize.price
                  order by [""" + col + "] " + type;
           try {
             ps = con.prepareStatement(xSql);
             rs = ps.executeQuery();
             while(rs.next()) {
                int xId = rs.getInt("productId");
                String xUserName = rs.getString("userName");
                String xProductName = rs.getString("productName");
                String xDescription = rs.getString("description");
                String xCategoryName = rs.getString("categoryName");
                String xStatusName = rs.getString("statusName");
                String xThumbnail = rs.getString("thumbnail");
                String xPrice = rs.getString("price");
               Product  x = new Product(xId, xUserName, xProductName, xDescription, xCategoryName, xStatusName, xThumbnail, xPrice);
               t.add(x);
             }
             rs.close();
             ps.close();
            }
            catch(SQLException e) {
            }
        return(t);
    }
    

    public List<Product> getListProductByTxt(String txtSearch) {
        List<Product> t = new ArrayList<>();
        String txt = txtSearch.replaceAll("\\s+","%");
        String txt2 = removeAccent(txtSearch).replaceAll("\\s+","%");
           xSql = "select Products.productId, userName, productName, description, categoryName, statusName, thumbnail, price from Products \n" +
"                    join Category on Category.categoryId = Products.categoryId\n" +
"                    join Status on Status.statusId = Products.statusId\n" +
"                    join Users on Users.userId = Products.userId\n" +
"                    join ProductSize on ProductSize.productId = Products.productId\n" +
"                    where sizeId=1 and Products.statusId = 1 and "
                   + "(productName like "+" N'%" + txt + "%' or "
                   + "productName like "+" N'%" + txt2 + "%' )";
           try {
             ps = con.prepareStatement(xSql);
             rs = ps.executeQuery();
             while(rs.next()) {
                int xId = rs.getInt("productId");
                String xUserName = rs.getString("userName");
                String xProductName = rs.getString("productName");
                String xDescription = rs.getString("description");
                String xCategoryName = rs.getString("categoryName");
                String xStatusName = rs.getString("statusName");
                String xThumbnail = rs.getString("thumbnail");
                String xPrice = rs.getString("price");
               Product  x = new Product(xId, xUserName, xProductName, xDescription, xCategoryName, xStatusName, xThumbnail, xPrice);
               t.add(x);
             }
             rs.close();
             ps.close();
            }
            catch(SQLException e) {
            }
        return(t);
    }
    private static final char[] SOURCE_CHARACTERS = {'À', 'Á', 'Â', 'Ã', 'È', 'É',
            'Ê', 'Ì', 'Í', 'Ò', 'Ó', 'Ô', 'Õ', 'Ù', 'Ú', 'Ý', 'à', 'á', 'â',
            'ã', 'è', 'é', 'ê', 'ì', 'í', 'ò', 'ó', 'ô', 'õ', 'ù', 'ú', 'ý',
            'Ă', 'ă', 'Đ', 'đ', 'Ĩ', 'ĩ', 'Ũ', 'ũ', 'Ơ', 'ơ', 'Ư', 'ư', 'Ạ',
            'ạ', 'Ả', 'ả', 'Ấ', 'ấ', 'Ầ', 'ầ', 'Ẩ', 'ẩ', 'Ẫ', 'ẫ', 'Ậ', 'ậ',
            'Ắ', 'ắ', 'Ằ', 'ằ', 'Ẳ', 'ẳ', 'Ẵ', 'ẵ', 'Ặ', 'ặ', 'Ẹ', 'ẹ', 'Ẻ',
            'ẻ', 'Ẽ', 'ẽ', 'Ế', 'ế', 'Ề', 'ề', 'Ể', 'ể', 'Ễ', 'ễ', 'Ệ', 'ệ',
            'Ỉ', 'ỉ', 'Ị', 'ị', 'Ọ', 'ọ', 'Ỏ', 'ỏ', 'Ố', 'ố', 'Ồ', 'ồ', 'Ổ',
            'ổ', 'Ỗ', 'ỗ', 'Ộ', 'ộ', 'Ớ', 'ớ', 'Ờ', 'ờ', 'Ở', 'ở', 'Ỡ', 'ỡ',
            'Ợ', 'ợ', 'Ụ', 'ụ', 'Ủ', 'ủ', 'Ứ', 'ứ', 'Ừ', 'ừ', 'Ử', 'ử', 'Ữ',
            'ữ', 'Ự', 'ự',};

    private static final char[] DESTINATION_CHARACTERS = {'A', 'A', 'A', 'A', 'E',
            'E', 'E', 'I', 'I', 'O', 'O', 'O', 'O', 'U', 'U', 'Y', 'a', 'a',
            'a', 'a', 'e', 'e', 'e', 'i', 'i', 'o', 'o', 'o', 'o', 'u', 'u',
            'y', 'A', 'a', 'D', 'd', 'I', 'i', 'U', 'u', 'O', 'o', 'U', 'u',
            'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A',
            'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'E', 'e',
            'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E',
            'e', 'I', 'i', 'I', 'i', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o',
            'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O',
            'o', 'O', 'o', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u',
            'U', 'u', 'U', 'u',};

    public static char removeAccent(char ch) {
        int index = Arrays.binarySearch(SOURCE_CHARACTERS, ch);
        if (index >= 0) {
            ch = DESTINATION_CHARACTERS[index];
        }
        return ch;
    }

    public static String removeAccent(String str) {
        StringBuilder sb = new StringBuilder(str);
        for (int i = 0; i < sb.length(); i++) {
            sb.setCharAt(i, removeAccent(sb.charAt(i)));
        }
        return sb.toString();
    }

    public boolean isNameExist(String productName) {
        xSql = "select * from Products\n"
                + "  where productName= N'" + productName + "'";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
        }
        return false;
    }

    public void insert(Product x, List<Product> splst) {
        xSql = "insert into Products values\n" +
"(?,?,?,?,?,?)\n";
                
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, x.getUserName());
            ps.setString(2, x.getProductName());
            ps.setString(3, x.getDescription());
            ps.setString(4, x.getCategoryName());
            ps.setString(5, x.getStatusName());
            ps.setString(6, x.getThumbnail());
            ps.executeUpdate();
            insertSizePrice(splst);
            ps.close();
            
        } catch (SQLException e) {
        }
    }

    private void insertSizePrice(List<Product> splst) {
        xSql = "insert into ProductSize values\n" +
"((SELECT Max(ProductID) as LastID FROM Products),?,?)\n";
                
        try {
            for (Product p : splst) {
                ps = con.prepareStatement(xSql);
                ps.setString(1, p.getSizeName());
                ps.setString(2, p.getPrice());
                ps.executeUpdate();
                ps.close();
            }
        } catch (SQLException e) {
        }
    }
    

    public List<Product> getListProductServer(int page, int PPP) {
        List<Product> t = new ArrayList<>();
        int start = PPP * (page - 1) + 1;
        int end = PPP * page;
           xSql = """
                  select productId, userName, productName, description, categoryName, statusName, thumbnail from Products
                  join Users on Users.userId = Products.userId
                  join Category on Category.categoryId = Products.categoryId
                  join Status on Status.statusId = Products.statusId
                  ORDER BY productId
                  OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;""";
           try {
             ps = con.prepareStatement(xSql);
             ps.setInt(1, start - 1); // Số dòng bắt đầu
            ps.setInt(2, PPP); // Số dòng kết thúc
             rs = ps.executeQuery();
             while(rs.next()) {
                int xId = rs.getInt("productId");
                String xUserName = rs.getString("userName");
                String xProductName = rs.getString("productName");
                String xDescription = rs.getString("description");
                String xCategoryName = rs.getString("categoryName");
                String xStatusName = rs.getString("statusName");
                String xThumbnail = rs.getString("thumbnail");
               Product  x = new Product(xId, xUserName, xProductName, xDescription, xCategoryName, xStatusName, xThumbnail);
               t.add(x);
             }
             rs.close();
             ps.close();
            }
            catch(SQLException e) {
            }
        return(t);
    }
    public List<Product> getListProductServer() {
        List<Product> t = new ArrayList<>();
        
           xSql = """
                  select productId, userName, productName, description, categoryName, statusName, thumbnail from Products
                  join Users on Users.userId = Products.userId
                  join Category on Category.categoryId = Products.categoryId
                  join Status on Status.statusId = Products.statusId
                  """;
           try {
             ps = con.prepareStatement(xSql);
             
             rs = ps.executeQuery();
             while(rs.next()) {
                int xId = rs.getInt("productId");
                String xUserName = rs.getString("userName");
                String xProductName = rs.getString("productName");
                String xDescription = rs.getString("description");
                String xCategoryName = rs.getString("categoryName");
                String xStatusName = rs.getString("statusName");
                String xThumbnail = rs.getString("thumbnail");
               Product  x = new Product(xId, xUserName, xProductName, xDescription, xCategoryName, xStatusName, xThumbnail);
               t.add(x);
             }
             rs.close();
             ps.close();
            }
            catch(SQLException e) {
            }
        return(t);
    }

    public void update(Product x, List<Product> splst) {
        xSql = """
               UPDATE Products
               SET 
                   userId = ?,
                   productName = ?,
                   description = ?,
                   categoryId = ?,
                   statusId = ?,
                   thumbnail = ?
               WHERE productId = ?;
               """;
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, x.getUserName());
            ps.setString(2, x.getProductName());
            ps.setString(3, x.getDescription());
            ps.setString(4, x.getCategoryName());
            ps.setString(5, x.getStatusName());
            ps.setString(6, x.getThumbnail());
            ps.setInt(7, x.getProductId());
            ps.executeUpdate();
            updateSizePrice(x.getProductId(),splst);
            ps.close();
        } catch (SQLException e) {
        }
    }

    private void updateSizePrice(int xId,List<Product> splst) {
        xSql = """
               UPDATE ProductSize
                SET 
                    price = ?
                WHERE productId = ? and sizeId = ?;""";
                
        try {
            for (Product p : splst) {
                ps = con.prepareStatement(xSql);
                ps.setString(1, p.getPrice());
                ps.setInt(2, xId);
                ps.setString(3, p.getSizeName());
                ps.executeUpdate();
                ps.close();
            }
        } catch (SQLException e) {
        }
    }

    public int CountProduct() {
        xSql = """
               select Count(*) as x from Products""";
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
    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();
        List<Product> splst = new ArrayList<>();
        System.out.println(dao.isNameExist("Đường đen sữa đá"));
    }

    public List<Product> orderBy(String colName, String sortType) {
        List<Product> t = new ArrayList<>();
           xSql = "select productId, userName, productName, description, categoryName, statusName, thumbnail from Products\n" +
"join Users on Users.userId = Products.userId\n" +
"join Category on Category.categoryId = Products.categoryId\n" +
"join Status on Status.statusId = Products.statusId\n" +
"order by ["+ colName+"] "+sortType+"";
                  
           try {
             ps = con.prepareStatement(xSql);
             rs = ps.executeQuery();
             while(rs.next()) {
                int xId = rs.getInt("productId");
                String xUserName = rs.getString("userName");
                String xProductName = rs.getString("productName");
                String xDescription = rs.getString("description");
                String xCategoryName = rs.getString("categoryName");
                String xStatusName = rs.getString("statusName");
                String xThumbnail = rs.getString("thumbnail");
               Product  x = new Product(xId, xUserName, xProductName, xDescription, xCategoryName, xStatusName, xThumbnail);
               t.add(x);
             }
             rs.close();
             ps.close();
            }
            catch(SQLException e) {
            }
        return(t);
    }

    public List<Product> searchByCategory(String xType) {
        List<Product> t = new ArrayList<>();
           xSql = "select productId, userName, productName, description, categoryName, statusName, thumbnail from Products\n" +
"                  join Users on Users.userId = Products.userId\n" +
"                  join Category on Category.categoryId = Products.categoryId\n" +
"                  join Status on Status.statusId = Products.statusId\n" +
"                  Where Products.categoryId = " + xType;
                  
           try {
             ps = con.prepareStatement(xSql);
             rs = ps.executeQuery();
             while(rs.next()) {
                int xId = rs.getInt("productId");
                String xUserName = rs.getString("userName");
                String xProductName = rs.getString("productName");
                String xDescription = rs.getString("description");
                String xCategoryName = rs.getString("categoryName");
                String xStatusName = rs.getString("statusName");
                String xThumbnail = rs.getString("thumbnail");
               Product  x = new Product(xId, xUserName, xProductName, xDescription, xCategoryName, xStatusName, xThumbnail);
               t.add(x);
             }
             rs.close();
             ps.close();
            }
            catch(SQLException e) {
            }
        return(t);
    }
    

    
}
