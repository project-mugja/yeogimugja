let emailOk='false';


$(document).ready(function() {
    $('#mem_pwd_ck').on('input', function() {
        checkPasswordMatch();
    });

    $('#password').on('input', function() {
        checkPasswordMatch();
    });
});

function disableEmailField() {
    // Get the email value
    var emailValue = document.getElementById("mem_email").value;

    // Copy the email value to the hidden input
    document.getElementById("mem_email_hidden").value = emailValue;

    // Disable the email input and button
    document.getElementById("mem_email").disabled = true;
    document.getElementById("email_auth").disabled = true;
}

function checkPasswordMatch() {
    var password = $('#password').val();
    var confirmPassword = $('#mem_pwd_ck').val();

    if (password === '' || confirmPassword === '') {
        $('#passwordMessage').text(''); // 입력 값이 비어있으면 메시지 지우기
    } else if (password === confirmPassword) {
        $('#passwordMessage').text('비밀번호가 일치합니다.').css('color', 'green');
    } else {
        $('#passwordMessage').text('비밀번호가 일치하지 않습니다.').css('color', 'red');
    }
}

$(function () {


    $('#join').click(function(){
        console.log(emailOk);

        if($('#password').val()!=$('#mem_pwd_ck').val()){
            alert("비밀번호가 일치하지 않습니다.");
            $("#password").focus();
            return false;
        }
        if($('#mem_email').val()==""){
            alert("이메일을 입력해주세요.");
            $('#mem_email').focus();
            return false;
        }
        if($('#password').val()==""){
            alert("비밀번호를 입력해주세요.");
            $('#password').focus();
            return false;
        }
        if(emailOk=='false'){
            alert("이메일 인증을 진행해주세요");
            return false;
        }
    })
})



$(document).ready(function() {
    fn_email();
});

function fn_email() {
    $('#email_auth').click(function() {

        var email = $('#mem_email').val();

        if (email=='') {
            alert("이메일을 입력해주세요.");
            $('#mem_email').focus();
            return false;
        }

        $.ajax({
            type: "POST",
            url: "/api/member/email",
            data: JSON.stringify({ mem_email: email }),
            contentType : "application/json",
            datatype : "json",
            success: function(response) {
                // 성공 시 처리할 코드
                if(response==false){
                    alert("중복된 이메일입니다.");
                    document.getElementById('mem_email').value = '';
                    return false;
                }
                else if (response==true){
                    console.log("데이터넘어감");
                    alert("인증번호가 전송되었습니다.");
                    $("#mail_check").slideDown();
                    fn_emailOk();
                }
            },
            error: function(error) {
                // 에러 시 처리할 코드
            }
        });
    });
}

/*인증버튼클릭시 인증값 넘겨준후 비교
 값일치하면 true값 가져와서 인증성공진행*/
function fn_emailOk() {
    $('#mailcheck').click(function() {

        var emailnum = $('#number').val();

        if (emailnum=='') {
            alert("인증번호를 입력해주세요");
            $('#number').focus();
            return false;
        }

        $.ajax({
            type: "POST",
            url: "/api/member/emailOk",
            data: JSON.stringify({ mem_email: emailnum }),
            contentType : "application/json",
            datatype : "json",
            success: function(response) {
                // 성공 시 처리할 코드
                if(response==true){
                    alert("인증이 완료되었습니다.");
                    $("#mail_check").slideUp();
                    emailOk ='true';
                    disableEmailField();
                    return;
                } else if(response==false){
                    alert("인증번호가 일치하지 않습니다.");
                    $("#number").focus();
                }
                return;



            },
            error: function(error) {
                // 에러 시 처리할 코드
            }
        });
    });
}


$(document).ready(function() {
    fn_emailpwd();
    fn_pwdchk();
});


