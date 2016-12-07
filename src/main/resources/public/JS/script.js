$(document).ready(function () {
    $("#signup_modal").click(function () {

        $("#form_id").validate();
        if ($("#form_id").valid()) {
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
                    //dataType: 'json',
                    async: true
                }).done(function (msg) {
                if(msg.toString() == "OK") {
                    $("#name").val("");
                    $("#email").val("");
                    $("#password").val("");
                    $("#myModal").modal("hide");
                    alert("User successfully registered!" +
                        "\nCheck out your e-mail address for confirmation mail.")

                }if (msg.toString() == "NO"){
                    alert("This e-mail address is already taken!")
                }
            }).fail(function (msg) {
                alert(msg)
            });
        }else {
            console.log("Not valid")
        }
    })
});