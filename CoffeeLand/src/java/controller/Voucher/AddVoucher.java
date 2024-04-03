/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.Voucher;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import model.Users.Users;
import model.Voucher.Voucher;
import model.Voucher.VoucherDAO;

/**
 *
 * @author acer
 */
public class AddVoucher extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pr = response.getWriter();
        HttpSession session = request.getSession(false);
        Users xUser = new Users();
        if (session.getAttribute("user") != null) {
           xUser = (Users) session.getAttribute("user");
        } else {
            response.sendRedirect("/CoffeeLand/server/Users/login.jsp");
        }
        String xvoucherName = request.getParameter("voucherName");
        String xvoucherCode_str = request.getParameter("voucherCode");
        String xvoucherCode = xvoucherCode_str.toUpperCase();
        String xdiscount = request.getParameter("discount");
        String xdescription = request.getParameter("description");
        String xcondition = request.getParameter("condition");
        String xstartedDate = request.getParameter("startedDate");
        String xexpirationDate = request.getParameter("expirationDate");
        String xuserId = request.getParameter("userId");
        String xvoucherImage = request.getParameter("thumbnail");
        
        Voucher x = new Voucher(xvoucherName, xvoucherCode, xvoucherImage, xdiscount, xdescription, xstartedDate, xexpirationDate, xuserId, xcondition);
        VoucherDAO vDAO = new VoucherDAO();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date xstartedDate_date = null;
        Date xexpirationDate_date = null;
        try {
            xstartedDate_date = sdf.parse(xstartedDate);
            xexpirationDate_date = sdf.parse(xexpirationDate);
            int discount_int = Integer.parseInt(xdiscount);
            int xcondition_int = Integer.parseInt(xcondition);
            
            x = new Voucher(xvoucherName, xvoucherCode, xvoucherImage, xdiscount, xdescription, xstartedDate, xexpirationDate, xuserId, xcondition);

            boolean isCodeExist = vDAO.isCodeExist(xvoucherCode);

            if (isCodeExist) {
                String alert = "Mã " + xvoucherCode + " đã tồn tại!";
                session.setAttribute("x", x);
                session.setAttribute("alert", alert);
                String url = "/CoffeeLand/server/voucher/AddVoucher.jsp";
                response.sendRedirect(url);
            }else if(discount_int<1 || discount_int>100){
                String alert = "Lượng giảm giá phải nằm trong khoảng từ 0 đến 100";
                session.setAttribute("x", x);
                session.setAttribute("alert", alert);
                String url = "/CoffeeLand/server/voucher/AddVoucher.jsp";
                response.sendRedirect(url);
            }else if(xcondition_int < 0){
                String alert = "Đơn hàng tối thiếu phải là số nguyên dương";
                session.setAttribute("x", x);
                session.setAttribute("alert", alert);
                String url = "/CoffeeLand/server/voucher/AddVoucher.jsp";
                response.sendRedirect(url);
            }else if(xstartedDate_date.after(xexpirationDate_date)){
                String alert = "Ngày bắt đầu phải trước ngày kết thúc";
                session.setAttribute("x", x);
                session.setAttribute("alert", alert);
                String url = "/CoffeeLand/server/voucher/AddVoucher.jsp";
                response.sendRedirect(url);
            }else{
                vDAO.insert(x);
                String alert = "Đã thêm mã giảm giá " + xvoucherName + " thành công!";
                session.setAttribute("x", x);
                session.removeAttribute("x");
                session.setAttribute("alert", alert);
                String url = "/CoffeeLand/server/voucher/VoucherManage.jsp";
                response.sendRedirect("voucherlist");
            }
        } catch (Exception e) {
            String alert = "Điều kiện và lượng giảm giá phải là số nguyên";
            session.setAttribute("x", x);
            session.setAttribute("alert", alert);
            String url = "/CoffeeLand/server/voucher/AddVoucher.jsp";
            response.sendRedirect(url);
        }
        
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
