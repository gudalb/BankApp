$(document).ready(function(){
    $("#submitRegistration").click(function(){



        var username = $("#username").val();
        var password = $("#password").val();
        var existresponse = {};




        $.ajax({
            url: "http://localhost:8080/usernameexists/" + username,
            async: false,
            dataType: 'json',
            success: function (data) {
                existresponse = data;
            }
        });

        if (existresponse == true) {
            alert("this username is taken");
            return false;
        }

        if(username.length < 3) {
            alert("username needs to be atleast 3 characters.")
            return false;
        }

        if(password.length < 3 || password.length > 10){
            alert("use a password with between 3 and 10 characters.");
            return false;
        }


        $.ajax({
            url: "http://localhost:8080/registeruser/" + username + "/" + password,
            async: false,
            dataType: 'json',
            success: function(user) {
                setActiveUser(username,password);
                location.href = "userpage.html";
            }
        });



        return false;

    });
});