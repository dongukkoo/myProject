<%--
  Created by IntelliJ IDEA.
  User: windows
  Date: 2023-07-07
  Time: 오후 12:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <link rel="stylesheet" href="/css/memberInfo.css">
    <title>사원정보</title>
</head>
<body>
<my:navBarFroAdmin></my:navBarFroAdmin>
<my:alert></my:alert>

<section class="container">
    <header>사원정보</header>
    <form class="form">
        <div class="input-box address">
            <label>아이디</label>
            <input type="text" value="${member.id}" readonly/>
            <div class="label-mg">이름 / 직급</div>
            <div class="column">
                <input type="text" value="${member.name}" readonly/>
                <input type="text" value="${member.position}" readonly/>
            </div>
        </div>
        </div>
        <div class="input-box">
            <label>이메일</label>
            <input type="email" value="${member.email}" readonly/>
        </div>
        <div class="column">
            <div class="input-box">
                <label>휴대폰</label>
                <input type="text" value="${member.phoneNum}" readonly/>
            </div>
            <div class="input-box">
                <label>생년월일</label>
                <input type="text" value="${member.birthDate}" readonly/>
            </div>
        </div>
        <div class="input-box address">
            <label>주소</label>
            <input type="text" value="${member.address}" readonly/>
        </div>

        <div hidden>
            <input type="text" value="${member.authority}" readonly/>
        </div>

        <div class="buttons">
            <button class="modiBtn" type="button" onClick="location.href='/member/modify?id=${member.id}'">수정</button>
            <sec:authorize access="hasAuthority('admin')">
                <button class="deleteBtn" type="button" id="modalOpenBtn">삭제</button>
            </sec:authorize>
        </div>
    </form>
</section>

<sec:authorize access="hasAuthority('admin')">
<div id="modal">
    <div class="modalContent">
        <p>사원정보를 삭제 하시겠습니까?</p>
        <button type="button" id="modalCloseBtn">취소</button>
        <button type="submit" form="removeForm">삭제</button>
    </div>
    <div class="modalLayer"></div>
</div>

<div style="display: none">
    <form id="removeForm" action="/member/remove" method="post">
        <input type="text" name="id" value="${member.id}">
    </form>
</div>
</sec:authorize>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js" integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

<script src="/js/modal.js"></script>

</body>
</html>
