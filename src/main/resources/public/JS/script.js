$(document).ready(function () {
    $("#signup_modal").click(function () {
        $("#form_id").validate();
        if ($("#form_id").valid()) {
            var $name = $("#name").val();
            var $email = $("#email").val();
            var $password = $("#password").val()
            $.post("/registration",
                {
                    name: $name,
                    email: $email,
                    password: $password

                })
        }
    })
});