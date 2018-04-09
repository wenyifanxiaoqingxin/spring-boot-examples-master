$(function()
{
    var username = $("#username").val();
    var password = $("#password").val();
    $("#ajaxLogin").click(function() {
        var  password= $("#password").val();
        var regId = /(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}$)/;

        var re =/^1[34578]\d{9}$/;
        var codereg =/^\d{6}$/;
        // if(!regId.test(password)){
        //
        //     alert("身份证格式错误");
        // }
        // if(!CheckPassWord(password)){
        //     alert("密码格式错误");
        // }
        $.post("/ajaxLogin", {
            "username" : $("#username").val(),
            "password" : password
        }, function(result) {
            if (result.status == 200) {
                location.href = "index/index";
            } else {
                $("#erro").html(result.message);
            }
        });
    });
});

function CheckPassWord(password) {//必须为字母加数字且长度不小于8位
    var str = password;
    if (str == null || str.length <8) {
        return false;
    }
    var reg1 = new RegExp(/^[0-9A-Za-z]+$/);
    if (!reg1.test(str)) {
        return false;
    }
    var reg = new RegExp(/[A-Za-z].*[0-9]|[0-9].*[A-Za-z]/);
    if (reg.test(str)) {
        return true;
    } else {
        return false;
    }
}