document.addEventListener("DOMContentLoaded", function() {
    const form = document.getElementById('login-form');

    form.addEventListener('submit', function(event) {
        event.preventDefault(); // 폼의 기본 제출 동작을 막습니다.
        console.log("in js")
        const formData = new FormData(form);
        const loginData = {
            username: formData.get('username'),
            password: formData.get('password')
        };

        fetch('/mugja/loginaction', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(loginData)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                console.log("response : ",response)
                return response.json();
            })
            .then(data => {
                // 서버에서 받은 JWT 토큰을 쿠키에 저장합니다.
                setCookie('token', data.token, 1);
                console.log(data);
                console.log(document.cookie)
                window.location.href = '/mugja/main';
            })
            .catch(error => {
                console.error('There was a problem with the fetch operation:', error);
            });
    });

    function setCookie(name, value, days) {
        const date = new Date();
        date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
        const expires = "expires=" + date.toUTCString();
        document.cookie = name + "=" + value + ";" + expires + ";path=/;Secure;SameSite=None";
    }
});