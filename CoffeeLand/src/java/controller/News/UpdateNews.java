/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.News;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import model.News.News;
import model.News.NewsDAO;

/**
 *
 * @author acer
 */
@MultipartConfig
public class UpdateNews extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {            
            
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter pr = response.getWriter();
            HttpSession session = request.getSession(false);
            News rootNews = new News();
            if (session.getAttribute("x") != null) {
                rootNews = (News) session.getAttribute("x");
            }
            
            String xNewsId = request.getParameter("newsId");
            int xId = Integer.parseInt(xNewsId);
            String userId = request.getParameter("userId");
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            String priorityId = request.getParameter("priorityId");
            News x = new News();
            NewsDAO nDAO = new NewsDAO();
            if (session.getAttribute("x") == null) {
                x = nDAO.getNews(Integer.parseInt(xNewsId));
                session.setAttribute("x", x);
                String url = "/CoffeeLand/server/news/UpdateNews.jsp";
                response.sendRedirect(url);
            }else{
                x=new News(xId, userId, title, content, priorityId);
                nDAO.update(rootNews.getNewsId(),x);
                x=nDAO.getNews(Integer.parseInt(xNewsId));
                session.setAttribute("x", x);
                String alert = "Đã cập nhật tin tức thành công!";
                session.setAttribute("alert", alert);
                String url = "/CoffeeLand/server/news/NewsManage.jsp";
                response.sendRedirect("newslist");
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error uploading the file: " + e.getMessage());
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
