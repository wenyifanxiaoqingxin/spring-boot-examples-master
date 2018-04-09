package com.neo.config;

import com.neo.entity.SysPermission;
import com.neo.entity.SysRole;
import com.neo.entity.UserInfo;
import com.neo.sevice.UserInfoService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

public class MyShiroRealm extends AuthorizingRealm {
    @Resource
    private UserInfoService userInfoService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        UserInfo userInfo  = (UserInfo)principals.getPrimaryPrincipal();
        for(SysRole role:userInfo.getRoleList()){
            authorizationInfo.addRole(role.getRole());
            for(SysPermission p:role.getPermissions()){
                authorizationInfo.addStringPermission(p.getPermission());
            }
        }
        return authorizationInfo;
    }

    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        System.out.println("MyShiroRealm.doGetAuthenticationInfo()");
        UsernamePasswordToken tokens = (UsernamePasswordToken) token;

        //获取用户的输入的账号.
        String username = tokens.getUsername();
        String password = String.valueOf(tokens.getPassword());
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        UserInfo userInfo = userInfoService.findByUsername(username,password);
        System.out.println("----->>userInfo="+userInfo);
        if(userInfo == null){
            throw new AccountException("帐号或密码不正确！");
        }else{

        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userInfo, //用户名
                userInfo.getPassword(), //密码
                ByteSource.Util.bytes(password),
                getName()  //realm name
        );
        return authenticationInfo;
    }


    /**
     * 明文进行谜面进行加密
     * @param args
     */
    public static void main(String[] args) {
        int hashIterations = 2;//加密的次数
        char[] credentials = {'1','2','3','4','5','6'};//密码
        String hashAlgorithmName = "md5";//加密方式
        Object simpleHash = new SimpleHash(hashAlgorithmName, credentials,
                ByteSource.Util.bytes("123456"), hashIterations);
        System.out.println("加密后的值----->" + simpleHash);
    }

}