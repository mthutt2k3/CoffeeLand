/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.Client.Product;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Category.Category;
import model.Category.CategoryDAO;
import model.Information.InformationDAO;
import model.Product.Product;
import model.Product.ProductDAO;

/**
 *
 * @author acer
 */
public class SearchProductList extends HttpServlet {
   
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
            out.println("<title>Servlet SearchProductList</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchProductList at " + request.getContextPath () + "</h1>");
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
        HttpSession session = request.getSession();
        InformationDAO info = new InformationDAO();
        ProductDAO pDAO = new ProductDAO();
        CategoryDAO cDAO = new CategoryDAO();
        
        String txtSearch = request.getParameter("textSearch");
        
        List<Product> plst = pDAO.getListProductByTxt(txtSearch);
        List<Category> clst = cDAO.getListCategory();
        
        session.setAttribute("txtSearch", txtSearch);
        session.setAttribute("top5Product", pDAO.get5Product());
        session.setAttribute("totalProduct", pDAO.getTotalProduct());
        session.setAttribute("info", info.getInformation("1"));
        session.setAttribute("lstProduct", plst);
        session.setAttribute("lstCategory", clst);
        session.setAttribute("maxPrice", pDAO.getMaxPrice());
        String url="/CoffeeLand/client/product/ProductList.jsp";
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
    }// </editor-fold>

}
