$(document).ready(function () {
    $("#signup_modal").click(function () {
        // $("#form_id").validate();
        // if ($("#form_id").valid()) {
        var $name = $("#name").val();
        var $email = $("#email").val();
        var $password = $("#password").val();
        var arr = {name: $name, email: $email, password: $password}
        $.ajax(
            {
                url: '/registration',
                type: 'POST',
                data: JSON.stringify(arr),
                contentType: 'application/json; charset=utf-8',
                dataType: 'json',
                async: true,
                success: function (msg) {
                    alert(msg);
                }

            })
    })
});