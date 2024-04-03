/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.Users;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import model.Category.CategoryDAO;
import model.News.NewsDAO;
import model.Orders.Orders;
import model.Orders.OrdersDAO;
import model.Product.Product;
import model.Product.ProductDAO;
import model.Users.Users;
import model.Users.UsersDAO;

/**
 *
 * @author Admin
 */
public class SalerDashboard extends HttpServlet {
   
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
            out.println("<title>Servlet SalerDashboard</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SalerDashboard at " + request.getContextPath () + "</h1>");
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
        doPost(request, response);
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
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(false);
        OrdersDAO u = new OrdersDAO();
        ProductDAO p = new ProductDAO();
        UsersDAO user= new UsersDAO();
        NewsDAO n= new NewsDAO();
        CategoryDAO c= new CategoryDAO();
        List<Product> lstP = p.getListProduct();
        Users current = new Users();
        if (session.getAttribute("user") != null) {
            current = (Users) session.getAttribute("user");
        }
        session.setAttribute("lstPro", lstP);
        int countAllOrder= u.CountOrderSaler(current.getUserId());
        int countOrderWaiting= u.CountOrderSalerWaiting(current.getUserId());
        session.setAttribute("orderwaiting", countOrderWaiting);
        session.setAttribute("orderInProcess", countAllOrder-countOrderWaiting);
        List<Orders> lst = u.getListOrdersBySalerId(1, 10000, current.getUserId());
        session.setAttribute("lstOrders", lst);
        int lstU = user.CountUsers();
        int lstN= n.CountNews();
        int lstC= c.CountCate();
        session.setAttribute("noUser", lstU);
        session.setAttribute("noNews", lstN);
        session.setAttribute("noCate", lstC);
        int year=0;
        if(request.getParameter("year")==null){
            year= 2024;
        }else{
            year= Integer.parseInt(request.getParameter("year"));
        }
        session.setAttribute("year", year);
        
        int[] lstInt = new int[12];
        for(int i=1; i<=12; i++){
            int m= u.grandTotalByMonthSaler(i, year, current.getUserId());
            lstInt[i-1]= m;
        }
        session.setAttribute("lstOrders", lst);
        session.setAttribute("lstInt", lstInt);
        response.sendRedirect("/CoffeeLand/server/Users/SalerDashboard.jsp");
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
