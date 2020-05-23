function pageLoad(){
    $(document).ready(function(){

        var activeUser = {};
        var userLoans = {};
        var userAccounts = {};

        $.ajax({
            url: "http://localhost:8080/getactiveuser",
            async: false,
            dataType: 'json',
            success: function(user) {
                activeUser = user;
            }
        });


        $.ajax({
            url: "http://localhost:8080/accounts/" + activeUser.userId,
            async: false,
            dataType: 'json',
            success: function(accounts) {
                userAccounts = accounts;
            }
        });

        $.ajax({
            url: "http://localhost:8080/loans/" + activeUser.userId,
            async: false,
            dataType: 'json',
            success: function(loans) {
                userLoans = loans;
            }
        });


        console.log(activeUser);
        console.log(userAccounts);
        console.log(userLoans);

        $("#loggedinmessage").append(activeUser.username);

        $.each(userAccounts, function(key, item)
        {
            console.log(item);
            $('#accounttable tr:last').after('<tr><td>' + item.id + '</td><td>' + item.balance + '</td><td>' + item.interestRate + '%</td></tr>');
        });

        $.each(userLoans, function(key, item)
        {
            console.log(item);
            $('#loantable tr:last').after('<tr><td>' + item.id + '</td><td>' + item.amount + '</td><td>' + item.approved + '</td></tr>');
        });

    });
}