<%--
  Created by IntelliJ IDEA.
  User: windows
  Date: 2023-07-06
  Time: 오후 1:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>사원등록</title>
    <link rel="stylesheet" href="/css/memberAdd.css">
</head>
<body>
<my:navBarFroAdmin></my:navBarFroAdmin>
<my:alert></my:alert>

<div class="container">
    <header>사원정보 수정</header>
    <form id="modifyForm" action="/member/modify" method="post" class="form">
        <div class="input-box">
            <div class="id-field">
                <div class="label-mg">아이디</div>
                <div class="input-field">
                    <input type="text" value="${member.id}" name="id" class="id" readonly />
                </div>
            </div>
            <div class="password-field">
                <div class="label-mg">비밀번호</div>
                <div class="input-field">
                    <input type="password" value="" name="password" class="password" />
                </div>
            </div>
            <div class="confirm-field">
                <div class="input-field">
                    <input type="password" placeholder="비밀번호 확인" name="confirmPassword" class="cPassword" required />
                </div>
                <span class="cPassword-error">
                    <p class="error-text">
                        <i class="fa-solid fa-triangle-exclamation"></i>
                        비밀번호가 일치하지 않습니다.</p>
                </span>
            </div>
            <div class="error-message" id="password-error"></div>
            <div class="label-mg">이름 / 직급</div>
            <div class="column">
                <input type="text" placeholder="이름을 입력하세요." name="name" required />
                <div class="select-box">
                    <select name="position">
                        <option hidden>직급</option>
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
            <div class="label-mg">이메일</div>
            <input type="email" placeholder="이메일을 입력하세요." name="email" required />
        </div>
        <div class="column">
            <div class="input-box">
                <div class="label-mg">휴대폰</div>
                <input type="text" placeholder="휴대폰 번호를 입력하세요" name="phoneNum" required />
                <div class="error-message" id="phoneNum-error"></div>
            </div>
            <div class="input-box">
                <div class="label-mg">생년월일</div>
                <input type="date" placeholder="Enter birth date" name="birthDate" required />
            </div>
        </div>
        <div class="gender-box">
            <div class="label-mg">성별</div>
            <div class="gender-option">
                <div class="gender">
                    <input type="radio" id="check-male" name="gender" value="남" checked />
                    <label for="check-male">남</label>
                </div>
                <div class="gender">
                    <input type="radio" id="check-female" name="gender" value="여" />
                    <label for="check-female">여</label>
                </div>
            </div>
        </div>
        <div class="input-box">
            <div class="label-mg">주소</div>
            <input type="button" onclick="goPopup();" value="주소 찾기" class="addressBtn"><br>
            <input type="text" placeholder="우편번호" id="zipNo" name="zipNo" required>
            <input type="text" placeholder="주소" id="address" name="address" required><br>
        </div>
        <input class="sub" type="submit" value="등록">
    </form>
</div>


<script src="/js/validation.js"></script>
<script src="/js/address.js"></script>
</body>
</html>
