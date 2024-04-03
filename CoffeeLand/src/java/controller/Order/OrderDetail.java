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
import java.util.ArrayList;
import java.util.List;
import model.OrderDetail.*;
import model.Orders.Orders;
import model.Orders.OrdersDAO;
import model.OrderDetail.OrderDetailDAO;
import model.ProductSaler.ProductSaler;
import model.ProductSaler.ProductSalerDAO;

/**
 *
 * @author Admin
 */
public class OrderDetail extends HttpServlet {
   
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
            out.println("<title>Servlet OrderDetail</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderDetail at " + request.getContextPath () + "</h1>");
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
        PrintWriter pr = response.getWriter();
        HttpSession session = request.getSession(false);
        String status = request.getParameter("status");
        if(status.equals("0")){
            response.sendRedirect("/CoffeeLand/server/Order/OrderManage.jsp");
        }else{
            Orders rootOrder = (Orders) session.getAttribute("OrderDetail");
            OrdersDAO x = new OrdersDAO();
            x.UpdateStatus(rootOrder.getOrderId(), status);
            session.setAttribute("alert", "Cập nhật đơn hàng " + rootOrder.getOrderCode() + " thành công");
            response.sendRedirect("ordermanage");
        }
        
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
        PrintWriter pr = response.getWriter();
        OrdersDAO u = new OrdersDAO();
        String id = request.getParameter("orderId");
        Orders lst = u.getOrdersById(id);
        DecimalFormat decimalFormat = new DecimalFormat("#,###");

        // Định dạng số và lưu vào chuỗi
        String formattedNumber = decimalFormat.format(Integer.parseInt(lst.getTotalAmount()));
                lst.setTotalAmount(formattedNumber);
                
                
                String formattedNumbery = decimalFormat.format(Integer.parseInt(lst.getGrandTotal()));
                lst.setGrandTotal(formattedNumbery);
        // Định dạng số và lưu vào chuỗi
        String formattedNumberx;
        formattedNumberx = decimalFormat.format(Integer.parseInt(lst.getDiscountAmount()));
                lst.setDiscountAmount(formattedNumberx);
                
        // Định dạng số và lưu vào chuỗi
        
        OrderDetailDAO od= new OrderDetailDAO();
        List<OrderDetailEntity> xOrderDetails= od.getListProductByOrderId(id);
        List<ProductSaler> listProductSalers= new ArrayList<>();
        ProductSalerDAO proDetail= new ProductSalerDAO();
        for(OrderDetailEntity x: xOrderDetails){
            listProductSalers.add(proDetail.getProductById(x.getProductId(), id));
        }
        session.setAttribute("lstProductDetail", listProductSalers);
        session.setAttribute("OrderDetail", lst);
        response.sendRedirect("/CoffeeLand/server/Order/OrderDetail.jsp");
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
