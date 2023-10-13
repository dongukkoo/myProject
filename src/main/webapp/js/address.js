function goPopup(){
    // 주소검색을 수행할 팝업 페이지를 호출합니다.
    // 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://business.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
    var pop = window.open("/member/jusoPopup","pop","width=570,height=420, scrollbars=yes, resizable=yes");

    // 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://business.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
    //var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes");
}


function jusoCallBack(roadFullAddr, zipNo, siNm, sggNm, emdNm) {
    var zipNoField = document.querySelector("#zipNo");
    var addressField = document.querySelector("#address");

    // 우편번호와 주소를 각각의 필드에 할당
    zipNoField.value = zipNo;
    addressField.value = roadFullAddr;

    // 상세주소까지 포함된 주소 전체값을 address 필드에 할당
    addressField.value = roadFullAddr + " " + emdNm;
}