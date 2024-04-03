/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.HashSet;
import model.Category.CategoryDAO;
import model.Feedback.FeedbackDAO;
import model.News.NewsDAO;
import model.Orders.OrdersDAO;
import model.Product.ProductDAO;
import model.Users.UsersDAO;
import model.Voucher.VoucherDAO;

/**
 *
 * @author acer
 */
public class MarketerDashboard extends HttpServlet {
   
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MarketerDashboard</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MarketerDashboard at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
//        PrintWriter pr = response.getWriter();
        HttpSession session = request.getSession(false);
        ProductDAO proDAO = new ProductDAO();
        CategoryDAO catDAO = new CategoryDAO();
        UsersDAO uDAO = new UsersDAO();
        NewsDAO nDAO = new NewsDAO();
        VoucherDAO vDAO = new VoucherDAO();
        FeedbackDAO fDAO = new FeedbackDAO();
        
        session.setAttribute("countProduct", proDAO.getListProductServer().size());
        session.setAttribute("countCategory", catDAO.getListCategory().size());
        session.setAttribute("countCustomer", uDAO.getListUserByRole("Customer").size());
        session.setAttribute("countNews", nDAO.CountNews());
        session.setAttribute("countVoucher", vDAO.getListVoucher().size());
        session.setAttribute("countFeedback", fDAO.getListFeedback().size());
        
        session.setAttribute("topProduct", proDAO.getTopProduct());
        session.setAttribute("topProductQuantity", proDAO.getTopProductQuantity());
        
        session.setAttribute("TopCategory", proDAO.getTopCategory());
        session.setAttribute("TopCategoryQuantity", proDAO.getTopCategoryQuantity());
        
        session.setAttribute("TopFeedBack", proDAO.getTopFeedBack());
        session.setAttribute("TopFeedBackQuantity", proDAO.getTopFeedBackQuantity());
        
        response.sendRedirect("/CoffeeLand/server/MarketerDashboard.jsp");
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
