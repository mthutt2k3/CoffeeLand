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
import model.Voucher.Voucher;
import model.Voucher.VoucherDAO;

/**
 *
 * @author acer
 */
public class UpdateVoucher extends HttpServlet {
   
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateVoucher</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateVoucher at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        doPost(request, response);
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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pr = response.getWriter();
        HttpSession session = request.getSession(false);
        Voucher rootVoucher = new Voucher();
        if (session.getAttribute("user") == null) {
           response.sendRedirect("/CoffeeLand/server/Users/login.jsp");
        }
        if(session.getAttribute("x") != null){
            rootVoucher = (Voucher) session.getAttribute("x");
        }
        
        String xvoucherId  = request.getParameter("voucherId");
        String xvoucherName = request.getParameter("voucherName");
        String xvoucherCode_str = request.getParameter("voucherCode");
        String xdiscount = request.getParameter("discount");
        String xstartedDate = request.getParameter("startedDate");
        String xexpirationDate = request.getParameter("expirationDate");
        String xuserId = request.getParameter("userId");
        String xdescription = request.getParameter("description");
        String xcondition = request.getParameter("condition");
        
        String xvoucherImage = request.getParameter("thumbnail");
        
        Voucher x = new Voucher(xvoucherName, xvoucherName, xvoucherImage, xdiscount, xdescription, xstartedDate, xexpirationDate, xuserId, xcondition);
        VoucherDAO vDAO = new VoucherDAO();
        
        if(session.getAttribute("x") == null){
            int xId = Integer.parseInt(xvoucherId);
            x = vDAO.getVoucher(xId);
            
            session.setAttribute("x", x);
            session.setAttribute("filename", x.getVoucherImage());
            String url = "/CoffeeLand/server/voucher/UpdateVoucher.jsp";
            response.sendRedirect(url);
        }else{
            x = new Voucher(rootVoucher.getVoucherId(),xvoucherName, xvoucherCode_str, xvoucherImage, xdiscount, xdescription, xstartedDate, xexpirationDate, xuserId, xcondition);
            String xvoucherCode = xvoucherCode_str.toUpperCase();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date xstartedDate_date = null;
            Date xexpirationDate_date = null;
            try {
                xstartedDate_date = sdf.parse(xstartedDate);
                xexpirationDate_date = sdf.parse(xexpirationDate);
                int discount_int = Integer.parseInt(xdiscount);
                int condition_int = Integer.parseInt(xcondition);
                
                x = new Voucher(rootVoucher.getVoucherId(),xvoucherName, xvoucherCode, xvoucherImage, xdiscount, xdescription, xstartedDate, xexpirationDate, xuserId, xcondition);
                boolean isCodeExist = vDAO.isCodeExist(xvoucherCode);
                Voucher root = vDAO.getVoucher(x.getVoucherId());
                if(xvoucherCode.equalsIgnoreCase(root.getVoucherCode())){
                    isCodeExist = false;
                }

                if (isCodeExist) {
                    String alert = "Mã " + xvoucherCode + " đã tồn tại!";
                    session.setAttribute("x", x);
                    session.setAttribute("alert", alert);
                    String url = "/CoffeeLand/server/voucher/UpdateVoucher.jsp";
                    response.sendRedirect(url);
                }else if(discount_int<1 || discount_int>100){
                    String alert = "Lượng giảm giá phải nằm trong khoảng từ 0 đến 100";
                    session.setAttribute("x", x);
                    session.setAttribute("alert", alert);
                    String url = "/CoffeeLand/server/voucher/UpdateVoucher.jsp";
                    response.sendRedirect(url);
                }else if(condition_int < 0){
                    String alert = "Đơn hàng tối thiếu phải là số nguyên dương";
                    session.setAttribute("x", x);
                    session.setAttribute("alert", alert);
                    String url = "/CoffeeLand/server/voucher/UpdateVoucher.jsp";
                    response.sendRedirect(url);
                }else if(xstartedDate_date.after(xexpirationDate_date)){
                    String alert = "Ngày bắt đầu phải trước ngày kết thúc";
                    session.setAttribute("x", x);
                    session.setAttribute("alert", alert);
                    String url = "/CoffeeLand/server/voucher/UpdateVoucher.jsp";
                    response.sendRedirect(url);
                }else{
                    vDAO.update(rootVoucher.getVoucherId(), x);
                    String alert = "Cập nhật mã giảm giá " + x.getVoucherId()+ " thành công!";
                    String url = "/CoffeeLand/server/voucher/temp.jsp";
                    session.setAttribute("alert", alert);
                    session.setAttribute("x",x);
                    response.sendRedirect("voucherlist");
                }
            } catch (Exception e) {
                String alert = "Điều kiện và lượng giảm giá phải là số nguyên";
                session.setAttribute("x", x);
                session.setAttribute("alert", alert);
                String url = "/CoffeeLand/server/voucher/UpdateVoucher.jsp";
                response.sendRedirect(url);
            }
            
        }
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
