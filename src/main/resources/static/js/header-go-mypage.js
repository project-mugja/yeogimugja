const goMyPageBtn = document.getElementById("mypage-btn");

goMyPageBtn ? goMyPageBtn.addEventListener("click",(event)=>{
    event.preventDefault();
    console.log("mypageBtn loaded");
    window.location.assign(`https://main--mugja.netlify.app/mypage/bookinglist`);
}) : null;