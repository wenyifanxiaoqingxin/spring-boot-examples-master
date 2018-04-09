var opts = {
    num_edge_entries: 2,
    num_display_entries: 4,
    items_per_page: 10,
    current_page: 0
};

$(function(){
    $('.count_00').attr("class", "nav-item start active open");

    $('.count_02').attr("class", "nav-item start active open");
    showDate();
    //进入页面时加载第一页数据 并初始化分页控件
    // queryData(opts.current_page + 1,opts.items_per_page)
})
//数据为空设置null为""
var changeState=function(str){
    if(str==null){
        return "";
    }else{
        return str;
    }
}
var changePhoneOrEmail=function(emailStr,phoneStr){
    if(phoneStr!=null){
        return phoneStr;
    }else if((emailStr!=null)){
        return emailStr;
    }else{
        return "";
    }
}

//登录来源数据转换
var changeRegOpenid=function(openId){

    //1：微信，2：微博，3：facebook，4：twitter，5：QQ，6：Google+，7：Instgram，8：VK',
    if(openId==null){
        return "";
    }else{
        switch (openId)
        {
            case 1:
                return "微信";
                break;
            case 2:
                return "微博";
                break;
            case 3:
                return "facebook";
                break;
            case 4:
                return "twitter";
                break;
            case 5:
                return "QQ";
                break;
            case 6:
                return "Google+";
                break;
            case 7:
                return "Instgram";
                break;
            case 8:
                return "VK";
                break;
        }
    }
}

var showDate= function(){
    var date=new Date();
    var year=date.getFullYear();
    var month = date.getMonth()+1;
    var day = date.getDate();
    var str;
    var week = date.getDay();
    switch (week) {
        case 0:
            str = '日';
            break;
        case 1:
            str = '一';
            break;
        case 2:
            str = '二';
            break;
        case 3:
            str = '三';
            break;
        case 4:
            str = '四';
            break;
        case 5:
            str = '五';
            break;
        case 6:
            str = '六';
            break;
    }
    if (month < 10) {
        month = "0" + month;
    }
    if (day < 10) {
        day = "0" + day;
    }
    var mydate =year+'-'+month+'-'+day ;
    var myday='今天是星期'+str;
    $("#day").text(myday);
    $("#data").text(mydate);
}
// var queryBtn = function () {
//     queryData(1,opts.items_per_page)
// }
//
// var queryData = function(page,size){
//     var phoneNumber = $("#phoneNumber").val()
//     var url = '/listAjax'
//     $.ajax({
//         url: url,
//         type: 'post',
//         dataType: 'json',
//         data:{
//             // 需要传到后台的值，可带参进行分页
//             phoneNumber:phoneNumber,
//             page: page,
//             size: size
//         },
//         success: function(data){
//             //加载内容到页面中
//             var html = '';
//             $('#content').text(html)
//             if(data.code == '0000'){
//                 var content = data.data.content
//                 for (i in content){
//                     html += '<tr>'
//                     html += ('<td>'+changeState(content[i].id)+'</td>')
//                     html += ('<td>'+changeState(content[i].cNickname)+'</td>')
//                     html += ('<td>'+changePhoneOrEmail(content[i].cEmail,content[i].cMobileNum)+'</td>')
//                     html += ('<td>'+changeRegOpenid(content[i].nRegOpenidSource)+'</td>')
//                     html += ('<td nowrap="nowrap">'+changeState(content[i].dRegDate)+'</td>')
//                     html += ('<td nowrap="nowrap">'+changeState(content[i].dLastLogin)+'</td>')
//                     // html += ('<td nowrap="nowrap">')
//                     // html += ('<div class="ui primary button" onclick="delate(\''+content[i].id+'\')">删除</div>')
//                     // html += ('</td>')
//                     html += '</tr>'
//                 }
//                 $('#content').append(html)
//                 $('#usersCount').text("用户数："+data.data.totalElements);
//             }else{
//                 cautionModal(data.message)
//             }
//             //mybatis需要将data.data.number 减 1，JPA不需要做操作
//             opts.current_page = data.data.number
//             //每次成功请求后会重新初始化分页控件
//             $('#page').pagination(data.data.totalElements,opts);
//         }
//     });
// }
