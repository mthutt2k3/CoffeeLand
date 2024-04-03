package controller;

import model.Product.*;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.text.DecimalFormat;
import java.util.*;
import model.Category.Category;
import model.Category.CategoryDAO;
import model.News.News;
import model.News.NewsDAO;
import model.Orders.Orders;
import model.Orders.OrdersDAO;
import model.Users.Users;
import model.Users.UsersDAO;
import model.Voucher.Voucher;
import model.Voucher.VoucherDAO;

public class Search extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
//        PrintWriter pr = response.getWriter();
        HttpSession session = request.getSession(false);
        String xInput = request.getParameter("input").trim();
        String xTarget = request.getParameter("target").trim();
        ProductDAO proDAO = new ProductDAO();
        CategoryDAO catDAO = new CategoryDAO();
        UsersDAO uDAO = new UsersDAO();
        NewsDAO nDAO = new NewsDAO();
        VoucherDAO vDAO = new VoucherDAO();
        OrdersDAO oDAO = new OrdersDAO();
        
        String url = "";
        switch (xTarget) {
            case "ProductManage":
                List<Product> lstPro = proDAO.getListProductByTxt(xInput);
                url = "/CoffeeLand/server/product/ProductManage.jsp";
                session.setAttribute("lstProduct", lstPro);
                response.sendRedirect(url);
                break;
            case "UserManage":
                int PPP = 7;
                int noPage = uDAO.CountUsers() / PPP + 1;
                int page = 1;

                String xpage = request.getParameter("page");
                if (xpage != null) {
                    page = Integer.parseInt(xpage);
                }
                session.setAttribute("pageType", "search");
                session.setAttribute("currentPage", page);
                session.setAttribute("totalPages", noPage);
                List<Users> lstUser = uDAO.searchUsers(xInput, page, 100000);
                url = "/CoffeeLand/server/Users/UserManage.jsp";
                session.setAttribute("inputsearch", xInput);
                session.setAttribute("lstUsers", lstUser);
                session.setAttribute("type", "search");
                response.sendRedirect(url);
                break;
            case "UserManageForSaler":
                List<Users> lstCus = uDAO.searchCustomer(xInput);
                url = "/CoffeeLand/server/Users/UserManageForSaler.jsp";
                session.setAttribute("lstCus", lstCus);
                response.sendRedirect(url);
                break;
            case "OrderHistory":
                
                String id = request.getParameter("customerId");
                List<Orders> lstOrderHistory = oDAO.searchOrderHistory(xInput, id);
                url = "/CoffeeLand/server/Order/OrderHistory.jsp";
                session.setAttribute("lstOrder", lstOrderHistory);
                response.sendRedirect(url);
                break;
            case "CategoryManage":
                List<Category> lstCat = catDAO.searchCategory(xInput);
                url = "/CoffeeLand/server/category/CategoryManage.jsp";
                session.setAttribute("lstCategory", lstCat);
                response.sendRedirect(url);
                break;
            case "Order":
                UsersDAO x = new UsersDAO();
        Users xUser = (Users) session.getAttribute("user");
        session.setAttribute("pageType", "search");
        
                List<Orders> lstOrder = oDAO.searchOrder(xInput, xUser.getUserId());
                for(Orders cha : lstOrder){
                DecimalFormat decimalFormat = new DecimalFormat("#,###");

        // Định dạng số và lưu vào chuỗi
        String formattedNumber = decimalFormat.format(Integer.parseInt(cha.getGrandTotal()));
                cha.setGrandTotal(formattedNumber);
            }
                url = "/CoffeeLand/server/Order/OrderManage.jsp";
                session.setAttribute("lstOrder", lstOrder);
                response.sendRedirect(url);
                break;
            case "NewsManage":
                List<News> lstNews = nDAO.searchNews(xInput);
                url = "/CoffeeLand/server/news/NewsManage.jsp";
                session.setAttribute("lstNews", lstNews);
                response.sendRedirect(url);
                break;
            case "VoucherManage":
                List<Voucher> lstVoucher = vDAO.searchVoucher(xInput);
                url = "/CoffeeLand/server/voucher/VoucherManage.jsp";
                session.setAttribute("lstVoucher", lstVoucher);
                response.sendRedirect(url);
                break;
            default:
                throw new AssertionError();
        }

    }
}
