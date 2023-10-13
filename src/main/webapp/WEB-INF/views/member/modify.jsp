<%--
  Created by IntelliJ IDEA.
  User: windows
  Date: 2023-07-07
  Time: 오후 5:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>사원정보 수정 페이지</title>
    <link rel="stylesheet" href="/css/memberAdd.css">
</head>
<body>
<my:navBarFroAdmin></my:navBarFroAdmin>
<my:alert></my:alert>

<section class="container">
    <header>사원정보 수정</header>
    <form id="modifyForm" action="/member/modify" method="post" class="form">
        <div class="input-box address">
            <label>아이디</label>
            <input type="text" value="${member.id}" name="id" readonly />
            <label>비밀번호</label>
            <input type="password" value="" name="password"  />
            <div class="label-mg">이름 / 직급</div>
            <div class="column">
                <input type="text" value="${member.name}" name="name" required />
                <div class="select-box">
                    <select name="position">
                        <option hidden>${member.position}</option>
                        <option>사원</option>
                        <option>대리</option>
                        <option>과장</option>
                        <option>차장</option>
                        <option>부장</option>
                        <option>팀장</option>
                        <option>이사</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="input-box">
            <label>이메일</label>
            <input type="email" value="${member.email}" name="email" required />
        </div>
        <div class="column">
            <div class="input-box">
                <label>휴대폰</label>
                <input type="text" value="${member.phoneNum}" name="phoneNum" required />
            </div>
            <div class="input-box">
                <label>생년월일</label>
                <input type="date" value="${member.birthDate}" name="birthDate" required />
            </div>
        </div>
        <div class="gender-box">
            <label>성별</label>
            <div class="gender-option">
                <div class="gender">
                    <input type="radio" id="check-male" name="gender" value="남"
                    ${member.gender == "남" ? "checked" : ""} />
                    <label for="check-male">남</label>
                </div>
                <div class="gender">
                    <input type="radio" id="check-female" name="gender" value="여"
                    ${member.gender == "여" ? "checked" : ""} />
                    <label for="check-female">여</label>
                </div>
            </div>
        </div>
        <div class="input-box address">
            <label>주소</label><br>
            <input type="button" onclick="goPopup();" value="주소 찾기" class="addressBtn"><br>
            <input type="hidden" placeholder="우편번호" id="zipNo" name="zipNo" required>
            <input type="text" id="address" name="address" value="${member.address}" required />
        </div>
        <button class="sub" type="button" id="modalOpenBtn">수정</button>
    </form>
</section>

<div id="modal">
    <div class="modalContent">
        <p>사원정보를 수정 하시겠습니까?</p>
        <button type="button" id="modalCloseBtn">취소</button>
        <button type="submit" form="modifyForm">수정</button>
    </div>
    <div class="modalLayer"></div>
</div>



<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js" integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

<script src="/js/modal.js"></script>
<script src="/js/address.js"></script>
</body>
</html>
