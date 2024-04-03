package controller.Users;

import model.Users.*;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.util.List;

public class SortUsers extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter pr = response.getWriter();
        HttpSession session = request.getSession(false);
        UsersDAO u = new UsersDAO();
        String colName = request.getParameter("colName");
        String sortType = request.getParameter("sortType");
        String role = request.getParameter("role");

        String url;
        String alert;
        switch (role) {
            case "Admin":
                List<Users> lst = u.orderAdmin(colName, sortType);
                url = "/CoffeeLand/server/Users/UserManage.jsp";
                
                alert = "Đã sắp xếp theo " + colName +" "+ sortType;
                session.setAttribute("alert", alert);
                session.setAttribute("lstUsers", lst);
                session.setAttribute("type", "sort");
                session.setAttribute("currentPage", "1");
                response.sendRedirect(url);
                break;
            case "Saler":
                List<Users> lstCus = u.orderSaler(colName, sortType);
                url = "/CoffeeLand/server/Users/UserManageForSaler.jsp";
                alert = "Đã sắp xếp theo " + colName+" "+ sortType;
                session.setAttribute("alert", alert);
                session.setAttribute("lstCus", lstCus);
                session.setAttribute("currentPage", "1");
                session.setAttribute("type", "sort");
                response.sendRedirect(url);
                break;
        }
    }
}
