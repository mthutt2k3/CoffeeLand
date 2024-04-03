/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author acer
 */
public class controlDispatch extends HttpServlet {

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
        String variable, url = "";
        variable = request.getParameter("requestAction");
        switch (variable) {
            //Client
            case "Login Client":
                url = "loginclient";
                break;

            //Order
            case "Order Manage":
                url = "ordermanage";
                break;
            case "Order Detail":
                url = "orderdetail";
                break;
            case "Sort Order":
                url = "sortorder";
                break;
            case "View Purchase History":
                url = "orderhistory";
                break;
            //User
            case "Data Statistics":
                url = "datastatistics";
                break;
            case "Banned":
                url = "banned";
                break;
            case "Inactive User":
                url = "inactiveuser";
                break;
            case "Inactive Customer":
                url = "inactivecustomer";
                break;
            case "View Profile":
                url = "viewprofile";
                break;
            case "Tạo người dùng":
                url = "createuser";
                break;
            case "Tạo khách hàng":
                url = "createcustomer";
                break;
            case "Đăng nhập":
                url = "login";
                break;
            case "Register":
                url = "register";
                break;
            case "Reset Password":
                url = "resetpassword";
                break;
            case "Manage Users":
                url = "list";
                break;
            case "Sort Users":
                url = "sortusers";
                break;
            case "Logout":
                url = "logout";
                break;
            case "Set Role":
                url = "setrole";
                break;
            case "Profile":
            case "Change password":
            case "Change Information":
                url = "update";
                break;
            case "Get User By Role":
                url = "getuserbyrole";
                break;
            case "Update User":
                url = "updateuser";
                break;
            case "Cập nhật người dùng":
                url = "updateprofile";
                break;
            //Products
            case "Manage Product":
                url = "productlist";
                break;
            case "Get Product":
                url = "getproduct";
                break;
            case "Get Product Type":
                url = "getproductbytype";
                break;
            case "Sort Product":
                url = "sortproduct";
                break;
            case "Add Product":
                url = "addproduct";
                break;
            case "Update Product":
                url = "updateproduct";
                break;
            //Category
            case "Manage Category":
                url = "categorylist";
                break;
            case "Sort Category":
                url = "sortcategory";
                break;
            case "Update Category":
                url = "updatecategory";
                break;
            case "Add Category":
                url = "addcategory";
                break;
            case "Get Category Type":
                url = "getcategorybytype";
                break;
            //Information about shop
            case "Information":
                url = "information";
                break;
            case "Update Information Image":
                url = "updateinformationimage";
                break;
            //News
            case "Manage News":
                url = "newslist";
                break;
            case "Add News":
                url = "addnews";
                break;
            case "Sort News":
                url = "sortnews";
                break;
            case "Get News Priority Type":
                url = "getnewsprioritytype";
                break;
            case "Update News":
                url = "updatenews";
                break;
            //News
            case "Manage Voucher":
                url = "voucherlist";
                break;
            case "Add Voucher":
                url = "addvoucher";
                break;
            case "Sort Voucher":
                url = "sortvoucher";
                break;
            case "Update Voucher":
                url = "updatevoucher";
                break;

            //General
            case "Search":
                url = "search";
                break;
            case "Delete":
                url = "delete";
                break;
            case "Update Image":
                url = "updateimg";
                break;
            default:
                url = "MainPage.jsp";
                break;
        }
        request.getRequestDispatcher(url).forward(request, response);
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
        response.setContentType("text/html;charset=UTF-8");
        processRequest(request, response);
    }
//
//    /** 
//     * Returns a short description of the servlet.
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>

}
