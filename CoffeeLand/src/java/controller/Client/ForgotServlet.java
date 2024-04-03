/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Client;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Client.*;

/**
 *
 * @author Admin
 */
public class ForgotServlet extends HttpServlet {

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
            out.println("<title>Servlet ForgotServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ForgotServlet at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("/client/Forgot.jsp").forward(request, response);
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
        String to = request.getParameter("email");
        Email e = new Email();
        String code = e.generateRandomCode(6);
        String title = "Vui lòng xác nhận email từ Coffee Land";
        String subject = "<strong style=\"color: #ff0000;\">Mã Xác Minh Đặt Lại Mật Khẩu</strong>";
        String greeting = "Xin chào,<br><br>";
        String intro = "Bạn đã yêu cầu đặt lại mật khẩu của mình. Vui lòng sử dụng mã xác minh sau đây:<br><br>";
        String codeMessage = "<strong>Mã xác minh: </strong><span style=\"color: #ff0000;\">" + code + "</span><br><br>";
        String instructions = "Vui lòng nhập mã này trên trang xác minh để đặt lại mật khẩu của bạn.<br><br>";
        String closing = "Nếu bạn không yêu cầu đặt lại mật khẩu này, vui lòng bỏ qua email này.<br><br>";
        String signature = "Trân trọng,<br>Đội ngũ CoffeeLand";

        String body = greeting + intro + codeMessage + instructions + closing + signature;

        HttpSession session = request.getSession();
        session.setAttribute("storedCode", code);
        session.setAttribute("email", to);
        ClientDAO d = new ClientDAO();
        if (!d.checkUniqueEmail(to)) {
            e.sendEmail(to, body, title);
            request.getRequestDispatcher("/client/GetCode.jsp").forward(request, response);
        } else {
            request.setAttribute("e", "Email chưa được đăng ký");
            request.getRequestDispatcher("/client/Forgot.jsp").forward(request, response);
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
