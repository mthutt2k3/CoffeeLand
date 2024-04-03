<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "model.Users.*" %>
<%@page import = "java.util.*" %>
<%@page import="model.Information.*"%>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    session = request.getSession(false);
    if (session.getAttribute("user") != null) {
        response.sendRedirect("/_WebMaster/client/MainPage.jsp");
    }
%>

<%
    
    String xEmail = "";
    if(session.getAttribute("email") != null){
        xEmail = (String) session.getAttribute("email");
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

        <title>Reset your password|WebMaster</title>
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
            .forgot-items {
    margin-top: 20px;
}

.title {
    font-size: 24px;
    font-weight: bold;
}

.form-forgot {
    margin-top: 20px;
}

.form-forgot input[type="email"] {
    width: 300px;
    height: 40px;
    padding: 5px 10px;
    font-size: 16px;
    border: 1px solid #ccc;
    border-radius: 5px;
}

.form-forgot input[type="submit"] {
    width: 200px;
    height: 40px;
    margin-top: 10px;
    background-color: #d35400;
    color: #fff;
    border: none;
    border-radius: 5px;
    font-size: 16px;
    cursor: pointer;
}

.form-forgot input[type="submit"]:hover {
    background-color: #f1c40f;
}

#error-message {
    border-radius: 10px;
    padding: 5px;
    width: 250px;
    color: #f1c40f;
    margin-left: 30px;
    margin-top: 20px;
    font-size: 20px;
}

            
        </style>
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
                            <div class="forgot-items">


                                <p class="title">Quên mật khẩu?</p>
                                <div class="forgot">

                                    <div class="form-forgot">
                                        <form action="/CoffeeLand/forgetpassword" method="post">
                                            <p>Nhập email đã đăng ký của bạn: <input type="email" name="email" placeholder="Nhập email vào đây ..." required/></p>
                                            <input type="submit" value="Gửi và check email"/>
                                        </form>
                                    </div>

                                </div>
                                <div id="error-message" ></div>
                            </div>
                        </div>  
                    </div>
                </div>
            </div>
        </div>
        <script type="text/javascript" type="text/javascript" src="/CoffeeLand/assests/js/bootstrap.bundle.js"></script>
    </body>
    <script>
        // Function to display error message
        function displayError(message) {
            var errorMessage = document.getElementById('error-message');
            errorMessage.innerText = message;
        }

        // Function to extract notice parameter from URL
        function getNoticeFromURL() {
            var urlParams = new URLSearchParams(window.location.search);
            return urlParams.get('e');
        }

        // Call the function to display error message when the page loads
        window.onload = function () {
            var e = getNoticeFromURL();
            if (e) {
                displayError(e);
            }
        }
    </script>
</html>