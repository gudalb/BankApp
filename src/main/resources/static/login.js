$(document).ready(function(){
    $("#submitLogin").click(function(){


        var username = $("#username").val();
        var password = $("#password").val();
        var url = "http://localhost:8080/login/" + username + "/" + password;
        var isVerified;


        $.ajax({
            url: url
        }).then(function(data) {
            isVerified = data.isVerified;
            console.log(isVerified);

            if(isVerified == "false") {
                $("#errormessage").text("Incorrect username or password");
            } else {
                setActiveUser(username,password);
                setLoggedIn();
                location.href = "userpage.html";
            }
        });


        return false;

    });
});

function setActiveUser(username, password) {

    var url = "http://localhost:8080/setactiveuser/" + username + "/" + password;

    $.ajax({
        url: url
    });
}

function setLoggedIn() {
    sessionStorage.setItem("userLoggedIn", "yes");
}