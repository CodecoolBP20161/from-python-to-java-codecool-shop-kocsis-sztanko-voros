function clear() {
    $("#name").val("");
    $("#email").val("");
    $("#password").val("");
}

$(document).ready(function () {
    $("#logout").hide();
    $("#signup").click(function () {
        clear()
    });
    $("#signup_modal").click(function () {
        $("#form_id").validate();
        if ($("#form_id").valid()) {
            var $name = $("#name").val();
            var $email = $("#email").val();
            var $password = $("#password").val();
            var arr = {name: $name, email: $email, password: $password};
            $.ajax(
                {
                    url: '/registration',
                    type: 'POST',
                    data: JSON.stringify(arr),
                    contentType: 'application/json; charset=utf-8',
                    async: true
                }).done(function (msg) {
                if (msg.toString() == "OK") {
                    clear();
                    $("#myModal").modal("hide");
                    alert("User successfully registered!" +
                        "\nCheck out your e-mail address for confirmation mail.")

                }
                if (msg.toString() == "NO") {
                    alert("This e-mail address is already taken!")
                }
            }).fail(function (msg) {
                alert(msg)
            });
        } else {
            console.log("Not valid")
        }
    });
    $("#login").click(function () {
        clear()
    });
    $("#login_modal_button").click(function () {
        $("#form_login_id").validate();
        if ($("#form_login_id").valid()) {
            var $email = $("#login_email").val();
            var $password = $("#login_password").val();
            var arr = {email: $email, password: $password};
            $.ajax(
                {
                    url: '/login',
                    type: 'POST',
                    data: JSON.stringify(arr),
                    contentType: 'application/json; charset=utf-8',
                    async: true
                }).done(function (msg) {
                if (msg.toString() == "OK") {
                    clear();
                    $("#modal_login").modal("hide");
                    alert("You're now logged in.");
                    $("#signup").hide();
                    $("#login").hide();
                    $("#logout").show();
                    $("#logout").click(function () {
                        $.ajax(
                            {
                                url: '/logout',
                                type: 'GET'
                            })
                    })
                }
                if (msg.toString() == "ERROR") {
                    alert("Invalid credentials. Try again!")
                }
            }).fail(function (msg) {
                alert(msg)
            });
        } else {
            console.log("Not valid")
        }
    });
});