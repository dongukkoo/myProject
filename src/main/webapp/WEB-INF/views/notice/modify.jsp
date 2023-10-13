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
  <link rel="stylesheet" href="/css/community/add.css">
  <title>공지사항</title>
</head>
<body>
<my:navBarFroAdmin></my:navBarFroAdmin>
<my:alert></my:alert>

<div class="container">
  <form method="post" enctype="multipart/form-data">
    <h2>공지사항</h2>
    <p>공지사항 수정 페이지 입니다.</p>
    <div class="header">
      <div class="title">
        <label for="titleInput" class="form-label">제목</label>
        <input id="titleInput" type="text" name="title" placeholder="제목 입력" class="tit_con" value="${notice.title }" />
      </div>
    </div>
    <div class="content">
      <textarea class="cont_box" name="body" placeholder="내용 입력">${notice.body }</textarea>
    </div>
    <div class="butt">
      <button type="submit" class="submit">수정</button>
      <a href="/notice/list" class="can">취소</a>
    </div>

  </form>
</div>

</body>
</html>
