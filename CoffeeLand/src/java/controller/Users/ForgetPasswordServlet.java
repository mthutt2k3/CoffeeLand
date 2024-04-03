/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Users;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.net.URLEncoder;
import model.Client.ClientDAO;
import model.Client.Email;
import model.Users.UsersDAO;

/**
 *
 * @author dell
 */
public class ForgetPasswordServlet extends HttpServlet {

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
            out.println("<title>Servlet ForgetPasswordServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ForgetPasswordServlet at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("/CoffeeLand/server/Users/ForgetPassword.jsp").forward(request, response);
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
        session.setAttribute("storedCodeS", code);
        session.setAttribute("email", to);
        UsersDAO d = new UsersDAO();
        if (!d.checkUniqueEmail(to)) {
            e.sendEmail(to, body, title);
            response.sendRedirect("/CoffeeLand/server/Users/GetCodeForget.jsp");
        } else {
            String notice = "Email chưa được đăng ký";
            response.sendRedirect("/CoffeeLand/server/Users/ForgetPassword.jsp?e=" + URLEncoder.encode(notice, "UTF-8"));
            
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
