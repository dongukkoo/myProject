<%--
  Created by IntelliJ IDEA.
  User: windows
  Date: 2023-07-28
  Time: 오전 9:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
    <link rel="stylesheet" href="/css/community/get.css">
    <title>공지사항</title>
</head>
<body>
<my:navBarFroAdmin></my:navBarFroAdmin>
<my:alert></my:alert>

<div class="container">
    <h2>공지사항</h2>
    <div class="header">
        <div class="title">
            <label class="form-label">제목</label>
            <input type="text" class="tit_con" value="${noticeList.title }" readonly />
        </div>
    </div>
    <div class="content">
        <textarea class="cont_box" readonly>${noticeList.body }</textarea>
    </div>
    <div class="butt">
        <a href="/notice/list" class="mod">목록으로</a>
        <sec:authorize access="hasAuthority('admin')">
            <button class="mod" type="button" onclick="location.href='/notice/modify/${noticeList.id}'">수정</button>
            <button class="can" type="button" id="modalOpenBtn">삭제</button>
        </sec:authorize>
    </div>

</div>

<div id="modal">
    <div class="modalContent">
        <p>게시글을 삭제 하시겠습니까?</p>
        <button type="button" id="modalCloseBtn">취소</button>
        <button type="submit" form="removeForm">삭제</button>
    </div>
    <div class="modalLayer"></div>
</div>

<div style="display: none">
    <form id="removeForm" action="/notice/remove" method="post">
        <input type="text" name="id" value="${noticeList.id}">
    </form>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js" integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

<script src="/js/modal.js"></script>

</body>
</html>
