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
import java.util.List;
import model.Users.Users;
import model.Users.UsersDAO;

/**
 *
 * @author Admin
 */
public class CreateUser extends HttpServlet {

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
            out.println("<title>Servlet CreateUser</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateUser at " + request.getContextPath() + "</h1>");
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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pr = response.getWriter();
        HttpSession session = request.getSession(false);

        String name = request.getParameter("userName").trim();
        String phone = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        String password = request.getParameter("password").trim();
        String address = request.getParameter("address");
        String cPassword = request.getParameter("confirmPassword").trim();
        String role = request.getParameter("role");
        
        UsersDAO c = new UsersDAO();
        List<Users> clst = c.getListRoleName();
        session.setAttribute("lstRole", clst);
        Users last= c.getLast();
        String uN= String.format("%04d", Integer.parseInt(last.getUserId()) + 1);
        String userName="";
        if(role.equals("1")){
            userName= "ADM" + uN;
        }
        if(role.equals("2")){
            userName= "MKT" + uN;
        }if(role.equals("3")){
            userName= "SAL" + uN;
        }if(role.equals("5")){
            userName= "SM" + uN;
        }
        Users x = new Users(userName, name, phone, email, password, address, "xxx", role, "1");
        if(!password.equals(cPassword)){
            String alert = "Mật khẩu nhập lại không đúng";
            session.setAttribute("x", x);
            session.setAttribute("alert", alert);
            String url = "/CoffeeLand/server/Users/CreateUser.jsp";
            response.sendRedirect(url);
        }
        else if (!phone.matches("^0\\d{9}$")) {
            String alert = "Số điện thoại phải bắt đầu bằng số 0 và chứa 10 số";
            session.setAttribute("x", x);
            session.setAttribute("alert", alert);
            String url = "/CoffeeLand/server/Users/CreateUser.jsp";
            response.sendRedirect(url);
        } else if (!email.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
            String alert = "Email phải có dạng ***@***.**";
            session.setAttribute("x", x);
            session.setAttribute("alert", alert);
            String url = "/CoffeeLand/server/Users/CreateUser.jsp";
            response.sendRedirect(url);
        } else if (password.length() < 8) {
            String alert = "Password phải có ít nhất 8 ký tự";
            session.setAttribute("x", x);
            session.setAttribute("alert", alert);
            String url = "/CoffeeLand/server/Users/CreateUser.jsp";
            response.sendRedirect(url);
        } else if (c.isExistEmail(email, role)) {
            String alert = "Email đã tồn tại";
            session.setAttribute("x", x);
            session.setAttribute("alert", alert);
            String url = "/CoffeeLand/server/Users/CreateUser.jsp";
            response.sendRedirect(url);
        } else if (c.isExistPhone(phone, role)) {
            String alert = "Số điện thoại đã tồn tại";
            session.setAttribute("x", x);
            session.setAttribute("alert", alert);
            String url = "/CoffeeLand/server/Users/CreateUser.jsp";
            response.sendRedirect(url);
        } else {
            c.insert(x);
            String alert = "Tạo người dùng mới thành công!";
            session.setAttribute("alert", alert);
            String url = "showlistusers";
            response.sendRedirect(url);
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
