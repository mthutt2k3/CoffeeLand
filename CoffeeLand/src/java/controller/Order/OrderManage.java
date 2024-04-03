/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Order;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.List;
import model.Orders.Orders;
import model.Orders.OrdersDAO;
import model.Users.Users;
import model.Users.UsersDAO;

/**
 *
 * @author Admin
 */
public class OrderManage extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
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
            out.println("<title>Servlet OrderManage</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderManage at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(false);
//        PrintWriter pr = response.getWriter();
UsersDAO x = new UsersDAO();
        Users xUser = (Users) session.getAttribute("user");
        OrdersDAO u = new OrdersDAO();
        int PPP = 7;
        int noPage = u.CountOrder(xUser.getUserId()) / PPP + 1;
        int page = 1;

        String xpage = request.getParameter("page");
        if (xpage != null) {
            page = Integer.parseInt(xpage);
        }
        session.setAttribute("currentPage", page);
        session.setAttribute("totalPages", noPage);
        
        if (xUser.isSaler()) {
            List<Users> lstSaler = x.getListSaler();
            List<Orders> lst = u.getListOrdersBySalerId(page, PPP, xUser.getUserId());
            for(Orders cha : lst){
                DecimalFormat decimalFormat = new DecimalFormat("#,###");

        // Định dạng số và lưu vào chuỗi
        String formattedNumber = decimalFormat.format(Integer.parseInt(cha.getGrandTotal()));
                cha.setGrandTotal(formattedNumber);
            }
            session.setAttribute("pageType", "list");
            session.setAttribute("lstSaler", lstSaler);
            session.setAttribute("lstOrder", lst);
        }
        if (xUser.isSalerManager()) {
            session.setAttribute("pageType", "list");
            List<Users> lstSaler = x.getListSaler();
            List<Orders> lst = u.getListOrdersByStatus(page, PPP, "1");
            for(Orders cha : lst){
                DecimalFormat decimalFormat = new DecimalFormat("#,###");

        // Định dạng số và lưu vào chuỗi
        String formattedNumber = decimalFormat.format(Integer.parseInt(cha.getGrandTotal()));
                cha.setGrandTotal(formattedNumber);
            }
            session.setAttribute("lstSaler", lstSaler);
            session.setAttribute("lstOrder", lst);
        }

        response.sendRedirect("/CoffeeLand/server/Order/OrderManage.jsp");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pr = response.getWriter();
        HttpSession session = request.getSession(false);
        String status = request.getParameter("status");
                String salerId = request.getParameter("salerId");

        String id = request.getParameter("orderId");
        String code = request.getParameter("orderCode");
        Users xUser = (Users) session.getAttribute("user");
        if (xUser.isSaler()) {
            if (status.equals("0")) {
                response.sendRedirect("/CoffeeLand/server/Order/OrderManage.jsp");
            } else {
                OrdersDAO x = new OrdersDAO();
                x.UpdateStatus(id, status);
                session.setAttribute("alert", "Cập nhật đơn hàng " + code + " thành công");
                doGet(request, response);
            }
        }
        if (xUser.isSalerManager()) {
            if (salerId.equals("0")) {
                response.sendRedirect("/CoffeeLand/server/Order/OrderManage.jsp");
            } else {
                OrdersDAO x = new OrdersDAO();
                x.UpdateSalerOrder(id, salerId);
                session.setAttribute("alert", "Cập nhật đơn hàng " + code + " thành công");
                doGet(request, response);
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
