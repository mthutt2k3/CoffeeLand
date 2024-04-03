package controller.Users;

/**
 * this function use for both password and information update
 */
import model.Users.UsersDAO;
import model.Users.Users;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.util.List;
import model.Category.Category;
import model.Category.CategoryDAO;
import model.Product.Product;
import model.Product.ProductDAO;

public class UpdateUser extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pr = response.getWriter();
        HttpSession session = request.getSession(false);
        Users rootUser = new Users();
        if (session.getAttribute("user") != null) {
            rootUser = (Users) session.getAttribute("user");
        }
        Users current = new Users();
        if (session.getAttribute("x") != null) {
            current = (Users) session.getAttribute("x");
        }
        String userName = request.getParameter("userName");
        String userId = request.getParameter("userId");
        String status = request.getParameter("status");

        String name = request.getParameter("name").trim();
        String phone = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        String password = request.getParameter("password").trim();
        String address = request.getParameter("address");
        String role = request.getParameter("role");
        if(role.equals("Admin")){
            role="1";
        }
        if(role.equals("Marketer")){
            role="2";
        }
        if(role.equals("Saler")){
            role="3";
        }
        if(role.equals("Customer")){
            role="4";
        }
        if(role.equals("Saler Manager")){
            role="5";
        }
        UsersDAO c = new UsersDAO();
        List<Users> clst = c.getListRoleName();
        session.setAttribute("lstRole", clst);
        Users x = new Users(userName, name, phone, email, password, address, "xxx", role, "1");
        if (!phone.matches("^0\\d{9}$")) {
            String alert = "Số điện thoại phải bắt đầu bằng số 0 và chứa 10 số";
            
            session.setAttribute("alert", alert);
            String url = "/CoffeeLand/server/Users/UpdateUser.jsp";
            response.sendRedirect(url);
        } else if (!email.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
            String alert = "Email phải có dạng ***@***.**";
            
            session.setAttribute("alert", alert);
            String url = "/CoffeeLand/server/Users/UpdateUser.jsp";
            response.sendRedirect(url);
        } else if (password.length() < 8) {
            String alert = "Password phải có ít nhất 8 ký tự";
            session.setAttribute("x", x);
            session.setAttribute("alert", alert);
            String url = "/CoffeeLand/server/Users/UpdateUser.jsp";
            response.sendRedirect(url);
        }else if (!current.getEmail().equalsIgnoreCase(email) && c.isExistEmail(email, role)) {
                String alert = "Email đã tồn tại";
                session.setAttribute("alert", alert);
                String url = "/CoffeeLand/server/Users/UpdateUser.jsp";
                response.sendRedirect(url);

        }else if (!current.getPhoneNumber().equalsIgnoreCase(phone) && c.isExistPhone(phone, role)) {
                String alert = "Số điện thoại đã tồn tại";
                session.setAttribute("alert", alert);
                String url = "/CoffeeLand/server/Users/UpdateUser.jsp";
                response.sendRedirect(url);
            

        }  else{
            
            c.update(userId, x);
            String alert = "Cập nhật người dùng thành công";
            if (rootUser.getRoleId().equalsIgnoreCase("Admin") || rootUser.getRoleId().equalsIgnoreCase("1")) {
                session.setAttribute("alert", alert);
                session.setAttribute("user", rootUser);
                response.sendRedirect("showlistusers");
            }

            if (rootUser.getRoleId().equalsIgnoreCase("Maketer") || rootUser.getRoleId().equalsIgnoreCase("2")) {
                session.setAttribute("alert", alert);
                session.setAttribute("user", rootUser);
                response.sendRedirect("usermanageforsaler");
            }
        }
        

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        UsersDAO c = new UsersDAO();
        String id = request.getParameter("userId");
        session.setAttribute("x", c.getUsersById(Integer.parseInt(id)));
        String url = "/CoffeeLand/server/Users/UpdateUser.jsp";
        response.sendRedirect(url);
    }
}
