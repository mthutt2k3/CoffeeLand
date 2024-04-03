package controller.Product;

import model.Product.*;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class GetProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter pr = response.getWriter();
        request.getRequestDispatcher("/server/product/Product.jsp").forward(request, response);
    }
}
