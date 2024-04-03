/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Income;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import model.Income.IncomeDAO;
import model.Orders.Orders;
import model.Users.Users;

/**
 *
 * @author dell
 */
public class SelectNameSalerServlet extends HttpServlet {

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
            out.println("<title>Servlet SelectNameSalerServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SelectNameSalerServlet at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("/CoffeeLand/server/income/IncomeManager.jsp").forward(request, response);
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
        Users user = (Users) session.getAttribute("user");
        if (user == null) {
            // Nếu userId chưa tồn tại, chuyển hướng đến trang đăng nhập
            response.sendRedirect("/CoffeeLand/server/Users/login.jsp");
        } else {
            String userId = request.getParameter("user");
            String date = request.getParameter("date");
            String monthlyMonth = request.getParameter("monthlyMonth");
            String yearlyYear = request.getParameter("yearlyYear");
            if (date.isEmpty() && monthlyMonth.isEmpty() && yearlyYear.isEmpty()) {
                IncomeDAO icd = new IncomeDAO();

                List<Orders> listO = icd.getOrderBySalerID(Integer.parseInt(userId));
                session.setAttribute("listOrders", listO);
                session.removeAttribute("listOrdersByDay");
                session.removeAttribute("listOrdersByMonth");
                session.removeAttribute("listOrdersByYear");
                response.sendRedirect("/CoffeeLand/server/income/IncomeManager.jsp");
            } else if (!date.isEmpty()) {
                IncomeDAO icd = new IncomeDAO();
                try {
                    java.sql.Date sqlDate = java.sql.Date.valueOf(date);

                    List<Orders> listO = icd.getOrderBySalerIDandDay(Integer.parseInt(userId), sqlDate);
                    session.setAttribute("listOrdersByDay", listO);
                    session.removeAttribute("listOrders");
                    session.removeAttribute("listOrdersByMonth");
                    session.removeAttribute("listOrdersByYear");
                    response.sendRedirect("/CoffeeLand/server/income/IncomeManager.jsp");
                } catch (Exception e) {
                }

            } else if (!monthlyMonth.isEmpty()) {
                IncomeDAO icd = new IncomeDAO();
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
                    java.util.Date utilDate = dateFormat.parse(monthlyMonth);
                    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

                    List<Orders> listO = icd.getOrderByMonthandYear(Integer.parseInt(userId), sqlDate);
                    session.setAttribute("listOrdersByMonth", listO);
                    session.removeAttribute("listOrders");
                    session.removeAttribute("listOrdersByDay");
                    session.removeAttribute("listOrdersByYear");
                    response.sendRedirect("/CoffeeLand/server/income/IncomeManager.jsp");
                } catch (Exception e) {
                }

            } else if (!yearlyYear.isEmpty()) {
                IncomeDAO icd = new IncomeDAO();
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
                    java.util.Date utilDate = dateFormat.parse(yearlyYear);
                    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

                    List<Orders> listO = icd.getOrderByYear(Integer.parseInt(userId), sqlDate);
                    session.setAttribute("listOrdersByYear", listO);
                    session.removeAttribute("listOrders");
                    session.removeAttribute("listOrdersByDay");
                    session.removeAttribute("listOrdersByMonth");
                    response.sendRedirect("/CoffeeLand/server/income/IncomeManager.jsp");
                } catch (Exception e) {
                }

            }
        }
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
