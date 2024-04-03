package controller;


import model.Product.*;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.util.*;
import model.Users.Users;
import model.Users.UsersDAO;

public class Delete extends HttpServlet {
    /**
     * This function can delete an Users or a Cart 
     * @param request
     * @param response
     * @throws jakarta.servlet.ServletException
     * @throws java.io.IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(false);
        Users xUser = new Users();
        String sProductID = request.getParameter("productID");
        String productName = request.getParameter("productName");
        String url="", alert ="";
        String xEmail = request.getParameter("email");
        //Delete an Product
        if(sProductID != null){
            int productID = Integer.parseInt(sProductID);
            ProductDAO u = new ProductDAO();
            u.delete(productID);
            session.setAttribute("lstProduct", u.getListProduct());
            alert = "Đã xóa " + productName;
        }
        
        url = request.getHeader("referer");
        session.setAttribute("alert", alert);
        if (url != null && !url.isEmpty()) {
            response.sendRedirect(url);
        } else {
            response.sendRedirect("/CoffeeLand/server/product/ProductManage.jsp");
        }
    }
}
