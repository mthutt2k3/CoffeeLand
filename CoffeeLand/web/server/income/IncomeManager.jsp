<%-- 
    Document   : IncomeManager
    Created on : Mar 14, 2024, 11:12:03 AM
    Author     : dell
--%>
<%@ page import="model.Income.IncomeDAO" %>
<%@ page import="model.Users.Users" %>
<%@ page import="model.Orders.Orders" %>
<%@page import="model.Information.*"%>
<%@page import="model.Users.*"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Income Manager Page</title>

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/CoffeeLand/assests/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="/CoffeeLand/assests/css/style.css"/>
        <style>
            .bg-navbar
            {
                --bs-bg-opacity:1;
                background-color:#5e5643;
            }
            .dropdown-menu-darknavbar{
                --bs-dropdown-color:#dee2e6;
                --bs-dropdown-bg:#403d34;
                --bs-dropdown-border-color:var(--bs-border-color-translucent);
                --bs-dropdown-link-color:#dee2e6;
                --bs-dropdown-link-hover-color:#171512;
                --bs-dropdown-divider-bg:var(--bs-border-color-translucent);
                --bs-dropdown-link-hover-bg:rgba(255, 255, 255, 0.15);
                --bs-dropdown-link-active-color:#fff;
                --bs-dropdown-link-active-bg:#0d6efd;
                --bs-dropdown-link-disabled-color:#adb5bd;
                --bs-dropdown-header-color:#adb5bd
            }
            .bg-bodylist
            {
                --bs-bg-opacity:1;
                background-color:#94927b;
            }
            .search-inputlist{
                font-size: 14px;
                border: none;
                background: #c5c1ae;
                margin: 0;
                color: inhefrit;
                border: 1px solid black;
                border-radius: inherit;
            }
            .table-infolist{
                --bs-table-color:#000;
                --bs-table-bg:#bbbd27;
                --bs-table-border-color:#000000;
                --bs-table-striped-bg:#c5e8ef;
                --bs-table-striped-color:#000;
                --bs-table-active-bg:#badce3;
                --bs-table-active-color:#000;
                --bs-table-hover-bg:#757b33;
                --bs-table-hover-color:#000;
                color:var(--bs-table-color);
                border-color:var(--bs-table-border-color)
            }

            .btn-outline-infolist{
                --bs-btn-color:#e8ff00;
                --bs-btn-border-color:#e8ff00;
                --bs-btn-hover-color:#000;
                --bs-btn-hover-bg:#e8ff00;
                --bs-btn-hover-border-color:#e8ff00;
                --bs-btn-focus-shadow-rgb:13,202,240;
                --bs-btn-active-color:#000;
                --bs-btn-active-bg:#0dcaf0;
                --bs-btn-active-border-color:#e8ff00;
                --bs-btn-active-shadow:inset 0 3px 5px rgba(0, 0, 0, 0.125);
                --bs-btn-disabled-color:#0dcaf0;
                --bs-btn-disabled-bg:transparent;
                --bs-btn-disabled-border-color:#0dcaf0;
                --bs-gradient:none
            }
            /* CSS cho form */
            #incomeForm {
                margin-bottom: 20px;
            }

            label {
                display: block;
                margin-bottom: 5px;
            }

            select, input[type="date"], input[type="text"] {
                width: 200px;
                padding: 8px;
                margin-bottom: 10px;
                border: 1px solid #ccc;
                border-radius: 5px;
                box-sizing: border-box;
            }

            input[type="submit"] {
                padding: 10px 20px;
                background-color: #007bff;
                color: #fff;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }

            /* CSS cho bảng */
            table {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 20px;
            }

            thead th, tbody td {
                border: 1px solid #ddd;
                padding: 10px;
                text-align: center;
            }

            thead th {
                background-color: #f2f2f2;
                color: black
            }

            tbody tr:nth-child(even) {
                background-color: #f2f2f2;
                color: black
            }

        </style>
    </head>
    <%
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        session = request.getSession(false);
        Users xUser = (Users) session.getAttribute("user");
        if (xUser == null) {
            response.sendRedirect("/CoffeeLand/server/Users/login.jsp");
        } else {
    %>
    <%@include file="/navigator/Navbar.jsp" %>
    <%
        }
    %>
    <body>
        <%@include file="/navigator/Toast.jsp" %>
        <%@include file="/navigator/sideBarSalerManager.jsp" %>
        <div class="wrapper bg-bodylist" style="height: fit-content; min-height: 100vh">
            <div class="container main" style="margin-top: 62px">
                <div class="row bg-bodylist" style="height: fit-content">
                    <div class="mb-3 mt-2 text-center">
                        <h class="text-light big-title" style="font-size: 53px; font-weight: bold;">
                            Quản lý doanh thu
                        </h>
                    </div>
                    <form id="incomeForm" action="/CoffeeLand/selectName" method="post" onsubmit="return validateForm()">

                        <label>Chọn nhân viên</label>
                        <select name="user" id="nameSelect">
                            <%
                                IncomeDAO icd = new IncomeDAO();
                                List<Users> listUser = icd.getSalerNameByID();
                                for(Users user: listUser){
                            %>
                            <option value="<%=user.getUserId()%>"><%=user.getName()%></option>
                            <%
                                }
                            %>
                        </select>
                        <h3>Chỉ chọn một trong những mốc thời gian sau</h3>
                        <p>Tổng tiền theo ngày</p>
                        <input type="date" id="dailyDate" name="date"/>
                        <p>Tổng tiền theo tháng</p>
                        <input type="text" id="monthlyMonth" name="monthlyMonth" placeholder="YYYY-MM"/>
                        <p>Tổng tiền theo năm</p>
                        <input type="text" id="yearlyYear" name="yearlyYear" placeholder="YYYY"/>
                        <input type="submit" value="Kiểm tra" onclick="validateForm()"/>
                    </form>

                    <table>
                        <thead>
                            <tr>
                                <th>Mã đơn</th>
                                <th>Thời gian</th>
                                <th>Tổng đơn</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%-- Kiểm tra và hiển thị danh sách listOrders --%>
                            <c:if test="${not empty sessionScope.listOrders}">
                                <c:set var="total" value="0" />
                                <c:forEach var="order" items="${sessionScope.listOrders}">
                                    <tr>
                                        <td>${order.orderCode}</td>
                                        <td>${order.orderTime}</td>
                                        <td>${order.grandTotal}</td>
                                    </tr>
                                    <c:set var="total" value="${total + order.grandTotal}" />
                                </c:forEach>

                                <tr>
                                    <td style="color: red; font-weight: bold" colspan="2">Tổng tiền:</td>
                                    <td style="color: red; font-weight: bold">${total}</td>
                                </tr>
                            </c:if>

                            <%-- Kiểm tra và hiển thị danh sách listOrdersByDay --%>
                            <c:if test="${not empty sessionScope.listOrdersByDay}">
                                <c:set var="total" value="0" />
                                <c:forEach var="order" items="${sessionScope.listOrdersByDay}">
                                    <tr>
                                        <td>${order.orderCode}</td>
                                        <td>${order.orderTime}</td>
                                        <td>${order.grandTotal}</td>
                                    </tr>
                                    <c:set var="total" value="${total + order.grandTotal}" />
                                </c:forEach>
                                <tr>
                                    <td style="color: red; font-weight: bold" colspan="2">Tổng tiền:</td>
                                    <td style="color: red; font-weight: bold">${total}</td>
                                </tr>
                            </c:if>

                            <%-- Kiểm tra và hiển thị danh sách listOrdersByMonth --%>
                            <c:if test="${not empty sessionScope.listOrdersByMonth}">
                                <c:set var="total" value="0" />
                                <c:forEach var="order" items="${sessionScope.listOrdersByMonth}">
                                    <tr>
                                        <td>${order.orderCode}</td>
                                        <td>${order.orderTime}</td>
                                        <td>${order.grandTotal}</td>
                                    </tr>
                                <c:set var="total" value="${total + order.grandTotal}" />
                                </c:forEach>
                                <tr>
                                    <td style="color: red; font-weight: bold" colspan="2">Tổng tiền:</td>
                                    <td style="color: red; font-weight: bold">${total}</td>
                                </tr>
                            </c:if>

                            <%-- Kiểm tra và hiển thị danh sách listOrdersByYear --%>
                            <c:if test="${not empty sessionScope.listOrdersByYear}">
                                <c:set var="total" value="0" />
                                <c:forEach var="order" items="${sessionScope.listOrdersByYear}">
                                    <tr>
                                        <td>${order.orderCode}</td>
                                        <td>${order.orderTime}</td>
                                        <td>${order.grandTotal}</td>
                                    </tr>
                                <c:set var="total" value="${total + order.grandTotal}" />
                                </c:forEach>
                                <tr>
                                    <td style="color: red; font-weight: bold" colspan="2">Tổng tiền:</td>
                                    <td style="color: red; font-weight: bold">${total}</td>
                                </tr>
                            </c:if>


                        </tbody>
                    </table>
                </div>

            </div>


        </div>


        <script>
            function validateForm() {
                var dailyDate = document.getElementById("dailyDate");
                var monthlyMonth = document.getElementById("monthlyMonth");
                var yearlyYear = document.getElementById("yearlyYear");
                var filledCount = 0;

                // Kiểm tra xem ngày, tháng hoặc năm đã được nhập hay chưa
                if (dailyDate.value !== "") {
                    filledCount++;
                }
                if (monthlyMonth.value !== "") {
                    filledCount++;
                }
                if (yearlyYear.value !== "") {
                    filledCount++;
                }

                // Kiểm tra xem người dùng đã nhập quá hai trường "ngày", "tháng" hoặc "năm" hay chưa
                if (filledCount > 1) {
                    alert("Vui lòng chỉ nhập một trong các trường: Tổng tiền theo ngày, tháng hoặc năm.");
                    return false;
                }

                // Kiểm tra định dạng ngày nhập vào (YYYY-MM-DD)
                var dailyDateValue = dailyDate.value.trim();
                if (dailyDateValue !== "" && !isValidDateFormat(dailyDateValue, "YYYY-MM-DD")) {
                    alert("Vui lòng nhập đúng định dạng ngày YYYY-MM-DD.");
                    return false;
                }

                // Kiểm tra định dạng tháng nhập vào (YYYY-MM)
                var monthlyMonthValue = monthlyMonth.value.trim();
                if (monthlyMonthValue !== "" && !isValidDateFormat(monthlyMonthValue, "YYYY-MM")) {
                    alert("Vui lòng nhập đúng định dạng tháng YYYY-MM.");
                    return false;
                }

                // Kiểm tra định dạng năm nhập vào (YYYY)
                var yearlyYearValue = yearlyYear.value.trim();
                if (yearlyYearValue !== "" && !isValidDateFormat(yearlyYearValue, "YYYY")) {
                    alert("Vui lòng nhập đúng định dạng năm YYYY.");
                    return false;
                }

                // Nếu mọi thứ hợp lệ, cho phép gửi form
                return true;
            }

            // Hàm kiểm tra định dạng ngày tháng
            function isValidDateFormat(dateString, format) {
                var dateFormatRegex = /^\d{4}-(0?[1-9]|1[0-2])-(0?[1-9]|[1-2][0-9]|3[0-1])$/; // YYYY-MM-DD
                var monthFormatRegex = /^\d{4}-(0?[1-9]|1[0-2])$/; // YYYY-MM
                var yearFormatRegex = /^\d{4}$/; // YYYY

                if (format === "YYYY-MM-DD") {
                    return dateFormatRegex.test(dateString);
                } else if (format === "YYYY-MM") {
                    return monthFormatRegex.test(dateString);
                } else if (format === "YYYY") {
                    return yearFormatRegex.test(dateString);
                } else {
                    return false;
                }
            }
        </script>



    </body>
</html>
