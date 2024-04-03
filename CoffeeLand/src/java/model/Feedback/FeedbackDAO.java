/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Feedback;

import java.util.ArrayList;
import java.util.List;
import model.MyDAO;

/**
 *
 * @author Admin
 */
public class FeedbackDAO extends MyDAO {
    
    public List<Feedback> getListFeedback() {
        ArrayList<Feedback> list = new ArrayList<>();
        try {
            xSql = "SELECT \n"
                    + "    f.feedbackId, \n"
                    + "    p.productName, \n"
                    + "    u.name, \n"
                    + "	   f.ratedStar,\n"
                    + "    f.image, \n"
                    + "    f.message, \n"
                    + "    f.statusid, \n"
                    + "    f.feedbacktime\n"
                    + "FROM \n"
                    + "    Feedbacks f\n"
                    + "JOIN \n"
                    + "    Products p ON f.productId = p.productId\n"
                    + "JOIN \n"
                    + "    Users u ON f.userId = u.userId;";
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Feedback s = new Feedback(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
                list.add(s);

            }
        } catch (Exception e) {
            System.err.println("erorr");
        }
        return list;
    }

    public String getTotalFeedback() {
        try {
            xSql = "	SELECT \n"
                    + "    (SELECT COUNT(*) FROM Feedbacks) AS feedbackCount";
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString(1);

            }
        } catch (Exception e) {
        }
        return null;
    }

   public List<Feedback> pagingFeedbacks(int page, int PPP) {
        List<Feedback> list = new ArrayList<>();
        int start = PPP * (page - 1) + 1;
        int end = PPP * page;
        String xSql = """
                     SELECT 
                          f.feedbackId, 
                          p.productName, 
                          u.name, 
                      	  f.ratedStar,
                          f.image, 
                          f.message, 
                          f.statusid, 
                          f.feedbacktime
                      FROM 
                          Feedbacks f
                      JOIN 
                          Products p ON f.productId = p.productId
                      JOIN 
                          Users u ON f.userId = u.userId
                      ORDER BY 
                          f.feedbackTime DESC
                      OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;""" // Sắp xếp theo categoryId để phân trang
        ; // Lấy số dòng bắt đầu từ start và số dòng kết thúc từ end
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, start - 1); // Số dòng bắt đầu
            ps.setInt(2, PPP); // Số dòng kết thúc
            rs = ps.executeQuery();
            while (rs.next()) {
                 Feedback s = new Feedback(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
                list.add(s);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    public List<Feedback> getListProduct() {
        ArrayList<Feedback> list = new ArrayList<>();
        try {
            xSql = "	SELECT DISTINCT p.productName\n"
                    + "FROM Feedbacks f\n"
                    + "JOIN Products p ON f.productId = p.productId;";
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Feedback s = new Feedback(rs.getString(1));
                list.add(s);

            }
        } catch (Exception e) {
            System.err.println("erorr");
        }
        return list;
    }

    public void updateFeedbackStatus(String Id, String newStatus) {
        String xSql = """
                 UPDATE Feedbacks
                 SET statusID = ?
                 WHERE FeedbackId = ?;
                 """;
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, newStatus);
            ps.setString(2, Id);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String count(String txtSearch) {
        try {
            xSql = "SELECT \n"
                    + "    p.productName,\n"
                    + "    COUNT(f.feedbackId) AS feedbackCount\n"
                    + "FROM \n"
                    + "    Products p\n"
                    + "JOIN \n"
                    + "    Feedbacks f ON p.productId = f.productId\n"
                    + "WHERE \n"
                    + "    p.productName LIKE ? \n"
                    + "GROUP BY \n"
                    + "    p.productName;";
            ps = con.prepareStatement(xSql);
            ps.setString(1, "%" + txtSearch + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString(2);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Feedback> search(String txtSearch, int index, int size) {
        ArrayList<Feedback> list = new ArrayList<>();
        try {
            String xSql = "WITH FeedbacksJoined AS (\n"
                    + "    SELECT \n"
                    + "        f.feedbackId, \n"
                    + "        p.productName, \n"
                    + "        u.name, \n"
                    + "        f.ratedStar,\n"
                    + "        f.image, \n"
                    + "        f.message, \n"
                    + "        f.statusid, \n"
                    + "        f.feedbacktime,\n"
                    + "        ROW_NUMBER() OVER (ORDER BY f.feedbacktime DESC) AS r\n"
                    + "    FROM \n"
                    + "        Feedbacks f\n"
                    + "    JOIN \n"
                    + "        Products p ON f.productId = p.productId\n"
                    + "    JOIN \n"
                    + "        Users u ON f.userId = u.userId\n"
                    + "    WHERE \n"
                    + "        u.name = ?\n"
                    + ")\n"
                    + "SELECT * \n"
                    + "FROM FeedbacksJoined \n"
                    + "WHERE r BETWEEN (? - 1) * ? + 1 AND ? * ?;";
            ps = con.prepareStatement(xSql);
            ps.setString(1, txtSearch);
            ps.setInt(2, index);
            ps.setInt(3, size);
            ps.setInt(4, index);
            ps.setInt(5, size);
            rs = ps.executeQuery();

            while (rs.next()) {
                Feedback s = new Feedback(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
                list.add(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Feedback> getFeedbacksByProductName(String productName) {
        List<Feedback> feedbacks = new ArrayList<>();
        String sql = "SELECT f.feedbackId, p.productName, u.name, f.ratedStar, f.image, f.message, f.statusid, f.feedbacktime "
                + "FROM Feedbacks f "
                + "JOIN Products p ON f.productId = p.productId "
                + "JOIN Users u ON f.userId = u.userId "
                + "WHERE p.productName = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, productName);
            rs = ps.executeQuery();
            while (rs.next()) {
                Feedback feedback = new Feedback(
                        rs.getString("feedbackId"),
                        rs.getString("productName"),
                        rs.getString("username"),
                        rs.getString("ratedStar"),
                        rs.getString("image"),
                        rs.getString("message"),
                        rs.getString("statusid"),
                        rs.getString("feedbacktime")
                );
                feedbacks.add(feedback);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return feedbacks;
    }

    public static void main(String[] args) {
        FeedbackDAO db = new FeedbackDAO();
        List<Feedback> list = db.search("latte", 1, 3);

        System.out.println(list);

    }
    
}
