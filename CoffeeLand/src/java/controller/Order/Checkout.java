/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Order;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Users.Users;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.sql.Timestamp;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.CartClient.CartClient;
import model.CartClient.CartClientDAO;
import model.OrderDetail.OrderDetailDAO;
import model.Orders.OrdersDAO;
import model.VoucherClient.VoucherClient;
import model.VoucherClient.VoucherClientDAO;

/**
 *
 * @author Admin
 */
public class Checkout extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Checkout</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Checkout at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(false);
        CartClientDAO ccDAO = new CartClientDAO();
        Users rootUser = (Users) session.getAttribute("user");
        String voucher = request.getParameter("voucherCode");

        {

            String selectedProductsJson = request.getParameter("selectedProducts");
            List<CartClient> xCartClientQuantity = new ArrayList<>();
            List<CartClient> xCartClients = new ArrayList<>();
            int bill = 0;
            if (selectedProductsJson != null) {
                Gson gson = new Gson();
                Type listType = new TypeToken<List<Map<String, String>>>() {
                }.getType();
                List<Map<String, String>> selectedProducts = gson.fromJson(selectedProductsJson, listType);
                for (Map<String, String> product : selectedProducts) {
                    String productId = product.get("productId");
                    String quantity = product.get("quantity");
                    String size = product.get("size");
                    CartClient x = new CartClient(productId, size, quantity);
                    xCartClientQuantity.add(x);

                    CartClient y = ccDAO.getProductInCartByUserIdProductId(rootUser.getUserId(), productId, size);

                    if (size.equals("S")) {
                        size = "1";
                    }
                    if (size.equals("M")) {
                        size = "2";
                    }
                    if (size.equals("L")) {
                        size = "3";
                    }
                    String price = y.getPrice();
                    String total = String.valueOf(Integer.parseInt(quantity) * Integer.parseInt(price));
                    bill += Integer.parseInt(total);
                    ccDAO.updateCheckout(rootUser.getUserId(), productId, size, quantity, price, total);

                    if (size.equals("1")) {
                        size = "S";
                    }
                    if (size.equals("2")) {
                        size = "M";
                    }
                    if (size.equals("3")) {
                        size = "L";
                    }
                    CartClient z = ccDAO.getProductInCartByUserIdProductId(rootUser.getUserId(), productId, size);

                    xCartClients.add(z);

                }
            }

            if (voucher.length() == 0) {

                VoucherClientDAO vou = new VoucherClientDAO();
                VoucherClient voucherClient = vou.getVoucher(Integer.parseInt("1"));
                session.setAttribute("voucher", voucherClient);
                session.setAttribute("lstQuantity", xCartClientQuantity);
                session.setAttribute("lstProCart", xCartClients);
                response.sendRedirect("/CoffeeLand/client/cart/Checkout.jsp");

            } else {
                VoucherClientDAO vou = new VoucherClientDAO();
                VoucherClient voucherClient = vou.getVoucherByCode(voucher);

                if (voucherClient == null) {
                    session.setAttribute("alert", "Mã giảm giá đã hết hạn hoặc không tồn tại!");
                    response.sendRedirect("/CoffeeLand/client/cart");
                } else {
                    if (bill <= Integer.parseInt(voucherClient.getCondition())) {
                        session.setAttribute("alert", "Đơn hàng không đủ giá trị tối thiểu");
                        response.sendRedirect("/CoffeeLand/client/cart");
                    } else {
                        session.setAttribute("voucher", voucherClient);
                        session.setAttribute("lstQuantity", xCartClientQuantity);
                        session.setAttribute("lstProCart", xCartClients);
                        response.sendRedirect("/CoffeeLand/client/cart/Checkout.jsp");
                    }
                }
            }
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(false);
        Users rootUser = (Users) session.getAttribute("user");
        VoucherClient voucherClient = (VoucherClient) session.getAttribute("voucher");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String chonxa = request.getParameter("chonxa");
        address = chonxa + ", " + address;
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        List<CartClient> lstProCart = (List<CartClient>) session.getAttribute("lstProCart");
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        OrdersDAO oDAO = new OrdersDAO();
        String uN = String.format("%06d", oDAO.getLastOrderId() + 1);
        String userName = "";
        userName = "ORD" + uN;
        int totalAmount = (int) session.getAttribute("totalAmount");
        int discount = (int) session.getAttribute("discount");
        int grandTotal = (int) session.getAttribute("grandTotal");
        oDAO.addToOrder(userName, rootUser.getUserId(), currentTime.toString(), "1", name, address, phoneNumber, String.valueOf(voucherClient.getVoucherId()), totalAmount, discount, grandTotal);

        for (CartClient cc : lstProCart) {

            OrderDetailDAO oddDAO = new OrderDetailDAO();
            String sizeString = cc.getSize();
            if (cc.getSize().equals("S")) {
                sizeString = "1";
            }
            if (cc.getSize().equals("M")) {
                sizeString = "2";
            }
            if (cc.getSize().equals("L")) {
                sizeString = "3";
            }

            oddDAO.addToOrderDetail(String.valueOf(oDAO.getLastOrderId()), cc.getProductId(), sizeString, cc.getQuantity(), cc.getPrice());
            CartClientDAO cartClientDAO = new CartClientDAO();
            cartClientDAO.deleteFromCart(String.valueOf(rootUser.getUserId()), cc.getProductId(), sizeString);
        }
        session.setAttribute("alert", "Đặt hàng thành công");
        String url="orderdetailforcus?orderId="+String.valueOf(oDAO.getLastOrderId());
        response.sendRedirect(url);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
