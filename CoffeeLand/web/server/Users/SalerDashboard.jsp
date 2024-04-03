<%-- 
    Document   : UserManage
    Created on : Oct 5, 2023, 6:05:27 PM
    Author     : lap21
--%>
<%@page import="model.Information.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "model.Users.*" %>
<%@page import = "model.Orders.*" %>
<%@page import = "model.Product.*" %>
<%@page import = "java.util.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@page import= "java.text.DecimalFormat"%>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    session = request.getSession(false);
    Users xUser = new Users();
    if (session.getAttribute("user") != null) {
        xUser = (Users) session.getAttribute("user");
    } else {
        response.sendRedirect("/CoffeeLand/server/Users/login.jsp");
    }
%>

<%
    List<Users> lst = (List<Users>) session.getAttribute("lstUsers");
    List<Orders> lstOrder = (List<Orders>) session.getAttribute("lstOrders");
    List<Product> lstProduct = (List<Product>) session.getAttribute("lstPro");
    int[] lstInt = (int[]) session.getAttribute("lstInt");
    int lstU = (int) session.getAttribute("noUser");
    int lstN = (int) session.getAttribute("noNews");
    int lstC = (int) session.getAttribute("noCate");
    int year =  (int) session.getAttribute("year");
    int orderInProcess = (int) session.getAttribute("orderInProcess");
    int orderwaiting =  (int) session.getAttribute("orderwaiting");
    int doanhthu = 0;
if (lstInt != null && lstInt.length == 12) {
    
    for (int i = 0; i < 12; i++) {
        doanhthu += lstInt[i];
    }
    String colName;
} else {
}
    String colName;
    DecimalFormat decimalFormat = new DecimalFormat("###,###.##");
    String xTotalAmount = decimalFormat.format(doanhthu);
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Quản lý người dùng|CoffeeLand</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/CoffeeLand/assests/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="/CoffeeLand/assests/css/style.css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
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
            .input {
        padding: 10px; /* Đặt kích thước padding */
        font-size: 16px; /* Đặt kích thước chữ */
        border: 1px solid #ccc; /* Đặt border và màu border */
        border-radius: 5px; /* Đặt bo tròn cho góc */
        background-color: #f8f8f8; /* Đặt màu nền */
    }

    /* CSS cho nút "Xem" */
    .submit {
        padding: 10px 20px; /* Đặt kích thước padding */
        font-size: 16px; /* Đặt kích thước chữ */
        border: none; /* Loại bỏ border */
        border-radius: 5px; /* Đặt bo tròn cho góc */
        background-color: #904b4b; /* Đặt màu nền */
        color: white; /* Đặt màu chữ */
        cursor: pointer; /* Biến con trỏ thành hình bàn tay khi rê chuột qua */
    }

    /* Hover effect cho nút "Xem" */
    .submit:hover {
        background-color: #6b3535; /* Đổi màu nền khi hover */
    }
        </style>
    </head>
    <body>
        <!--nav-->
        <%@include file="/navigator/Navbar.jsp" %>
        <%@include file="/navigator/Toast.jsp" %>
        <%@include file="/navigator/sideBarSaler.jsp" %>

        <!--Main information-->
        <div class="wrapper bg-bodylist" style="height: fit-content; min-height: 100vh">
            <div class="container main" style="margin-top: 62px">
                <div class="row bg-bodylist" style="height: fit-content">
                    <div class="mb-3 mt-2 text-center">
                        <h class="text-light big-title" style="font-size: 53px; font-weight: bold;">
                            Thống kê Saler
                        </h>
                    </div>
                    <div class="col-lg-4 col-md-6 mb-4">
                        <div class="card text-white shadow" style="background-color: rgb(99, 159, 184)">
                            <div class="card-body d-flex justify-content-center align-items-center">
                                <div class="text-center">
                                    <p class="mb-0">Doanh thu năm <%=year%></p>
                                    <h3 class="mb-0"><%=xTotalAmount%></h3>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6 mb-4">
                        <div class="card text-white shadow" style="background-color: rgb(99, 159, 184)">
                            <div class="card-body d-flex justify-content-center align-items-center">
                                <div class="text-center">
                                    <p class="mb-0">Đơn hàng đã xử lý</p>
                                    <h3 class="mb-0"><%=orderInProcess%></h3>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6 mb-4">
                       <div class="card text-white shadow" style="background-color: rgb(99, 159, 184)">
                            <div class="card-body d-flex justify-content-center align-items-center">
                                <div class="text-center">
                                    <p class="mb-0">Đơn hàng chưa xử lý</p>
                                    <h3 class="mb-0"><%=orderwaiting%></h3>
                                </div>
                            </div>
                        </div>
                    </div>

                    <form action="/CoffeeLand/salerdashboard" method="POST">
                        <div class="text-info">
                            <span style="color: #904b4b; font-weight: bold; font-size: 30px;">
        DOANH THU NĂM 
    </span>
                            <span>
                                <select class="input" name="year" required>
                                    <option value="<%=year%>"><%=year%></option>
                                    <option value="2021">2021</option>
                                    <option value="2022">2022</option>
                                    <option value="2023">2023</option>
                                    <option value="2024">2024</option>
                                </select>
                                <input type="submit" class="submit" name="requestAction" value="Xem">
                            </span>
                        </div>
                    </form>
                    <canvas id="revenue-chart"></canvas>
                </div>
            </div>
        </div>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.6.2/chart.min.js"></script>




        <script>
            var ctx = document.getElementById("revenue-chart").getContext("2d");

            var chart = new Chart(ctx, {
                type: "bar",
                data: {
                    labels: ["Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"],

                    datasets: [{
                            backgroundColor: "blue",

                            data: [<%=lstInt[0]%>, <%=lstInt[1]%>, <%=lstInt[2]%>, <%=lstInt[3]%>, <%=lstInt[4]%>, <%=lstInt[5]%>, <%=lstInt[6]%>, <%=lstInt[7]%>, <%=lstInt[8]%>, <%=lstInt[9]%>, <%=lstInt[10]%>, <%=lstInt[11]%>]
                        }]
                },

                options: {
                    plugins: {
                        legend: {
                            display: false
                        }
                    },

                    scales: {
                        x: {
                            grid: {
                                display: true
                            }
                        },
                        y: {
                            grid: {
                                display: true
                            },

                            beginAtZero: true
                        }
                    }
                }
            });
        </script>
    </body>
</html>




