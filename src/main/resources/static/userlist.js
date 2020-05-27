function pageLoad(){
    $(document).ready(function(){


        var users = {};

        $.ajax({
            url: "http://localhost:8080/allusers",
            async: false,
            dataType: 'json',
            success: function(data) {
                users = data;
            }
        });


        $.each(users, function(key, item)
        {
            console.log(item);
            $('#userstable tr:last').after('<tr><td>' + item.id + '</td><td>' + item.username + '</td><td>' + item.admin + '</td></tr>');
        });

    });
}

