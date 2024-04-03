package controller.Users;

import model.Users.*;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import model.Information.InformationDAO;
import model.Information.Informations;
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.sendRedirect("/CoffeeLand/server/Users/login.jsp");
    } 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pr = response.getWriter();
        String xEmail = request.getParameter("email");
        String xPassword = request.getParameter("password");
        String xRole = request.getParameter("role");
        if(xEmail == null){
            xEmail = "";
        }
        if(xPassword == null){
            xPassword = "";
        }
        if(xRole == null){
            xRole = "";
        }
        
        UsersDAO dao = new UsersDAO();
        Users x = dao.getUsersByEmail(xEmail, xRole);
        String alert = "Email hoặc mật khẩu sai!";
        
        
        HttpSession session = request.getSession();
        
        if(x == null || !x.getPassword().equals(xPassword)){
            session.setAttribute("email", xEmail);
            session.setAttribute("alert", alert);
            response.sendRedirect("/CoffeeLand/server/Users/login.jsp");
        }else if(x.getStatusId().equals("2")){
            session.setAttribute("email", xEmail);
            session.setAttribute("alert", "Tài khoản này không được phép đăng nhập");
            response.sendRedirect("/CoffeeLand/server/Users/login.jsp");
        }else {
            InformationDAO infoDAO= new InformationDAO();
            Informations info = infoDAO.getInformation("1");
            session.setAttribute("info", info);
            if (x.getRoleId().equals("1")) {
                session.setAttribute("alert", "Đăng nhập thành công");
                session.setAttribute("user", x);
                response.sendRedirect("datastatistics");
            }
            if (x.getRoleId().equals("2")) {
                session.setAttribute("user", x);
                session.setAttribute("alert", "Đăng nhập thành công");
                response.sendRedirect("marketerdashboard");
            }
            if (x.getRoleId().equals("3")) {
                session.setAttribute("user", x);
                session.setAttribute("alert", "Đăng nhập thành công");
                response.sendRedirect("salerdashboard");
            }
            if (x.getRoleId().equals("5")) {
                session.setAttribute("user", x);
                session.setAttribute("alert", "Đăng nhập thành công");
                response.sendRedirect("salermanagerdashboard");
            }
            if (x.getRoleId().equals("4")) {
                session.setAttribute("alert", "Tài khoản của bạn không được phép đăng nhập!");
                response.sendRedirect("/CoffeeLand/server/Users/login.jsp");
            }
        }
    }
}
