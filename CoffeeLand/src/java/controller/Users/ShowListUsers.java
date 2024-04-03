package controller.Users;

import model.Users.UsersDAO;
import model.Users.Users;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.util.*;
public class ShowListUsers extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(false);
//        PrintWriter pr = response.getWriter();
        UsersDAO u = new UsersDAO();
        int PPP=7;
        int noPage= u.CountUsers()/PPP+1;
        int page=1;

        String xpage = request.getParameter("page");
        if(xpage!= null){
            page=Integer.parseInt(xpage);
        }
        
        session.setAttribute("pageType", "list");
        session.setAttribute("currentPage", page);
        session.setAttribute("totalPages", noPage);
        
        List<Users> lst = u.getListUsers(page, PPP);
        session.setAttribute("lstUsers", lst);
        List<Users> lstRole = u.getListRoleName();
        session.setAttribute("lstRoles", lstRole);
        response.sendRedirect("/CoffeeLand/server/Users/UserManage.jsp");
    }
    
}
