/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Feedback;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Feedback.Feedback;
import model.Feedback.FeedbackDAO;
import model.Product.Product;
import model.Product.ProductDAO;

/**
 *
 * @author NguyenDucTruong
 */
public class ProductFilterForFb extends HttpServlet {

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
            out.println("<title>Servlet ProductFilterForFb</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductFilterForFb at " + request.getContextPath() + "</h1>");
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
        ProductDAO productDAO = new ProductDAO();

        // Lấy tên sản phẩm từ request để lọc danh sách sản phẩm
        String productName = request.getParameter("productName");
        

        request.getRequestDispatcher("server/feedback/FeedbackManage.jsp").forward(request, response);
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
        String productName = request.getParameter("productName");
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        List<Feedback> product = feedbackDAO.getListProduct();
        request.setAttribute("listproduct", product);
        if (productName != null && !productName.isEmpty()) {

            // Lọc danh sách phản hồi theo tên sản phẩm
            List<Feedback> filteredFeedbackList = feedbackDAO.getFeedbacksByProductName(productName);

            // Set danh sách phản hồi đã lọc vào request để hiển thị trên trang
            request.setAttribute("lst", filteredFeedbackList);
            request.getRequestDispatcher("server/feedback/FeedbackManage.jsp").forward(request, response);
        }else{
            List<Feedback> listFeedback = feedbackDAO.getListFeedback();
            request.setAttribute("lst", listFeedback);
            request.setAttribute("listproduct", product);
            request.getRequestDispatcher("server/feedback/FeedbackManage.jsp").forward(request, response);
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
