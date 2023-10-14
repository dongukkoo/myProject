<%--
  Created by IntelliJ IDEA.
  User: windows
  Date: 2023-07-05
  Time: 오후 2:54
  To change this template use File | Settings | File Templates.
--%>
<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ tag import="com.example.myproject.domain.Member" %>
<%
    Member member = new Member();

%>
<html>
<head>
    <link rel="stylesheet" href="/css/navBar.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
          integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
</head>
<body>

<div class="wrapper">
    <div class="sidebar">
        <a href="/home"><h2>StaffHub</h2></a>
        <ul>
            <li><a href="/home"><i class="fas fa-home"></i>Home</a></li>
            <li><a href="/member/info?id=<sec:authentication property="name"/>"><i class="fas fa-solid fa-address-card"></i>마이페이지</a>
            </li>
            <li><a href="/notice/list"><i class="fas fa-calendar-days"></i>공지사항</a></li>
            <li><a href="/community/list"><i class="fas fa-project-diagram"></i>커뮤니티</a></li>
            <sec:authorize access="hasAuthority('admin')">
                <li><a href="/member/add"><i class="fas fa-solid fa-user-plus"></i>사원등록</a></li>
                <li><a href="/member/list"><i class="fas fa-solid fa-users"></i>사원목록</a></li>
                <li><a href="/allUserAttendances"><i class="fas fa-solid fa-clock"></i>근태관리</a></li>
            </sec:authorize>
            <div class="out">
                <li><a href="/member/logout"><i class="fas fa-right-from-bracket"></i>로그아웃</a></li>
            </div>
        </ul>
    </div>
    <div class="topbar">
        <div class="header">${member.name}님 반갑습니다.
            <span id="clock" style="color:gray; font-size: 20px;">clock</span>
            <span id="apm" style="color:gray; font-size: 10px;">ampm</span>
            <button id="toggleButton" onclick="toggleAttendance()">출근</button>
        </div>
    </div>




</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js"
        integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>

<script src="/js/clock.js"></script>
<script src="/js/attendance.js"></script>

</body>
</html>
