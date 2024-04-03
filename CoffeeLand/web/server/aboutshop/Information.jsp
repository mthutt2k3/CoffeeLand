<%-- 
    Document   : Information
    Created on : Feb 12, 2024, 10:09:08 AM
    Author     : acer
--%>
<%@page import="model.Information.*"%>
<%@page import="java.util.List"%>
<%@page import="model.Users.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    session = request.getSession(false);
    Users xUser = new Users();
    if (session.getAttribute("user") != null) {
        xUser = (Users) session.getAttribute("user");
    } else {
        response.sendRedirect("/CoffeeLand/server/Users/login.jsp");
    }
    Informations x = (Informations) session.getAttribute("x");
    if(x == null){
        x = new Informations(0, "", "","","","","","","");
    }
%>
<%
    String errPhone = (String) session.getAttribute("errPhone");
    String errEmail = (String) session.getAttribute("errEmail");
    String suc = (String) session.getAttribute("success");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/CoffeeLand/assests/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="/CoffeeLand/assests/css/style_login.css"/>
        <title>Cập nhật thông tin cửa hàng|<%=x.getNameStore()%></title>
        <style>
            .input-box header{
                font-size: x-large;
                font-weight: 700;
                text-align: center;
                margin-bottom: 13px;
                color: #fff;
            }
            .mainn{
                display: flex;
                justify-content: center;
                align-items: center;
                min-height: 100vh;
            }
        </style>
    </head>
    <body>
        <!--nav-->
        <%@include file="/navigator/Navbar.jsp" %>
        <%@include file="/navigator/Toast.jsp" %>
        <%@include file="/navigator/sideBarMKTManage.jsp" %>
        <div class="wrapper">
            <div class="container mainn">
                
                <div class="col-md-3 left"   >
                    <form action="/CoffeeLand/updateinformationimage" method="post" enctype="multipart/form-data">
                        <input type="file" class="" name="photo">
                        <input type="hidden" name="informationId" value="1">
                        <input type="hidden" name='requestAction' value="Update Information Image"/>
                        <button type="submit" class="btn btn-outline-success mx-1 border-0 p-0" title="Cap nhat logo">
                            <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
                            <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                            <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
                            </svg>
                        </button>
                    </form>
                    <img style="height: 200px; width: 200px;" src='/CoffeeLand/assests/image/information_thumbnail/<%=x.getImage()%>'/>
                </div>
                <div class="col-md-6 right">
                    <div class="input-box" style="width: 700px;
                        box-sizing: border-box;
                        color: #fff;">
                        <header style="font-size: x-large;
                                font-weight: 700;
                                text-align: center;
                                margin-bottom: 13px;
                                color: red;">Cập nhật thông tin cửa hàng</header>
                        <div style="text-align: right; color: greenyellow;">
                            <i>Người chỉnh sửa cuối: <%=x.getUser()%></i>
                        </div>
                        <form action="/CoffeeLand/information" method="get" enctype="multipart/form-data">
                            <table class="table">
                                <td>
                                    <div class="text-info">Tên cửa hàng *<div>
                                        <input type="text" class="input" name="nameStore" value="<%=x.getNameStore()%>" required>
                                    </div>
                                    <div class="text-info">Mô tả *<div>
                                        <textarea type="text" class="input" name="description" style="height: 200px" required><%=x.getDescription()%></textarea>
                                    </div>
                                    
                                    
                                </td>
                                <td>
                                    <div class="text-info">Địa chỉ *<div>
                                        <textarea type="text" class="input" name="address" style="height: 70px"  required><%=x.getAddress()%></textarea>
                                    </div>
                                    <div class="text-info">Số điện thoại *<div>
                                        <input type="text" class="input" name="contactPhone" value="<%=x.getContactPhone()%>" required>
                                    </div>
                                        <%if(errPhone != null){%>
                                        <div class="alert alert-danger p-1 mx-2" style="" role="alert">
                                            <%=errPhone%>
                                        </div>
                                        <%}%>
                                    <div class="text-info">Email *<div>
                                        <input type="text" class="input" name="contactEmail" value="<%=x.getContactEmail()%>" required>
                                    </div>
                                        <%if(errEmail != null){%>
                                        <div class="alert alert-danger p-1 mx-2" style="" role="alert">
                                            <%=errEmail%>
                                        </div>
                                        <%}%>
                                    <div class="text-info">Link Facebook *<div>
                                        <input type="text" class="input" name="contactFacebook" value="<%=x.getContactFacebook()%>" required>
                                    </div>
                                </td>
                            </table>
                            <div class="">
                                <input type="hidden" class="input" name="userId" value="<%=xUser.getUserId()%>" required>
                                <input type="hidden" class="input" name="informationId" value="1" required>
                            </div>
                            
                            <div class="">
                                <% if(suc != null) { %>
                                    <div class="alert alert-success p-1 mx-2" role="alert" style="background-color: #28a745; color: white;">
                                        <%= suc %>
                                    </div>
                                <% } %>
                                <br/>
                                <div class="input-field">
                                    <input type="submit" class="submit" name="requestAction" value="Lưu">
                                </div> 
                            </div>      
                        </form>
                    </div>
                </div>
            </div>
        </div>
    <script type="text/javascript" type="text/javascript" src="/CoffeeLand/assests/js/bootstrap.bundle.js"></script>
</body>
</html>

