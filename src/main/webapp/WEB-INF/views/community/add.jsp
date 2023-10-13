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
    <title>Title</title>
</head>
<body>
<my:navBarFroAdmin></my:navBarFroAdmin>
<my:alert></my:alert>

<div class="container">
  <form method="post" enctype="multipart/form-data">
    <h2>Community</h2>
    <p>글쓰기 페이지 입니다.</p>
    <div class="header">
      <div class="title">
        <label for="titleInput" class="form-label">제목</label>
        <input id="titleInput" type="text" name="title" placeholder="제목 입력" class="tit_con" value="${community.title }" />
      </div>
      <div class="mid">
        <span>카테고리</span>
        <select class="cat" name="category">
          <option value="취미">취미</option>
          <option value="고민">고민</option>
          <option value="결혼">결혼</option>
          <option value="육아">육아</option>
          <option value="칭찬">칭찬</option>
          <option value="운동">운동</option>
          <option value="식사">식사</option>
          <option value="게임">게임</option>
          <option value="기타">기타</option>
        </select>



      </div>
    </div>
    <div class="content">
      <textarea class="cont_box" name="body" placeholder="내용 입력">${community.body }</textarea>
    </div>
    <div class="butt">
      <button type="submit" class="submit">등록</button>
      <a href="/community/list" class="can">취소</a>
    </div>

  </form>
</div>

</body>
</html>
