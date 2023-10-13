<%--
  Created by IntelliJ IDEA.
  User: windows
  Date: 2023-07-27
  Time: 오후 4:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
        integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
        crossorigin="anonymous" referrerpolicy="no-referrer"/>

  <link rel="stylesheet" href="/css/community/list.css">

  <title>사원목록</title>
</head>
<style>
  #table{
    width: 1100px;
    margin-left: -80px;
  }
</style>
<body>
<my:navBarFroAdmin></my:navBarFroAdmin>
<my:alert></my:alert>

<div class="board-content">
  <h1>사원목록</h1>
  <form id="categoryForm" method="get">
    <table id="table">
      <thead>
      <tr>
        <th>아이디</th>
        <th style="width: 80px">이름</th>
        <th style="width: 90px">직급</th>
        <th style="width: 60px">성별</th>
        <th style="width: 300px">주소</th>
        <th style="width: 125px">휴대폰번호</th>
        <th>이메일</th>
        <th style="width: 125px">생일</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${memberList}" var="member">
        <tr>
          <td><a href="/member/info?id=${member.id}">${member.id}</a></td>
          <td>${member.name}</td>
          <td>${member.position}</td>
          <td>${member.gender}</td>
          <td>${member.address}</td>
          <td>${member.phoneNum}</td>
          <td>${member.email}</td>
          <td>${member.birthDate}</td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </form>
</div>



<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js"
        integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>

</body>
</html>
