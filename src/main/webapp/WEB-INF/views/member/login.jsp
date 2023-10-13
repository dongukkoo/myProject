<%--
  Created by IntelliJ IDEA.
  User: windows
  Date: 2023-07-05
  Time: 오후 4:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>
<html>
<head>
    <title>Login Page</title>
    <link rel="stylesheet" href="/css/login.css">
</head>
<body>

<section>
    <div class="form-box">
        <div class="form-value">
            <form method="post">
                <h2>로그인</h2>
                <div class="inputbox">
                    <ion-icon name="mail-outline"></ion-icon>
                    <input type="text" name="username" required autocomplete="off" >
                    <label>ID</label>
                </div>
                <div class="inputbox">
                    <ion-icon name="lock-closed-outline"></ion-icon>
                    <input type="password" name="password" required>
                    <label>Password</label>
                </div>
                <input class="loginBtn" type="submit" value="Login">
            </form>
        </div>
    </div>
</section>

</body>
</html>
