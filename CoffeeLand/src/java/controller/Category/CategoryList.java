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
import model.Product.Product;
import model.Product.ProductDAO;

/**
 *
 * @author acer
 */
public class CategoryList extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        CategoryDAO u = new CategoryDAO();
        int PPP = 7; // Số lượng sản phẩm trên mỗi trang

        // Lấy tham số trang từ request
        int page = 1;
        String xpage = request.getParameter("page");
        if (xpage != null) {
            page = Integer.parseInt(xpage);
        }

        // Lấy tổng số trang
        int totalPages = 0;
        if(u.CountCate()%PPP==0){
            totalPages = u.CountCate() / PPP;
        }else{
            totalPages = u.CountCate() / PPP + 1;
        }

        // Lấy danh sách danh mục sản phẩm cho trang hiện tại
        List<Category> lstCat = u.getListCategory(page, PPP);

        // Lưu các giá trị vào session để sử dụng trong JSP
        session.setAttribute("lstCategory", lstCat);
        session.setAttribute("currentPage", page);
        session.setAttribute("totalPages", totalPages);

        // Redirect tới trang JSP hiển thị danh sách danh mục sản phẩm
        response.sendRedirect("/CoffeeLand/server/category/CategoryManage.jsp");
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

    private int maxId(List<Category> lstCat) {
        int result = lstCat.get(0).getCategoryId();
        for(int i=1;i<lstCat.size()+1;i++){
            if(lstCat.get(i).getCategoryId()>result){
                result = lstCat.get(i).getCategoryId();
            }
        }
        return result;
    }

}
