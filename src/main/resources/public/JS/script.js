function clear() {
    $("#name").val("");
    $("#email").val("");
    $("#password").val("");
}
function hideLogout() {
    $("#logout").hide();
    $("#signup").show();
    $("#login").show();
}
function showLogout() {
    $("#signup").hide();
    $("#login").hide();
    $("#logout").show();
}

$(document).ready(function () {

    if (localStorage["status"] == null) {
        localStorage["status"] ="logged_out"
    }

    if (localStorage["status"] == "logged_in") {
        showLogout()
    } else {
        hideLogout()
    }

    // handles logout
    $("#logout").click(function () {
        $.ajax(
            {
                url: '/logout',
                type: 'POST',
                async: true
            }).done(function (msg) {
            if (msg.toString() == "OK") {
                hideLogout();
                localStorage["status"] = "logged_out";
            }
        });
    });

    // handles sign up
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

    // handles login
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
                    showLogout();
                    localStorage["status"] = "logged_in";

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

    $(".review_modal_button").click(function () {
        var product_name = this.value
        $("#review_modal_label").append("Reviews of " + product_name);
        $.ajax(
            {
                url: 'http://localhost:61000/api/review?title=' + product_name,
                type: 'GET',
                dataType: "JSON",
                async: true
            }).fail(function () {
            $("#review_modal_body").append($("<b></b>").text("Something went wrong! Reviews are not available now, please come back later."));
            }).done(function (response) {
            $.each(response, function (index) {
                $("#review_modal_body").append($("<b></b>").text(response[index].text));
                $("#review_modal_body").append($("<br>"))
                $("#review_modal_body").append($("<a></a>").attr("href", response[index].url).attr("target", "_blank").text(response[index].url));
                $("#review_modal_body").append($("<br>"))
                $("#review_modal_body").append($("<br>"))
            });
        });
    });
    $("#modal_review").on("hidden.bs.modal" ,function () {
        $("#review_modal_label").empty();
        $("#review_modal_body").empty();
    })
});