$(document).ready(function () {
    console.log("benne");
    $("#signup_modal").click(function () {
        // $("#form_id").validate();
        // if ($("#form_id").valid()) {
            var $name = $("#name").val();
            var $email = $("#email").val();
            var $password = $("#password").val();
            console.log($name);
            $.post("/registration",
                {
                    name: $name,
                    email: $email,
                    password: $password
                })

    })
});