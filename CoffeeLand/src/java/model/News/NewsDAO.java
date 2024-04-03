/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.News;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.MyDAO;

/**
 *
 * @author acer
 */
public class NewsDAO extends MyDAO{
    
    public int CountNews(){
        xSql = """
               select Count(*) as x from News""";
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

    public List<News> getListNews(int page, int PPP) {
        List<News> t = new ArrayList<>();
        int start= PPP*(page-1)+1;
        int end= PPP*page;
        xSql = """
               WITH x AS(SELECT ROW_NUMBER() OVER(ORDER BY newsId DESC) AS r
                              ,* FROM News)
                              SELECT x.newsId, Users.userName, x.title, x.image, x.content, CONVERT(date, x.postedTime) AS postedDate, PriorityLevel.priorityName 
                              FROM x
                              JOIN Users ON Users.userId = x.userId
                              JOIN PriorityLevel ON PriorityLevel.priorityId = x.priorityId
                              								  where r between ? and ?""";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, start);
            ps.setInt(2, end);
            rs = ps.executeQuery();
            while (rs.next()) {
                int xnewsId = rs.getInt("newsId");
                String xuserName = rs.getString("userName");
                String xtitle = rs.getString("title");
                String ximage = rs.getString("image");
                String xcontent = rs.getString("content");
                String xpostTime = rs.getString("postedDate");
                String xpriorityName = rs.getString("priorityName");
                News x = new News(xnewsId, xuserName, xtitle, ximage, xcontent, xpostTime, xpriorityName);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
        }
        return (t);
    }
    public List<News> getListNews() {
        List<News> t = new ArrayList<>();
        xSql = "select * from News";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int xnewsId = rs.getInt("newsId");
                String xuserName = rs.getString("userId");
                String xtitle = rs.getString("title");
                String ximage = rs.getString("image");
                String xcontent = rs.getString("content");
                String xpostTime = rs.getString("postedDate");
                String xpriorityName = rs.getString("priorityName");
                News x = new News(xnewsId, xuserName, xtitle, ximage, xcontent, xpostTime, xpriorityName);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
        }
        return (t);
    }
    

    public List<News> searchNews(String xInput) {
        List<News> t = new ArrayList<>();
        xSql = "\n" +
"select News.newsId, Users.userName, title, content, image, postedTime, priorityName\n" +
"from News join Users on News.userId = Users.userId\n" +
"join PriorityLevel on News.priorityId = PriorityLevel.priorityId\n" +
"where title like '%"+xInput+"%' or title like N'%"+xInput+"%'";
        try {
            ps = con.prepareStatement(xSql);

            rs = ps.executeQuery();
            while (rs.next()) {
                int xnewsId = rs.getInt("newsId");
                String xuserName = rs.getString("userName");
                String xtitle = rs.getString("title");
                String ximage = rs.getString("image");
                String xcontent = rs.getString("content");
                String xpostTime = rs.getString("postedTime");
                String xpriorityName = rs.getString("priorityName");
                News x = new News(xnewsId, xuserName, xtitle, ximage, xcontent, xpostTime, xpriorityName);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
        }
        return (t);
    }

    public List<News> orderBy(String xColName, String xSortType) {
        List<News> t = new ArrayList<>();
        xSql = "SELECT News.newsId, Users.userName, News.title, News.image, News.content, CONVERT(date, News.postedTime) AS postedDate, PriorityLevel.priorityName \n" +
"FROM News\n" +
"JOIN Users ON Users.userId = News.userId\n" +
"JOIN PriorityLevel ON PriorityLevel.priorityId = News.priorityId order by [" + xColName + "] " + xSortType;
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int xnewsId = rs.getInt("newsId");
                String xuserName = rs.getString("userName");
                String xtitle = rs.getString("title");
                String ximage = rs.getString("image");
                String xcontent = rs.getString("content");
                String xpostTime = rs.getString("postedDate");
                String xpriorityName = rs.getString("priorityName");
                News x = new News(xnewsId, xuserName, xtitle, ximage, xcontent, xpostTime, xpriorityName);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
        }
        return (t);
    }

    public List<News> searchByPriority(String xType) {
        List<News> t = new ArrayList<>();
        xSql = "SELECT News.newsId, Users.userName, News.title, News.image, News.content, CONVERT(date, News.postedTime) AS postedDate, PriorityLevel.priorityName \n" +
"FROM News\n" +
"JOIN Users ON Users.userId = News.userId\n" +
"JOIN PriorityLevel ON PriorityLevel.priorityId = News.priorityId where PriorityLevel.priorityName like'%" + xType + "%'";
        try {
            ps = con.prepareStatement(xSql);

            rs = ps.executeQuery();
            while (rs.next()) {
                int xnewsId = rs.getInt("newsId");
                String xuserName = rs.getString("userName");
                String xtitle = rs.getString("title");
                String ximage = rs.getString("image");
                String xcontent = rs.getString("content");
                String xpostTime = rs.getString("postedDate");
                String xpriorityName = rs.getString("priorityName");
                News x = new News(xnewsId, xuserName, xtitle, ximage, xcontent, xpostTime, xpriorityName);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
        }
        return (t);
    }

    public void insert(News x) {
        xSql = "INSERT INTO [News] ([userId], [title], [image], [content], [postedTime], [priorityId])\n" +
"VALUES \n" +
"    (?, ?, ?, ?, GETDATE(), ?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, x.getUserName());
            ps.setString(2, x.getTitle());
            ps.setString(3, x.getImage());
            ps.setString(4, x.getContent());
            ps.setString(5, x.getPriorityName());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
        }
    }

    public void update(int newsId, News x) {
        xSql = "UPDATE [News]\n" +
"SET [userId]=?, [title] = ?, [content] = ?,[postedTime]=GETDATE(), [priorityId] = ?\n" +
"WHERE [newsId] = ?;";
         try {      
            ps = con.prepareStatement(xSql);
            ps.setString(1, x.getUserName());
            ps.setString(2, x.getTitle());
            ps.setString(3, x.getContent());
            ps.setString(4, x.getPriorityName());
            ps.setInt(5, newsId);
            ps.executeUpdate();
            ps.close();
         }
          catch(SQLException e) {
          }
    }

    public News getNews(int xId) {
        News x = null;
        xSql = "SELECT News.newsId, Users.userName, News.title, News.image, News.content, CONVERT(date, News.postedTime) AS postedDate, PriorityLevel.priorityName \n" +
"FROM News\n" +
"JOIN Users ON Users.userId = News.userId\n" +
"JOIN PriorityLevel ON PriorityLevel.priorityId = News.priorityId where newsId = ?"; 
        try {
         ps = con.prepareStatement(xSql);
         ps.setInt(1, xId);
         rs = ps.executeQuery();
         if(rs.next()){
            String xuserName = rs.getString("userName");
            String xtitle = rs.getString("title");
            String xcontent = rs.getString("content");
            String ximage = rs.getString("image");
            String xpostTime = rs.getString("postedDate");
            String xpriorityName = rs.getString("priorityName");
            x = new News(xId, xuserName, xtitle, ximage, xcontent, xpostTime, xpriorityName);
         }
         rs.close();
         ps.close();
        }     
        catch(SQLException e) {
        }
        return(x);
    }
    public static void main(String[] args) {
        NewsDAO d = new NewsDAO();
        List<News> x= d.searchNews("");
        System.out.println(x);
        
    }

    public void updateImg(News x) {
        xSql = "update News set [image]=? where newsId = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, x.getImage());
            ps.setInt(2, x.getNewsId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
        }
    }

    
    
}
