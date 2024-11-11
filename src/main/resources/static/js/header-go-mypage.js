const goMyPageBtn = document.getElementById("mypage-btn");

goMyPageBtn ? goMyPageBtn.addEventListener("click",(event)=>{
    event.preventDefault();
    console.log("mypageBtn loaded");
    window.location.assign(`https://localhost:3000/mypage/bookinglist`);
}) : null;