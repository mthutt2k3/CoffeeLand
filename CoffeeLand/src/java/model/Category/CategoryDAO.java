/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Category;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.MyDAO;
import model.News.News;
import model.Product.Product;


/**
 *
 * @author acer
 */
public class CategoryDAO extends MyDAO {
    
    public int CountCate(){
        xSql = """
               select Count(*) as x from Category""";
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

    public List<Category> getListCategory(int page, int PPP) {
        List<Category> t = new ArrayList<>();
        int start = PPP * (page - 1) + 1;
        int end = PPP * page;
        String xSql = """
                      SELECT Category.categoryId, categoryName, COUNT(Products.productId) as countProduct 
                      FROM Category 
                      FULL OUTER JOIN Products ON Category.categoryId = Products.categoryId 
                      GROUP BY Category.categoryId, categoryName
                      HAVING COUNT(Products.productId) >= 0
                      ORDER BY Category.categoryId
                      OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;""" // Sắp xếp theo categoryId để phân trang
        ; // Lấy số dòng bắt đầu từ start và số dòng kết thúc từ end
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, start - 1); // Số dòng bắt đầu
            ps.setInt(2, PPP); // Số dòng kết thúc
            rs = ps.executeQuery();
            while (rs.next()) {
                int xcategoryId = rs.getInt("categoryId");
                String xcategoryName = rs.getString("categoryName");
                String xcountProduct = rs.getString("countProduct");
                Category c = new Category(xcategoryId, xcategoryName, xcountProduct);
                t.add(c);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }
    public List<Category> getListCategory() {
        List<Category> t = new ArrayList<>();
        
        String xSql = """
                      SELECT Category.categoryId, categoryName, COUNT(Products.productId) as countProduct 
                      FROM Category 
                      FULL OUTER JOIN Products ON Category.categoryId = Products.categoryId 
                      GROUP BY Category.categoryId, categoryName
                      HAVING COUNT(Products.productId) >= 0
                      """ // Sắp xếp theo categoryId để phân trang
        ; // Lấy số dòng bắt đầu từ start và số dòng kết thúc từ end
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int xcategoryId = rs.getInt("categoryId");
                String xcategoryName = rs.getString("categoryName");
                String xcountProduct = rs.getString("countProduct");
                Category c = new Category(xcategoryId, xcategoryName, xcountProduct);
                t.add(c);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }


    public List<Category> searchCategory(String xString) {
        List<Category> t = new ArrayList<>();
        xSql = "SELECT Category.categoryId, categoryName, COUNT(Products.productId) as countProduct \n" +
"FROM Category \n" +
"FULL OUTER JOIN Products ON Category.categoryId = Products.categoryId\n" +
"WHERE Category.categoryId like N'%"+ xString +"%' or categoryName like N'%"+ xString +"%'\n" +
"GROUP BY Category.categoryId, categoryName\n" +
"HAVING COUNT(Products.productId) >= 0 ";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int xcategoryId = rs.getInt("categoryId");
                String xcategoryName = rs.getString("categoryName");
                String xcountProduct = rs.getString("countProduct");
                Category c = new Category(xcategoryId, xcategoryName, xcountProduct);
                t.add(c);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
        }
        return (t);
    }
    

    public List<Category> orderBy(String xColName, String xSortType) {
        List<Category> t = new ArrayList<>();
        xSql = "SELECT Category.categoryId, categoryName, COUNT(Products.productId) as countProduct \n" +
"                                     FROM Category \n" +
"                                     FULL OUTER JOIN Products ON Category.categoryId = Products.categoryId \n" +
"                                     GROUP BY Category.categoryId, categoryName\n" +
"                                     HAVING COUNT(Products.productId) >= 0\n" +
"                order by ["+ xColName +"] "+ xSortType +"";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int xcategoryId = rs.getInt("categoryId");
                String xcategoryName = rs.getString("categoryName");
                String xcountProduct = rs.getString("countProduct");
                Category c = new Category(xcategoryId, xcategoryName, xcountProduct);
                t.add(c);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
        }
        return (t);
    }

    public boolean isNameExist(String xcategoryName) {
        xSql = "select categoryId, categoryName from Category\n"
                + "  where categoryName='" + xcategoryName + "'";
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

    public void insert(Category x) {
        xSql = "INSERT INTO Category(categoryName)\n"
                + "VALUES (?)";
                
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, x.getCategoryName());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
        }
    }

    public Category getCategory(int xId) {
        Category x = null;
        xSql = "select categoryId, categoryName from Category\n"
                + " where categoryId=?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, xId);
            rs = ps.executeQuery();
            if (rs.next()) {
                String xcategoryName = rs.getString("categoryName");
                x = new Category(xId, xcategoryName);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
        }
        return (x);
    }

    public void update(int xId, Category x) {
        xSql = "update Category set [categoryName]=? where categoryId = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, x.getCategoryName());
            ps.setInt(2, xId);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
        }
    }

    public List<Category> searchByStatus(String xStatus) {
        List<Category> t = new ArrayList<>();
        xSql = "select categoryId, categoryName, statusName from Category join Status on Category.statusId = Status.statusId\n"
                + "where statusName ='" + xStatus + "'";
        try {
            ps = con.prepareStatement(xSql);

            rs = ps.executeQuery();
            while (rs.next()) {
                int xId = rs.getInt("categoryId");
                String xcategoryName = rs.getString("categoryName");
                String xstatus = rs.getString("statusName");
                Category x = new Category(xId, xcategoryName,xstatus);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
        }
        return (t);
    }

    public List<Category> getListCategory(int min) {
        List<Category> t = new ArrayList<>();
           xSql = "select Category.categoryId, categoryName, COUNT(Products.productId) as countProduct from Category\n" +
"left join Products on Products.categoryId = Category.categoryId\n" +
"group by Category.categoryId, categoryName\n" +
"having COUNT(Products.productId)>?";
           try {
             ps = con.prepareStatement(xSql);
             ps.setInt(1, min);
             rs = ps.executeQuery();
             while(rs.next()) {
                int xId = rs.getInt("categoryId");
                String xcategoryName = rs.getString("categoryName");
                String xcountProduct = rs.getString("countProduct");
                Category x = new Category(xId, xcategoryName, xcountProduct);
                t.add(x);
             }
             rs.close();
             ps.close();
            }
            catch(SQLException e) {
            }
        return(t);
    }

    public Category getCategoryByProductId(int xId) {
        Category x = null;
        xSql = "select Category.categoryId, Category.categoryName from Category join Products\n" +
"on Products.categoryId = Category.categoryId\n" +
"where productId =?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, xId);
            rs = ps.executeQuery();
            if (rs.next()) {
                int xcategoryId =  rs.getInt("categoryId");
                String xcategoryName = rs.getString("categoryName");
                x = new Category(xcategoryId, xcategoryName);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
        }
        return (x);
    }


    
    

    
}