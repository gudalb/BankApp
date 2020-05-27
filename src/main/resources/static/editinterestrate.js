function pageLoad(){
    $(document).ready(function(){



        var accounts = {};

        $.ajax({
            url: "http://localhost:8080/accounts/all",
            async: false,
            dataType: 'json',
            success: function(data) {
                accounts = data;
            }
        });


        $.each(accounts, function(key, item)
        {



            $('#accountsTable tr:last').after('<tr><td>' + item.user.id + '</td><td class="accountId">' + item.id + '</td><td>' + item.balance + '</td>' +
                '<td><input type="number" value="' + item.interestRate + '" class="interest"></td><td> ' +
                '<button class="save">Save</button>' +
                ' </td></tr>');


        });

        $(".save").click(function () {
            var accId = jQuery(this).closest("tr").find(".accountId").html();
            var interest = jQuery(this).closest("tr").find(".interest").val();


            saveInterestRate(accId, interest);

        });

    });
}




function saveInterestRate(accountId, interestRate) {

    if(interestRate < 1 || interestRate > 50) {
        alert("interest rate needs to be > 0 and < 51")
        return null;
    }

    $.ajax({
        url: "/accounts/setInterestRate/" + accountId + "/" + interestRate,
        async: false,
        dataType: 'json',
        success: function(account) {
            location.href = "editinterestrate.html";
        }
    });

}
