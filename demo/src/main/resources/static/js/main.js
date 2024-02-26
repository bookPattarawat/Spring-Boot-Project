function clickLogout(){
    localStorage.clear();
    localStorage.removeItem('username');
    localStorage.removeItem('roleId');
    window.location.href = `/login`;
}

let username = localStorage.getItem('username');
let role = localStorage.getItem('roleId');
const nav = document.querySelector('#navUser');
if(username != null) {
    document.getElementById('username').innerText = username;
    if(role == "1"){
        nav.style.display = "true";
    }else {
        nav.style.display = "none";
    }
}

function clickUser() {
    window.location.href = `/user/list`;
}

function clickStudent() {
    window.location.href = `/student/list`;
}

