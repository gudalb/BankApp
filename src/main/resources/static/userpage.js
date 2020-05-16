function pageLoad(){
    $(document).ready(function(){

        var url = "http://localhost:8080/getactiveuser";
        var isVerified;


        $.ajax({
            url: url
        }).then(function(data) {

            $("#loggedinmessage").append(data.username);

        });

    });
}