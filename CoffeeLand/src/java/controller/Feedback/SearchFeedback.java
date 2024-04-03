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

/**
 *
 * @author NguyenDucTruong
 */
public class SearchFeedback extends HttpServlet {

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
        try {
            
            String txtSearch = request.getParameter("txtSearch");
            String indexString = request.getParameter("index");
            int index = Integer.parseInt(indexString);

            FeedbackDAO fbdao = new FeedbackDAO();
            String countString = fbdao.count(txtSearch); // txtSearch là tham số bạn truyền vào để đếm số lượng phản hồi
            int count = Integer.parseInt(countString);

            int pageSize = 6;
            int endPage = 0;
            endPage = count / pageSize;
            if (count % pageSize != 0) {
                endPage++;
            }
            List<Feedback> listSearch = fbdao.search(txtSearch, index, pageSize);
            request.setAttribute("end", endPage);
            request.setAttribute("lst", listSearch);
            request.setAttribute("txtSearch", txtSearch);

        request.getRequestDispatcher("server/feedback/FeedbackManage.jsp").forward(request, response);
        } catch (Exception e) {
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
        processRequest(request, response);
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
        processRequest(request, response);
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
