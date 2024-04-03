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
import model.Category.Category;
import model.Category.CategoryDAO;

/**
 *
 * @author acer
 */
public class UpdateCategory extends HttpServlet {
   
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
            out.println("<title>Servlet UpdateCategory</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateCategory at " + request.getContextPath () + "</h1>");
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

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter pr = response.getWriter();
        HttpSession session = request.getSession(false);
        Category rootCat = new Category();
        if (session.getAttribute("user") == null) {
           response.sendRedirect("/CoffeeLand/server/Users/login.jsp");
        }
        if(session.getAttribute("x") != null){
            rootCat = (Category) session.getAttribute("x");
        }
        String xCategoryId = request.getParameter("categoryId");
        String categoryName = request.getParameter("categoryName");
        
        CategoryDAO cDAO = new CategoryDAO();
        Category x;
        if(session.getAttribute("x") == null){
            int id = Integer.parseInt(xCategoryId);
            x = cDAO.getCategory(id);
            session.setAttribute("x", x);
            String url = "/CoffeeLand/server/category/UpdateCategory.jsp";
            response.sendRedirect(url);
        }else{
            
            x = new Category(rootCat.getCategoryId(),categoryName);
            boolean isNameExist = cDAO.isNameExist(categoryName);
            Category croot = cDAO.getCategory(rootCat.getCategoryId());
            if(categoryName.equals(croot.getCategoryName())){
                isNameExist = false; 
            }
            
            if (isNameExist) {
                String alert = categoryName + " đã tồn tại!";
                session.setAttribute("x", x);
                session.setAttribute("alert", alert);
                String url = "/CoffeeLand/server/category/UpdateCategory.jsp";
                response.sendRedirect(url);
            }
            else{
                cDAO.update(rootCat.getCategoryId(), x);
                String alert = "Cập nhật loại sản phẩm " + rootCat.getCategoryId()+ " thành công!";
                String url = "/CoffeeLand/server/category/CategoryManage.jsp";
                session.setAttribute("alert", alert);
                session.removeAttribute("x");
                response.sendRedirect("categorylist");
            }
        }
    
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
