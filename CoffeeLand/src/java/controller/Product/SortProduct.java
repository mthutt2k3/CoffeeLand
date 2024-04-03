package controller.Product;

import model.Product.*;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.util.ArrayList;
import java.util.List;

public class SortProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter pr = response.getWriter();
        HttpSession session = request.getSession(false);
        ProductDAO u = new ProductDAO();
        String colName = request.getParameter("colName");
        String sortType = request.getParameter("sortType");
        List<Product> lst = new ArrayList<>();
        lst = u.orderBy(colName, sortType);
        String url = "/CoffeeLand/server/product/ProductManage.jsp";
        String alert = "Đã sắp xếp theo " + colName +" "+sortType;
        session.setAttribute("alert", alert);
        session.setAttribute("lstProduct", lst);
        response.sendRedirect(url);
    }
}
