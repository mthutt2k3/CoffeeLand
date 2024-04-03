<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "model.Users.*" %>
<%@page import = "java.util.*" %>
<%@page import="model.Information.*"%>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    session = request.getSession(false);
%>

<%
    String xEmail = (String) session.getAttribute("email");
    if(xEmail == null){
        xEmail="";
    }else{
        session.removeAttribute("email");
    }
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/CoffeeLand/assests/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <title>Đăng Nhập|CoffeeLand</title>
        <style>
            *{
                font-family: 'Poppins', sans-serif;
            }
            .body {
                background: #1a1a1a;
                color: #fff;
            }
            .wrapper{
                background: #94927b;
                padding: 0 20px 0 20px;
            }
            .main{
                display: flex;
                justify-content: center;
                align-items: center;
                min-height: 100vh;
            }
            .side-image{
                background-image: url(assests/images/background/Login.png);
                background-position: center;
                background-size: cover;
                background-repeat: no-repeat;
                border-radius: 10px 0 0 10px;
                position: relative;
            }
            .img{
                width: 35px;
                position: absolute;
                top: 30px;
                left: 30px;
            }

            .row{
                width:  900px;
                height:550px;
                border-radius: 10px;
                background: #5e5643;
                padding: 0px;
                box-shadow: 5px 5px 10px 1px rgba(0,0,0,0.2);
                color: #fff;
            }
            .right{
                display: flex;
                justify-content: center;
                align-items: center;
                position: relative;
            }
            .input-box{
                width: 330px;
                box-sizing: border-box;
                color: #fff;
            }
            .input-box header{
                font-size: x-large;
                font-weight: 700;
                text-align: center;
                margin-bottom: 45px;
                color: #d2d4aa;
                ;
            }

            .input-field{
                display: flex;
                flex-direction: column;
                position: relative;
                padding: 0 10px 0 10px;
            }

            .input{
                height: 45px;
                width: 100%;
                background: transparent;
                border: none;
                border-bottom: 2px solid rgba(0, 0, 0, 0.2);
                outline: none;
                margin-bottom: 20px;
                color: #fff;
            }

            .input-box .input-field label{
                position: absolute;
                top: 12px;
                left: 10px;
                pointer-events: none;
                transition: .5s;
                color: rgba(255, 255, 255, 0.7);
            }

            .input-field input:focus ~ label{
                top: -10px;
                font-size: 13px;
            }

            .input-field input:valid ~ label{
                top: -10px;
                font-size: 13px;
                color: #333003;
            }
            .input-field .input:focus, .input-field .input:valid{
                border-bottom: 1px solid #333003;
            }
            .submit{
                border: none;
                outline: none;
                height: 45px;
                background: #eaecc2;
                border-radius: 5px;
                transition: .4s;
            }
            .submit:hover{
                background: #c8cd67;
                color: #fff;
            }
            .submit:active{
                background: #73753c;
                height: 40px;
                color: #fff;
            }
            .signin{
                text-align: center;
                font-size: small;
                margin-top: 25px;
            }

            span a{
                text-decoration: none;
                font-weight: 700;
                color: #c4b76d;
                ;
                transition: .4s;
            }
            span a:hover{
                text-decoration: underline;
                color: #eccc17;
            }
            span a:active{
                text-decoration: underline;
                color: #2d280a;
            }
            .input-field {
                position: relative;
            }

            .toggle-password {
                position: absolute;
                right: 20px;
                top: 40%;
                transform: translateY(-50%);
                color: black;
                font-size: 20px;
                cursor: pointer;
            }
        </style>
        <!-- Thêm Font Awesome để lấy biểu tượng con mắt -->
        <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    </head>
    <body>
        <%@include file="/navigator/Toast.jsp" %>
        <div class="wrapper">
            <div class="container main">
                <div class="row">
                    <div class="col-md-6 side-image" style="background-image: url(/CoffeeLand/assests/image/background/backgroundlogin2.jpg)">
                        <img class="img" src="/CoffeeLand/assests/image/background/backgroundlogin2.jpg" alt="">
                    </div>
                    <div class="col-md-6 right">
                        <div class="input-box">
                            <header style="font-size: 60px">Đăng Nhập</header>
                            <form action="/CoffeeLand/control" method="POST">
                                <div class="input-field">
                                    <input type="text" class="input" id="email" name="email" value="<%= xEmail %>" required="" autocomplete="off">
                                    <label for="email">Email</label> 
                                </div> 
                                <div class="input-field">
                                    <input type="password" class="input" id="pass" name="password" value="" required="">
                                    <!-- Thêm biểu tượng con mắt với sự kiện onclick -->
                                    <span class="toggle-password" onclick="togglePassword()">
                                        <i class="far fa-eye"></i>
                                    </span>
                                    <label for="pass">Mật khẩu</label>
                                </div>
                                <div class="mx-2">
                                    <div class="font-monospace opacity-75" style="font-size: small; display: flex">                                        
                                        <div class="">
                                            <select name="role" id="role">
                                                <option value="1">Admin</option>
                                                <option value="2">Marketer</option>
                                                <option value="3">Saler</option>
                                                <option value="5">Saler Manager</option>
                                            </select>
                                        </div>
                                        <span style="font-family: monospace; font-size: small; opacity: 100%; font-weight: unset; margin-left: auto">
                                            <a href="/CoffeeLand/server/Users/ForgetPassword.jsp">Quên mật khẩu?</a>
                                        </span>
                                    </div>
                                </div>
                                <div class="input-field mt-4">
                                    <input type="submit" class="submit" name="requestAction" value="Đăng nhập">
                                </div> 
                            </form>
                        </div>  
                    </div>
                </div>
            </div>
        </div>
        <script type="text/javascript" type="text/javascript" src="/CoffeeLand/assests/js/bootstrap.bundle.js"></script>
        <script>
                                        function togglePassword() {
                                            var input = document.getElementById("pass");
                                            var icon = document.querySelector(".toggle-password i");

                                            // Kiểm tra kiểu của input
                                            if (input.type === "password") {
                                                input.type = "text";
                                                icon.className = "far fa-eye-slash";
                                            } else {
                                                input.type = "password";
                                                icon.className = "far fa-eye";
                                            }
                                        }
        </script>

    </body>
</html>