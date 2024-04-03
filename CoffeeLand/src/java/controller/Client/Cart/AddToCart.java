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
import java.util.Arrays;
import model.CartClient.CartClient;
import model.CartClient.CartClientDAO;
import model.Users.Users;

/**
 *
 * @author Admin
 */
public class AddToCart extends HttpServlet {
   
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
            out.println("<title>Servlet AddToCart</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddToCart at " + request.getContextPath () + "</h1>");
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
        String id = request.getParameter("productId");
        String size = request.getParameter("selectedSize");
        String quantity = request.getParameter("quantity");
        Users xUser = new Users();
        xUser = (Users) session.getAttribute("user");
        CartClientDAO x= new CartClientDAO();
        if("S".equals(size)){
            size= "1";
        }
        if("M".equals(size)){
            size= "2";
        }
        if("L".equals(size)){
            size= "3";
        }
        String price= String.valueOf(x.getPrice(id, size));
        String total= String.valueOf(x.getPrice(id, size)*Integer.parseInt(quantity));
        x.addToCart(xUser.getUserId(), id, size, quantity, price, total);
        String[] cartSession = (String[]) session.getAttribute("cartSession");
        String[] updatedCart = Arrays.copyOf(cartSession, cartSession.length + 1);
        updatedCart[cartSession.length] = id;
        session.setAttribute("cartSession", updatedCart);
        session.setAttribute("alert", "Đã thêm sản phẩm vào giỏ hàng");
        String url="/CoffeeLand/client/productdetail?productId="+id;
        response.sendRedirect(url);
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
    }
}
