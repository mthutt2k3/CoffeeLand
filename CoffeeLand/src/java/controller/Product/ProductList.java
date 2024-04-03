package controller.Product;

import model.Product.*;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.util.*;
import model.Category.*;
public class ProductList extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
//        PrintWriter pr = response.getWriter();
        ProductDAO u = new ProductDAO();
        CategoryDAO c = new CategoryDAO();
        
        
        int PPP = 7; // Số lượng sản phẩm trên mỗi trang

        // Lấy tham số trang từ request
        int page = 1;
        String xpage = request.getParameter("page");
        if (xpage != null) {
            page = Integer.parseInt(xpage);
        }

        // Lấy tổng số trang
        int totalPages = u.CountProduct()/ PPP + 1;
        
        List<Product> lst = u.getListProductServer(page, PPP);
        List<Category> clst = c.getListCategory(-1);
        // Lưu các giá trị vào session để sử dụng trong JSP
        session.setAttribute("currentPage", page);
        session.setAttribute("totalPages", totalPages);
        session.setAttribute("lstProduct", lst);
        session.setAttribute("lstCategory", clst);
        session.removeAttribute("priceS");
        session.removeAttribute("priceM");
        session.removeAttribute("priceL");
        session.removeAttribute("splst");
        String url="/CoffeeLand/server/product/ProductManage.jsp";
        response.sendRedirect(url);
    }
}
