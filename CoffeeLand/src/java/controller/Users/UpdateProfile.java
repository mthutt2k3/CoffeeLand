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
import model.Category.Category;
import model.Category.CategoryDAO;
import model.Product.Product;
import model.Product.ProductDAO;
import model.Users.Users;
import model.Users.UsersDAO;

/**
 *
 * @author Admin
 */
public class UpdateProfile extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pr = response.getWriter();
        HttpSession session = request.getSession(false);
        Users rootUser = new Users();
        if (session.getAttribute("user") != null) {
            rootUser = (Users) session.getAttribute("user");
        }

        //productName, originalPrice, retailPrice, image, description, categoryName
        String name = request.getParameter("userName");
        String phone = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String image = request.getParameter("image");
        String role = request.getParameter("role");
        if (image == null || image.length() == 0) {
            image = "null.png";
        }
        UsersDAO c = new UsersDAO();
        List<Users> clst = c.getListRoleName();
        session.setAttribute("lstRole", clst);

        Users x = new Users(name, phone, email, password, address, image, role);

        if (!phone.matches("^0\\d{9}$")) {
            String alert = "Số điện thoại phải bắt đầu bằng số 0 và có 10 số";
            session.setAttribute("x", x);
            session.setAttribute("alert", alert);
            String url = "/CoffeeLand/server/Users/UpdateUser.jsp";
            response.sendRedirect(url);
        } else if (!email.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
            String alert = "Email phải có dạng ***@***.**";
            session.setAttribute("x", x);
            session.setAttribute("alert", alert);
            String url = "/CoffeeLand/server/Users/UpdateUser.jsp";
            response.sendRedirect(url);
        } else if (password.length() < 8) {
            String alert = "Password phải có ít nhất 8 ký tự";
            session.setAttribute("x", x);
            session.setAttribute("alert", alert);
            String url = "/CoffeeLand/server/Users/UpdateUser.jsp";
            response.sendRedirect(url);
        } else {
            c.update(email, x);
            String alert = "Cập nhật người dùng thành công";
            String url = "/CoffeeLand/server/Users/login.jsp";
            if (rootUser.getRoleId().equalsIgnoreCase("Admin") || rootUser.getRoleId().equalsIgnoreCase("1")) {
                session.setAttribute("user", x);
                session.setAttribute("alert", alert);
            response.sendRedirect("/CoffeeLand/common/ProfileServer.jsp");
            }
            if (rootUser.getRoleId().equalsIgnoreCase("Marketer") || rootUser.getRoleId().equalsIgnoreCase("2")) {
                ProductDAO u = new ProductDAO();
                CategoryDAO z = new CategoryDAO();
                List<Product> lst = u.getListProduct();
                List<Category> catelst = z.getListCategory();
                session.setAttribute("lstProduct", lst);
                session.setAttribute("lstCategory", catelst);
                session.setAttribute("user", x);
                response.sendRedirect("/CoffeeLand/common/ProfileServer.jsp");
            }
            if (rootUser.getRoleId().equalsIgnoreCase("Saler") || rootUser.getRoleId().equalsIgnoreCase("3")) {
                
                session.setAttribute("user", x);
                session.setAttribute("alert", alert);
                response.sendRedirect("/CoffeeLand/common/ProfileServer.jsp");
            }
            if (rootUser.getRoleId().equalsIgnoreCase("Saler Manager") || rootUser.getRoleId().equalsIgnoreCase("5")) {
                
                session.setAttribute("user", x);
                session.setAttribute("alert", alert);
                response.sendRedirect("/CoffeeLand/common/ProfileServer.jsp");
            }
            if (rootUser.getRoleId().equalsIgnoreCase("Customer") || rootUser.getRoleId().equalsIgnoreCase("4")) {
                
                session.setAttribute("user", x);
                session.setAttribute("alert", alert);
                response.sendRedirect("/CoffeeLand/common/Profile.jsp");
            }
            session.setAttribute("alert", alert);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        UsersDAO c = new UsersDAO();
        String email = request.getParameter("email");
        String role = request.getParameter("role");
        session.setAttribute("x", c.getUsersByEmail(email, role));
        String url = "/CoffeeLand/server/Users/Profile.jsp";
        response.sendRedirect(url);
    }
}
