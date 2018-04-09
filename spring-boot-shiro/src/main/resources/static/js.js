$(function()
{
    alert(1)
    var username = $("#username").val();
    var password = $("#password").val();
    $("#ajaxLogin").click(function() {
        $.post("/ajaxLogin", {
            "username" : username,
            "password" : password
        }, function(result) {
            if (result.status == 200) {
                location.href = "/index";
            } else {
                $("#erro").html(result.message);
            }
        });
    });
});