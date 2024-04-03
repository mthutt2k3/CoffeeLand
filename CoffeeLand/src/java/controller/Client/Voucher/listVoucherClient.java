/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Client.Voucher;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Client.Voucher.VoucherClient;
import model.Client.Voucher.VoucherClientDAO;

/**
 *
 * @author NguyenDucTruong
 */
public class listVoucherClient extends HttpServlet {

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
            out.println("<title>Servlet listVoucherClient</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet listVoucherClient at " + request.getContextPath() + "</h1>");
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
        VoucherClientDAO db = new VoucherClientDAO();
        String indexPage = request.getParameter("index");
        if (indexPage == null) {
            indexPage = "1";
        }
        int index = Integer.parseInt(indexPage);
        List<VoucherClient> listVoucher = db.getListVoucher();
        request.setAttribute("lst", listVoucher);

        //get total voucher
        int count = db.getTotalVoucher();
        int endPage = count / 3;
        if (count % 3 != 0) {
            endPage++;
        }
        List<VoucherClient> list = db.pagingVoucher(index);

        request.setAttribute("lst", list);
        request.setAttribute("endP", endPage);

        request.getRequestDispatcher("client/voucher/ListVoucher.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        VoucherClientDAO n = new VoucherClientDAO();

        //filter by name
        String sName = request.getParameter("sName");
        List<VoucherClient> resultList = n.searchByName(sName);

        if (resultList.isEmpty()) {
            // Không có kết quả phù hợp
            request.setAttribute("message", "Không có kết quả phù hợp");
        } else {
            request.setAttribute("lst", resultList);
        }

        request.getRequestDispatcher("client/voucher/ListVoucher.jsp").forward(request, response);
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
