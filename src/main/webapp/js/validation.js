const idError = document.querySelector('.id-error');
const passwordError = document.querySelector('.password-error');
const confirmPasswordError = document.querySelector('.cPassword-error');

idError.style.display = 'none';
passwordError.style.display = 'none';
confirmPasswordError.style.display = 'none';

// 아이디 유효성 검사
function validateId() {
    const idField = document.querySelector('.id');
    const idPattern = /^[a-zA-Z0-9]{4,12}$/;

    if (!idPattern.test(idField.value)) {
        idError.style.display = 'block';
        idError.style.color = 'red';
        return false;
    } else {
        idError.style.display = 'none';
        return true;
    }
}

// 아이디 중복확인 버튼이 클릭되면
$("#checkIdBtn").click(function (){
    const userId =$("#inputId").val();
   // 입력한 아이디와 ajax 요청 보내서
    $.ajax("/member/checkId/" + userId,{
        success: function (data){

            if (data.available){
                $("#availableIdMsg").show();
                $("#notAvailableIdMsg").hide();
            } else {
                $("#availableIdMsg").hide();
                $("#notAvailableIdMsg").show();
            }
        }
    })
});


// 비밀번호 유효성 검사
function validatePassword() {
    const passwordField = document.querySelector('.password');
    const passwordPattern = /^(?=.*\d)(?=.*[a-zA-Z])(?=.*[\W_]).{8,15}$/;

    if (!passwordPattern.test(passwordField.value)) {
        passwordError.style.display = 'block';
        passwordError.style.color = 'red';
        return false;
    } else {
        passwordError.style.display = 'none';
        return true;
    }
}

// 비밀번호 확인 일치 여부 검사
function validatePasswordConfirmation() {
    const passwordField = document.querySelector('.password');
    const confirmPasswordField = document.querySelector('.cPassword');

    if (passwordField.value !== confirmPasswordField.value) {
        confirmPasswordError.style.display = 'block';
        confirmPasswordError.style.color = 'red';
        return false;
    } else {
        confirmPasswordError.style.display = 'none';
        return true;
    }
}

// 폼 제출 시 유효성 검사
function validateForm() {
    const isIdValid = validateId();
    const isPasswordValid = validatePassword();
    const isPasswordConfirmationValid = validatePasswordConfirmation();

    return isIdValid && isPasswordValid && isPasswordConfirmationValid;
}

// 이벤트 리스너 등록
const idField = document.querySelector('.id');
const passwordField = document.querySelector('.password');
const confirmPasswordField = document.querySelector('.cPassword');
const form = document.querySelector('.form');

idField.addEventListener('input', validateId);
passwordField.addEventListener('input', validatePassword);
confirmPasswordField.addEventListener('input', validatePasswordConfirmation);

form.addEventListener('submit', function (event) {
    if (!validateForm()) {
        event.preventDefault(); // 폼 제출 방지
    }
});