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


function logOut() {
    $.ajax({
        url: "http://localhost:8080/resetactiveuser"
    });

    location.href = "index.html";
}

function loggedInCheck () {
    $(document).ready(function() {
        var activeUser = getLoggedInUser();

        if (activeUser.userId == null) {
            location.href = "notloggedin.html";
        }
    });
}

function adminRedirect () {
    $(document).ready(function() {
        var activeUser = getLoggedInUser();

        if (activeUser.isAdmin == "true") {
            location.href = "adminpage.html";
        }
    });
}

function adminCheck () {
    $(document).ready(function() {
        var activeUser = getLoggedInUser();

        if (activeUser.isAdmin == "false") {
            location.href = "userpage.html";
        }
    });
}

function getLoggedInUser(){
    var activeUser = {};

    $.ajax({
        url: "http://localhost:8080/getactiveuser",
        async: false,
        dataType: 'json',
        success: function(user) {
            activeUser = user;
        }
    });

    return activeUser;
}