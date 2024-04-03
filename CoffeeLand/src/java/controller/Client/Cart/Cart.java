/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.Client.Cart;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.List;
import model.CartClient.CartClient;
import model.CartClient.CartClientDAO;
import model.Orders.Orders;
import model.VoucherClient.VoucherClient;
import model.VoucherClient.VoucherClientDAO;
import model.Users.Users;

/**
 *
 * @author acer
 */
public class Cart extends HttpServlet {
   
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
            String quatity = request.getParameter("quantity");
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Cart</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println(quatity);
            out.println("<h1>Servlet Cart at " + request.getContextPath () + "</h1>");
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
        HttpSession session = request.getSession(false);
        CartClientDAO x = new CartClientDAO();
        Users rootUser = (Users) session.getAttribute("user");
        List<CartClient> lst= x.getProductInCartById(rootUser.getUserId());
        
        VoucherClientDAO vDAO = new VoucherClientDAO();
        List<VoucherClient> lstVoucher= vDAO.getListVoucher();
        session.setAttribute("lstVoucher", lstVoucher);
        session.setAttribute("lstProCart", lst);
        request.getRequestDispatcher("/client/cart/Cart.jsp").forward(request, response);
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
        String id = request.getParameter("productId");
        String size = request.getParameter("size");
        Users rootUser = (Users) session.getAttribute("user");
        CartClientDAO x = new CartClientDAO();
        if(size.equals("S")){
            size= "1";
        }
        if(size.equals("M")){
            size= "2";
        }
        if(size.equals("L")){
            size= "3";
        }
        x.deleteFromCart(rootUser.getUserId(), id, size);
        
        List<CartClient> lst= x.getProductInCartById(rootUser.getUserId());
        session.setAttribute("lstProCart", lst);
        request.getRequestDispatcher("/client/cart/Cart.jsp").forward(request, response);
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
