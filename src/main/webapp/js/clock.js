var Target = document.getElementById("clock");
var Target_apm = document.getElementById("apm");

function clock() {
    var time = new Date();

    var year = time.getFullYear();
    var month = time.getMonth();
    var date = time.getDate();
    var day = time.getDay();
    var week = ['일', '월', '화', '수', '목', '금', '토'];

    var hours = time.getHours();
    var minutes = time.getMinutes();
    var seconds = time.getSeconds();
    var AmPm = "AM";
    if (hours > 12) {
        var AmPm = "PM";
        hours %= 12;
    }

    Target.innerText =
        `${year}년 ${month + 1}월 ${date}일 ${week[day]}요일 ` + `${hours}:${minutes < 10 ? `0${minutes}` : minutes}:${seconds < 10 ? `0${seconds}` : seconds}`;

    Target_apm.innerText = `${AmPm}`;

}

clock();
setInterval(clock, 1000); // 1초마다 실행