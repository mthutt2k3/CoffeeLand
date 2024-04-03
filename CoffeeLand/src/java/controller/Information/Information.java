/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Information;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.nio.file.Files;
import java.nio.file.Path;
import model.Information.InformationDAO;
import model.Information.Informations;
import model.Users.Users;

/**
 *
 * @author acer
 */
@MultipartConfig
public class Information extends HttpServlet {

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
        doPost(request, response);
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
        processRequest(request, response);
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
        try {
            response.setContentType("text/html;charset=UTF-8");
//        PrintWriter pr = response.getWriter();
            HttpSession session = request.getSession(false);
            Informations rootInformation = new Informations();

            if (session.getAttribute("x") != null) {
                rootInformation = (Informations) session.getAttribute("x");
            }
            Users xUser = new Users();
            if (session.getAttribute("user") != null) {
                xUser = (Users) session.getAttribute("user");
            } else {
                response.sendRedirect("/CoffeeLand/server/Users/login.jsp");
            }
            
            String xinformationId = request.getParameter("informationId");
            String userId = request.getParameter("userId");
            String description = request.getParameter("description");
            String nameStore = request.getParameter("nameStore");
            String address = request.getParameter("address");
            String contactPhone = request.getParameter("contactPhone");
            String contactEmail = request.getParameter("contactEmail");
            String contactFacebook = request.getParameter("contactFacebook");
            
            InformationDAO iDAO = new InformationDAO();
            Informations x;
            

            if (session.getAttribute("x") == null) {
                x = iDAO.getInformation(xinformationId);
                session.setAttribute("x", x);
                String url = "/CoffeeLand/server/aboutshop/Information.jsp";
                response.sendRedirect(url);
            } else {
                try {   
                    x = new Informations(Integer.parseInt(xinformationId), rootInformation.getUser(), description, nameStore, address, contactPhone, contactEmail, contactFacebook);

                    if (!contactPhone.matches("^0\\d{9}$")) {
                        String alert = "Số điện thoại phải bắt đầu bằng số 0 và có 10 số";
                        session.setAttribute("alert", alert);
                        session.setAttribute("x", x);
                        session.removeAttribute("errEmail");
                        session.setAttribute("errPhone", alert);
                        String url = "/CoffeeLand/server/aboutshop/Information.jsp";
                        response.sendRedirect(url);
                    } else if (!contactEmail.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
                        String alert = "Email phải có dạng ***@***.**";
                        session.setAttribute("alert", alert);
                        session.setAttribute("x", x);
                        session.removeAttribute("errPhone");
                        session.setAttribute("errEmail", alert);
                        String url = "/CoffeeLand/server/aboutshop/Information.jsp";
                        response.sendRedirect(url);
                    } else {
                        x=new Informations(Integer.parseInt(xinformationId), userId, description, nameStore, address, contactPhone, contactEmail, contactFacebook);
                        iDAO.update(rootInformation.getInformationId(), x);
                        String alert = "Đã cập nhật thông tin cửa hàng thành công!";
                        String url = "/CoffeeLand/server/aboutshop/Information.jsp";
                        session.setAttribute("alert", alert);
                        session.setAttribute("success", alert);
                        x = iDAO.getInformation(xinformationId);
                        session.removeAttribute("errPhone");
                        session.removeAttribute("errEmail");
                        session.setAttribute("x",iDAO.getInformation("1"));
                        session.setAttribute("info",iDAO.getInformation("1"));
                        response.sendRedirect(url);
                    }
                } catch (Exception e) {
                    if (session.getAttribute("x") == null) {
                        x = iDAO.getInformation(xinformationId);
                        session.setAttribute("x", x);
                        String url = "/CoffeeLand/server/aboutshop/Information.jsp";
                        response.sendRedirect(url);
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error uploading the file: " + e.getMessage());
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
