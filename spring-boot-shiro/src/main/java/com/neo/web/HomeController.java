package com.neo.web;

import com.neo.config.ShiroToken;
import com.neo.entity.GTMDateUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class HomeController {
//    @RequestMapping({"/","/index"})
//    public String index(){
//        return"/index";
//    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, Map<String, Object> map) throws Exception{
        System.out.println("HomeController.login()");
//        // 登录失败从request中获取shiro处理的异常信息。
//        // shiroLoginFailure:就是shiro异常类的全类名.
//        String exception = (String) request.getAttribute("shiroLoginFailure");
//        System.out.println("exception=" + exception);
//        String msg = "";
//        if (exception != null) {
//            if (UnknownAccountException.class.getName().equals(exception)) {
//                System.out.println("UnknownAccountException -- > 账号不存在：");
//                msg = "UnknownAccountException -- > 账号不存在：";
//            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
//                System.out.println("IncorrectCredentialsException -- > 密码不正确：");
//                msg = "IncorrectCredentialsException -- > 密码不正确：";
//            } else if ("kaptchaValidateFailed".equals(exception)) {
//                System.out.println("kaptchaValidateFailed -- > 验证码错误");
//                msg = "kaptchaValidateFailed -- > 验证码错误";
//            } else {
//                msg = "else >> "+exception;
//                System.out.println("else -- >" + exception);
//            }
//        }
//        map.put("msg", msg);
        // 此方法不处理登录成功,由shiro进行处理
        return "/login";
    }

    @RequestMapping("/403")
    public String unauthorizedRole(){
        System.out.println("------没有权限-------");
        return "403";
    }
    @RequestMapping(value="ajaxLogin",method= RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> submitLogin(String username, String password,Model model) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        try {

            UsernamePasswordToken token = new UsernamePasswordToken(username,password);
            Subject subject =SecurityUtils.getSubject();
            //如果用户已登录，先踢出
            if (subject.isAuthenticated()) {
                resultMap.put("status", 200);
                resultMap.put("message", "已经在线");
                return resultMap;
            }
            token.setRememberMe(true);
            subject.login(token);

            resultMap.put("status", 200);
            resultMap.put("message", "登录成功");
        }catch (AuthenticationException e){
            System.out.println(e.getMessage());
            resultMap.put("status", 500);
            resultMap.put("message", "密码错误");
        }
        return resultMap;
    }

    @RequestMapping("/logout")
    public String submitLogout() {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存
//            if (LOG.isDebugEnabled()) {
//                LOG.debug("用户" + username + "退出登录");
//            }
            resultMap.put("status", 200);
            resultMap.put("message", "退出成功");
        }

        return "/login";
    }

    public static void main(String[] args){

        String no = "2017-12-15T08:56:27.516Z";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date d = simpleDateFormat.parse(no);
            Long time1 = d.getTime();
            System.out.println(time1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        GTMDateUtil gtmDateUtil = new GTMDateUtil();
        String ddd = gtmDateUtil.GTMToLocalNoSplit(no);

        try {
            Date t2 = simpleDateFormat.parse(ddd);
            Long time2 = t2.getTime();
            System.out.println(time2);
            System.out.println(new Date().getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf= new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Long t = 1513567844916L;
        long t1 =1513567871951L;
        Date dd = new Date(t);
        Date ss = new Date(t1);
        System.out.println(sdf.format(dd));
        System.out.println(sdf.format(ss));

        System.out.println(new Date().getTime());

    }
}