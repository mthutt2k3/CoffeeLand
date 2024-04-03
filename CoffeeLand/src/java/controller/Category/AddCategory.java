/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.Category;

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
import model.Users.Users;

/**
 *
 * @author acer
 */
public class AddCategory extends HttpServlet {
   
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
        PrintWriter pr = response.getWriter();
        HttpSession session = request.getSession(false);
        Users xUser = new Users();
        if (session.getAttribute("user") != null) {
           xUser = (Users) session.getAttribute("user");
        } else {
            response.sendRedirect("/CoffeeLand/server/Users/login.jsp");
        }
        String xcategoryName = request.getParameter("categoryName").trim();
        Category x = new Category(xcategoryName);
        CategoryDAO cDAO = new CategoryDAO();
        if ((xcategoryName)==null || xcategoryName.equals("")) {
            String alert = "Loại sản phẩm không được để trống!";
            session.setAttribute("x", x);
            session.setAttribute("alert", alert);
            String url = "/CoffeeLand/server/category/CategoryManage.jsp";
            response.sendRedirect(url);
        }else
        if (cDAO.isNameExist(xcategoryName)) {
            String alert = xcategoryName + " đã tồn tại!";
            session.setAttribute("x", x);
            session.setAttribute("alert", alert);
            String url = "/CoffeeLand/server/category/CategoryManage.jsp";
            response.sendRedirect(url);
        }
        else{
            cDAO.insert(x);
            String alert = "Tạo " + xcategoryName + " thành công!";
            session.setAttribute("alert", alert);
            String url = "/CoffeeLand/server/category/CategoryManage.jsp";
            response.sendRedirect("categorylist");
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
