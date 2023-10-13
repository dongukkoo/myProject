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
    <title>커뮤니티</title>
</head>
<body>
<my:navBarFroAdmin></my:navBarFroAdmin>
<my:alert></my:alert>

<div class="container">
    <h2>Community</h2>
        <div class="header">
            <div class="title">
                <label class="form-label">제목</label>
                <input type="text" class="tit_con" value="${communityList.title }" readonly />
            </div>
            <div class="writer">
                <label class="form-label">작성자</label>
                <input type="text" class="wri_con" value="${communityList.writer}" readonly>
            </div>
        </div>
        <div class="content">
            <textarea class="cont_box" readonly>${communityList.body }</textarea>
        </div>
            <div class="butt">
                <a href="/community/list" class="mod">목록으로</a>
                <sec:authorize access="authentication.name eq #communityList.writer or hasAuthority('admin')">
                    <button class="mod" type="button" onclick="location.href='/community/modify/${communityList.id}'">수정</button>
                    <button class="can" type="button" id="modalOpenBtn">삭제</button>
                </sec:authorize>
            </div>

</div>

<sec:authorize access="authentication.name eq #communityList.writer or hasAuthority('admin')">
<div id="modal">
    <div class="modalContent">
        <p>게시글을 삭제 하시겠습니까?</p>
        <button type="button" id="modalCloseBtn">취소</button>
        <button type="submit" form="removeForm">삭제</button>
    </div>
    <div class="modalLayer"></div>
</div>

<div style="display: none">
    <form id="removeForm" action="/community/remove" method="post">
        <input type="text" name="id" value="${communityList.id}">
    </form>
</div>
</sec:authorize>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js" integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

<script src="/js/modal.js"></script>

</body>
</html>
