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

    <title>공지사항 페이지</title>
</head>
<body>
<my:navBarFroAdmin></my:navBarFroAdmin>
<my:alert></my:alert>

<div class="board-content">
    <h1>공지사항</h1>
    <sec:authorize access="hasAuthority('admin')">
        <div class="write">
            <a href="/notice/add"><i class="fa-solid fa-square-plus"></i>글쓰기</a>
        </div>
    </sec:authorize>
    <form id="categoryForm" method="get">
        <table id="table">
            <thead>
            <tr>
                <th class="tit">제목</th>
                <th class="wri">작성자</th>
                <th>작성일</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${noticeList}" var="notice">
                <tr>
                    <td class="text">
                        <a href="/notice/id/${notice.id}">${notice.title}</a>
                    </td>
                    <td>${notice.writer}</td>
                    <td>${notice.inserted}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <div class="pagination">

            <!-- 이전 버튼 -->
            <c:if test="${pageInfo.currentPageNum > 1 }">
                <a href="<c:url value='/notice/list?page=${pageInfo.currentPageNum - 1}' />" class="page-item">이전</a>
            </c:if>

            <!-- 페이지 번호들 -->
            <c:forEach begin="${pageInfo.leftPageNum }" end="${pageInfo.rightPageNum }" var="pageNum">
                <c:choose>
                    <c:when test="${pageNum == pageInfo.currentPageNum}">
                        <span class="page-item current">${pageNum}</span>
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/notice/list?page=${pageNum}' />" class="page-item">${pageNum}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <!-- 다음 버튼 -->
            <c:if test="${pageInfo.currentPageNum < pageInfo.lastPageNum }">
                <a href="<c:url value='/notice/list?page=${pageInfo.currentPageNum + 1}' />" class="page-item">다음</a>
            </c:if>
        </div>

        <my:freeSearch></my:freeSearch>
    </form>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js"
        integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>

</body>
</html>
