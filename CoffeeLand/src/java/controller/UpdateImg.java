/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

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
import model.Information.InformationDAO;
import model.Information.Informations;
import model.News.News;
import model.News.NewsDAO;

/**
 *
 * @author acer
 */
@MultipartConfig
public class UpdateImg extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            HttpSession session = request.getSession();
            String target = request.getParameter("target");
            Part part = request.getPart("photo");
            String realPath = request.getServletContext().getRealPath("/assests/image/thumbnails");
            String filename = Path.of(part.getSubmittedFileName()).getFileName().toString();

            if (!Files.exists(Path.of(realPath))) {
                Files.createDirectory(Path.of(realPath));
            }
            part.write(realPath + "/" + filename);
            session.setAttribute("filename", filename);
            switch (target) {
                case "UpdateNews":
                    String newsId = request.getParameter("newsId");
                    int newsId_int = Integer.parseInt(newsId);
                    News news = new News(newsId_int, filename);
                    NewsDAO ndao = new NewsDAO();
                    ndao.updateImg(news);
                    news = ndao.getNews(newsId_int);
                    session.setAttribute("x", news);
                    response.sendRedirect("/CoffeeLand/server/news/UpdateNews.jsp");
                    break;
                case "AddProduct":
                    response.sendRedirect("/CoffeeLand/server/product/AddProduct.jsp");
                    break;
                case "AddVoucher":
                    response.sendRedirect("/CoffeeLand/server/voucher/AddVoucher.jsp");
                    break;
                case "UpdateVoucher":
                    response.sendRedirect("/CoffeeLand/server/voucher/UpdateVoucher.jsp");
                    break;
                case "UpdateProduct":
                    response.sendRedirect("/CoffeeLand/server/product/UpdateProduct.jsp");
                    break;
                default:
                    throw new AssertionError();
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error uploading the file: " + e);
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
