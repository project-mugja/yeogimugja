const searchBtn = document.getElementById("search-button");
const searchInput = document.getElementById("search-result");
searchBtn? console.log("searchBtn") : console.log("no searchBtn");
searchBtn.addEventListener("click",(event)=>{
    event.preventDefault();
    console.log("do search")
    window.location.assign(`https://main--mugja.netlify.app/search/all/${searchInput.value}/${localStorage.getItem("token")}`);
})