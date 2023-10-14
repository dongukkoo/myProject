// attendance.js

// 페이지 로드 시 이전 상태를 확인하고 버튼 텍스트를 설정하는 함수
function checkInitialAttendance() {
    const toggleButton = document.getElementById("toggleButton");
    const isCheckedIn = localStorage.getItem("isCheckedIn");

    if (isCheckedIn === "true") {
        toggleButton.innerText = "출근";
        toggleButton.style.backgroundColor = "#4b4276";
    } else {
        toggleButton.innerText = "퇴근";
        toggleButton.style.backgroundColor = "blue";
    }
}

// 출근/퇴근 버튼 클릭 시 처리하는 함수
function toggleAttendance() {
    const toggleButton = document.getElementById("toggleButton");
    const isCheckedIn = localStorage.getItem("isCheckedIn");

    if (isCheckedIn === "true") {
        fetch("/checkInOut", { method: "POST", credentials: "same-origin" })
            .then(() => {
                localStorage.setItem("isCheckedIn", "false");
                toggleButton.innerText = "퇴근";
                toggleButton.style.backgroundColor = "blue";
                const message = "출근했습니다.";
                const currentTime = new Date().toLocaleString();
                alert(`${message} 시간: ${currentTime}`);
            })
            .catch((error) => {
                console.error("Error while checking in/out:", error);
            });
    } else {
        fetch("/checkInOut", { method: "POST", credentials: "same-origin" })
            .then(() => {
                localStorage.setItem("isCheckedIn", "true");
                toggleButton.innerText = "출근";
                toggleButton.style.backgroundColor = "#4b4276";
                const message = "퇴근했습니다.";
                const currentTime = new Date().toLocaleString();
                alert(`${message} 시간: ${currentTime}`);
            })
            .catch((error) => {
                console.error("Error while checking in/out:", error);
            });
    }
}

// 페이지 로드 시 이전 상태를 확인하고 버튼 텍스트를 설정
checkInitialAttendance();
