<%--
  Created by IntelliJ IDEA.
  User: windows
  Date: 2023-07-04
  Time: 오후 12:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
    <link rel="stylesheet" href="/css/home.css">
    <title>Home</title>
</head>

<body>
<my:navBarFroAdmin></my:navBarFroAdmin>
<my:alert></my:alert>

<div class="notice">
    <h3>공지사항</h3>
    <form>
        <table>
            <thead>
            <tr>
                <th class="title">제목</th>
                <th class="writer">작성자</th>
                <th class="date">작성일</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${noticeList}" var="notice">
                <tr>
                    <td>
                        <a href="/notice/id/${notice.id}">${notice.title}</a>
                    </td>
                    <td>${notice.name}</td>
                    <td>${notice.inserted}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>
</div>

<div class="quote">
    <p id="quoteText"></p>
    <p id="quoteAuthor"></p>
</div>

<div class="weather">
    <div class="situation">
        <div class="current_temp"></div>
        <div class="weather_description"></div>
        <div class="city">위치 : </div>
    </div>
    <div class="temp">
        <div class="temp_min"></div>
        <div class="temp_max"></div>
        <div class="humidity"></div>
        <div class="wind"></div>
        <div class="cloud"></div>
    </div>
</div>

<div class="comm">
    <h3>커뮤니티</h3>
    <table>
        <thead>
        <tr>
            <th>카테고리</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${communityList}" var="community">
            <tr>
                <td>${community.category}</td>
                <td class="text">
                    <a href="/community/id/${community.id}">${community.title}</a>

                    <c:if test="${community.commentCount > 0 }">
                        <span class="badge rounded-pill text-bg-dark"> ${community.commentCount } </span>
                    </c:if>
                </td>
                <td>${community.name}</td>
                <td>${community.inserted}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script src="/js/quotes.js"></script>
<script src="/js/weather.js"></script>
</body>
</html>
