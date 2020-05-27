function pageLoad(){
    $(document).ready(function(){


        var unapprovedLoans = {};

        $.ajax({
            url: "http://localhost:8080/unapprovedloans",
            async: false,
            dataType: 'json',
            success: function(loans) {
                unapprovedLoans = loans;
            }
        });


        $.each(unapprovedLoans, function(key, item)
        {
            console.log(item);
            $('#unapprovedTable tr:last').after('<tr><td>' + item.user.id + '</td><td>' + item.id + '</td><td>' + item.amount + '</td><td>' + item.approved + '</td><td> <button onclick="approveLoan(' + item.id + ')">Approve</button> </td></tr>');
        });

    });
}

function approveLoan(id) {


    $.ajax({
        url: "http://localhost:8080/loans/setApproved/" + id + "/true",
        success: function() {
            location.href = "adminpage.html";
        }
    });


}