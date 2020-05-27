$(document).ready(function(){
    $("#transferFunds").click(function(){


        var activeUser = getLoggedInUser();
        var fromAccount = {};
        var toAccount = {};




        fromAccountId = $("#fromaccount").val();
        toAccountId = $("#toaccount").val();

        $.ajax({
            url: "http://localhost:8080/account/" + fromAccountId,
            async: false,
            dataType: 'json',
            success: function(account) {
                fromAccount = account;
                console.log("from: " + fromAccount.id);
            }
        });

        $.ajax({
            url: "http://localhost:8080/account/" + toAccountId,
            async: false,
            dataType: 'json',
            success: function(account) {
                toAccount = account;
                console.log("to: " + toAccount.id);
            }
        });

        console.log("fromaccount user id" + fromAccount.id);

        if(fromAccount.id == null) {
            alert("Account to transfer from does not exist.");
            return false;
        }

        if(fromAccount.user.id != activeUser.userId) {
            alert("You can only transfer from your accounts.");
            return false;
        }

        if(toAccount.id == null) {
            alert("recipient account does not exist.")
            return false;
        }

        var amount = $("#amount").val();

        if(amount > fromAccount.balance) {
            alert("Your account has insufficient funds")
            return false;
        }


        $.ajax({
            url: "http://localhost:8080/accounts/transferfunds/" + fromAccount.id + "/" + toAccount.id + "/" + amount,
            async: false,
            dataType: 'json',
            success: function () {
                alert("Funds has been transferred.")
                location.href = "userpage.html";
            }
        });


        return false;

    });
});