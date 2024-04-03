package model.NewsClient;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.MyDAO;

public class NewsDAO extends MyDAO {

    public List<News> getListNews() {
        ArrayList<News> list = new ArrayList<>();
        try {
            xSql = "SELECT * FROM News\n"
                    + "ORDER BY priorityId, postedTime DESC;";
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                News s = new News(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));
                list.add(s);

            }
        } catch (Exception e) {
            System.err.println("erorr");
        }
        return list;
    }

    public int getTotalNews() {
        try {
            xSql = "select count(*) from news";
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);

            }
        } catch (Exception e) {
        }
        return 0;
    }

    public List<News> pagingNews(int index) {
        ArrayList<News> list = new ArrayList<>();
        try {
            xSql = "select  * from news \n"
                    + "ORDER BY priorityId, postedTime DESC\n"
                    + "offset ? rows fetch next 6 rows only";
            ps = con.prepareStatement(xSql);
            ps.setInt(1, (index - 1) * 6);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new News(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public News findNewsById(String x) {
        try {
            xSql = """
                               SELECT *
                               FROM News
                               WHERE NewsId = ?;""";
            ps = con.prepareStatement(xSql);
            ps.setString(1, x);
            rs = ps.executeQuery();
            while (rs.next()) {
                News objE = new News(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7));
                return objE;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public NewsCmt InsertComment(NewsCmt cmt) {
        xSql = """
                   INSERT INTO Comments (newsId, userId, message, status, time)
                   VALUES 
                    (?, ?, ?, ?, ?);""";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, cmt.getNewsId());
            ps.setInt(2, cmt.getUserId());
            ps.setString(3, cmt.getMessage());
            ps.setString(4, cmt.getStatus());
            ps.setString(5, cmt.getTime());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return cmt;
    }

    public List<NewsCmt> getListNewsComment(int newsId) {
        ArrayList<NewsCmt> list = new ArrayList<>();
        try {
            xSql = "SELECT * FROM Comments WHERE newsId = ?";
            ps = con.prepareStatement(xSql);
            ps.setInt(1, newsId);
            rs = ps.executeQuery();
            while (rs.next()) {
                NewsCmt comment = new NewsCmt(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)
                );
                list.add(comment);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
        return list;
    }

    public int count(String txtSearch) {
        try {
            xSql = "select count(*) from news where title like ?";
            ps = con.prepareStatement(xSql);
            ps.setString(1, "%" + txtSearch + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public List<News> search(String txtSearch, int index, int size) {
        ArrayList<News> list = new ArrayList<>();
        try {
            xSql = "with x as (select ROW_NUMBER() over "
                    + "(order by postedTime desc) as r , * from "
                    + "news where title like ?) select * from x where\n"
                    + "r between ?*3-2 and ? * 3";
            ps = con.prepareStatement(xSql);
            ps.setString(1, "%" + txtSearch + "%");
            ps.setInt(2, index);
            ps.setInt(3, index);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new News(rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public List<model.NewsClient.News> getAllNewsByPRI(){
        String sql = "Select * from News where priorityId = 1";
        List<model.NewsClient.News> listNew = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int xnewsId = rs.getInt("newsId");
                int xuserId = rs.getInt("userId");
                String xtitle = rs.getString("title");
                String ximage = rs.getString("image");
                String xcontent = rs.getString("content");
                String xpostTime = rs.getString("postedTime");
                int xpriorityId = rs.getInt("priorityId");
                model.NewsClient.News x = new model.NewsClient.News(xnewsId, xuserId, xtitle, ximage, xcontent, xpostTime, xpriorityId);
                listNew.add(x);
            }
        } catch (Exception e) {
        }
        return listNew;
    }
    
    
    public List<model.NewsClient.News> getAllNewsByPostTime(){
        String sql = "SELECT TOP 3 * FROM News ORDER BY postedTime DESC;";
        List<model.NewsClient.News> listNew = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int xnewsId = rs.getInt("newsId");
                int xuserId = rs.getInt("userId");
                String xtitle = rs.getString("title");
                String ximage = rs.getString("image");
                String xcontent = rs.getString("content");
                String xpostTime = rs.getString("postedTime");
                int xpriorityId = rs.getInt("priorityId");
                model.NewsClient.News x = new model.NewsClient.News(xnewsId, xuserId, xtitle, ximage, xcontent, xpostTime, xpriorityId);
                listNew.add(x);
            }
        } catch (Exception e) {
        }
        return listNew;
    }

    public static void main(String[] args) {
        NewsDAO newsDAO = new NewsDAO();

//    NewsComment comment = new NewsComment();
//    comment.setNewsId(5); 
//    comment.setUserId(4);
//    comment.setMessage("This is a test comment.");
//    comment.setStatus("approved"); 
//    comment.setTime("2024-02-16 14:30:00"); 
//    newsDAO.InsertComment(comment);
//        List<NewsCmt> comments = newsDAO.getListNewsComment(3); // Chuyển newsId vào đây
//        for (NewsCmt comment : comments) {
//            System.out.println(comment);
//        }
//    }
//        int count = newsDAO.count("cà phê");
//        System.out.println(count);
        List<News> list = newsDAO.search("khám", 1, 3);
        for (News o : list) {
            System.out.println(list);
        }

    }
}
