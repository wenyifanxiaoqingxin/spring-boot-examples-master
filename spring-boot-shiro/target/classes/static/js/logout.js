$(function()
{
    $("#logout").click(function(){
        $.post("/logout", {
        }, function(result) {
            if (result.status == 200) {
                location.href = "/login";
            } else {
                $("#erro").html(result.message);
            }
        });
    });
});