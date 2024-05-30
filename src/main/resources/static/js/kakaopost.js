function execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById("host_address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("host_address_detail").focus();
        }
    }).open();
}






     function daumPostcode() {
            new daum.Postcode({
                oncomplete: function(data) {

		var addr = ''; // 주소 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            document.getElementById("postcode").value = data.zonecode;
            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById("host_address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("host_address_detail").focus();
                    // 위도 및 경도 좌푯값 구하기
                    var geocoder = new kakao.maps.services.Geocoder();
                    geocoder.addressSearch(data.roadAddress, function(result, status) {
                        if (status === kakao.maps.services.Status.OK) {
							document.getElementById("host_lat").value = result[0].y
							document.getElementById("host_lng").value = result[0].x
                            console.log('위도 : ' + result[0].y);
                            console.log('경도 : ' + result[0].x);
                        }
                    });
                }
                
             
            }).open();
        }

        // 함수 호출하여 우편번호 찾기 실행
