<%--
  Created by IntelliJ IDEA.
  User: windows
  Date: 2023-10-12
  Time: 오후 2:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>근태기록</title>
    <link rel="stylesheet" href="/css/community/list.css">
</head>
<body>
<my:navBarFroAdmin></my:navBarFroAdmin>

<div class="board-content">
    <h1>근태관리</h1>
    <form id="categoryForm" method="get">
        <table id="table">
            <thead>
            <tr>
                <th>이름</th>
                <th>아이디</th>
                <th>출근 시간</th>
                <th>퇴근 시간</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${attendanceList}" var="attendance">
                <tr>
                    <td>${attendance.name}</td>
                    <td>${attendance.userId}</td>
                    <td>${attendance.checkIn}</td>
                    <td>${attendance.checkOut}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>
</div>

</body>
</html>
