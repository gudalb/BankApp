function pageLoad(){
    $(document).ready(function(){


        var approvedLoans = {};

        $.ajax({
            url: "http://localhost:8080/approvedloans",
            async: false,
            dataType: 'json',
            success: function(loans) {
                approvedLoans = loans;
            }
        });


        $.each(approvedLoans, function(key, item)
        {
            console.log(item);
            $('#loanstable tr:last').after('<tr><td>' + item.user.id + '</td><td>' + item.id + '</td><td>' + item.amount + '</td><td>' + item.approved + '</td></tr>');
        });

    });
}