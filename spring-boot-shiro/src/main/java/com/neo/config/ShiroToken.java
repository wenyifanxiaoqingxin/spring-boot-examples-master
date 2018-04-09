package com.neo.config;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.HostAuthenticationToken;
import org.apache.shiro.authc.RememberMeAuthenticationToken;

/**
 * Created by fx on 2017/12/12.
 */
public class ShiroToken implements AuthenticationToken{

    private String userName;

    private String password;

    private boolean isRemember = false;

    private String host;

    public  ShiroToken(){

    }
    public ShiroToken(final String userName, final String password) {
        this.userName = userName;
        this.password = password;
    }
    public ShiroToken(final String userName, final String password, final boolean isRemember,final String host) {
        this.userName = userName;
        this.password = password;
        this.isRemember = isRemember;
        this.host = host;
    }

    @Override
    public Object getPrincipal() {
        return getUserName();
    }

    @Override
    public Object getCredentials() {
        return getPassword();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String  getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRemember() {
        return isRemember;
    }

    public void setRemember(boolean remember) {
        isRemember = remember;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void clear(){

        this.host = null;
        this.userName = null;
        this.isRemember = false;
        this.password = null;



    }

    public String toString(){

        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getName());
        sb.append(" - ");
        sb.append(userName);
        sb.append(", rememberMe=").append(isRemember);
        if (host != null) {
            sb.append(" (").append(host).append(")");
        }
        return sb.toString();

    }

    public boolean isRememberMe() {
        return false;
    }
}