function fn_emailpwd() {
    $('#email_auth_pwd').click(function() {

        var email = $('#mem_email').val();

        if (email=='') {
            alert("이메일을 입력해주세요.");
            $('#mem_email').focus();
            return false;
        }

        $.ajax({
            type: "POST",
            url: "/api/member/emailpwd",
            data: JSON.stringify({ mem_email: email }),
            contentType : "application/json",
            datatype : "json",
            success: function(response) {
                // 성공 시 처리할 코드
                if(response==false){
                    alert("존재하지 않는 회원입니다.");
                    document.getElementById('email_auth_pwd').value = '';
                    return false;
                }
                else if (response==true){
                    console.log("데이터넘어감");
                    alert("인증번호가 전송되었습니다.");
                    $("#mail_check").slideDown();
                    fn_emailpwdOk();
                }
            },
            error: function(error) {
                console.log("존재하지 않는 이메일");
            }
        });
    });
}


function fn_emailpwdOk() {
    $('#mailcheck').click(function() {

        var emailnum = $('#number').val();

        if (emailnum=='') {
            alert("인증번호를 입력해주세요");
            $('#number').focus();
            return false;
        }

        $.ajax({
            type: "POST",
            url: "/api/member/emailOk",
            data: JSON.stringify({ mem_email: emailnum }),
            contentType : "application/json",
            datatype : "json",
            success: function(response) {
                // 성공 시 처리할 코드
                if(response==true){
                    alert("인증이 완료되었습니다.");
                    alert("랜덤비밀번호가 발송되었습니다.");
                    fn_sendpwd();

                    window.location.href="/mugja/login"

                } else if(response==false){
                    alert("인증번호가 일치하지 않습니다.");
                    $("#number").focus();
                }
                return;



            },
            error: function(error) {
                // 에러 시 처리할 코드
            }
        });
    });
}


function fn_sendpwd() {

    var email = $('#mem_email').val();

    $.ajax({
        type: "POST",
        url: "/api/member/emailSendPwd",
        data: JSON.stringify({ mem_email: email }),
        contentType : "application/json",
        datatype : "json",
        success: function(data) {
            window.location.href="/mugja/pwdchgemail";
            return;
        },
        error: function(error) {
            console.log("잘못된 접근");
        }
    });
}

function fn_pwdchk() {
    $('#pwdchg').click(function() {

        var mem_pwd = $('#mem_pwd').val();
        var password = $('#password').val();
        var password_ck = $('#mem_pwd_ck').val();

        if (mem_pwd=='') {
            alert("현재 비밀번호를 입력해주세요");
            $('#mem_pwd').focus();
            return false;
        }
        if(password==''){
            alert("변경하실 비밀번호를 입력해주세요");
            $('#password').focus();
            return false;
        }
        if(password!=password_ck){
            alert("새 비밀번호가 일치하지 않습니다.");
            return false;
        }


        $.ajax({
            type: "POST",
            url: "/api/member/mypwdChg",
            data: JSON.stringify({ mem_pwd: password }),
            contentType: "application/json",
            dataType : "json",
            success: function(response) {
                // 성공 시 처리할 코드
                console.log(response);
                if(response==true){
                    alert("비밀번호 변경이 완료되었습니다.");
                    $('#passwordForm').submit();
                } else if(response==false){
                    alert("비밀번호 변경에 실패하였습니다.");
                    $("#number").focus();
                    return false;
                }




            },
            error: function(error) {
                alert("에러발생");
            }
        });
    });
}



/*function fn_pwdchg() {
    $('#pwdchg').click(function() {
		var password_ck = $('#mem_pwd_ck').val();

        $.ajax({
            type: "POST",
            url: "/mugja/mypwdChg",
            data: JSON.stringify({ mem_pwd: password_ck }),
            contentType : "application/json",
            datatype : "json",
            success: function(response) {
                // 성공 시 처리할 코드
                if(response==true){
					alert("비밀번호 변경이 완료되었습니다.");
					
					
				} else if(response==false){
					alert("비밀번호 변경에 실패하였습니다.");
					$("#number").focus();
				}
              return;
                
                
                
            },
            error: function(error) {
                // 에러 시 처리할 코드
            }
        });
    });
}*/