$(document).ready(function(){
    $("#submitLoanForm").click(function(){


        var activeUser = {};

        $.ajax({
            url: "http://localhost:8080/getactiveuser",
            async: false,
            dataType: 'json',
            success: function(user) {
                activeUser = user;
            }
        });

        var amount = $("#loanAmount").val();

        if(amount > 0 && amount < 100000000) {
            $.ajax({
                url: "http://localhost:8080/loans/apply/" + activeUser.userId + "/" + amount,
                async: false,
                dataType: 'json',
                success: function () {
                    location.href = "userpage.html";
                }
            });
        } else {
            alert("Amount needs to be over 0 and under 100 000 000")
        }


        return false;

    });
});