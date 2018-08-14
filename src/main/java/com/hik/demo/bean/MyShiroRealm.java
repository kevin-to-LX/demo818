package com.hik.demo.bean;

import com.hik.demo.service.MyCasService;
import org.apache.shiro.authc.*;

import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Creat by kevin on 2018/8/10
 */

public class MyShiroRealm extends AuthenticatingRealm {
    @Autowired
    private MyCasService myCasService;
    private SimpleAuthenticationInfo info = null;



/**
     * 1.doGetAuthenticationInfo，获取认证消息，如果数据库中没有数，返回null，如果得到了正确的用户名和密码，
     * 返回指定类型的对象
     *
     * 2.AuthenticationInfo 可以使用SimpleAuthenticationInfo实现类，封装正确的用户名和密码。
     *
     * 3.token参数 就是我们需要认证的token
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //将token转换成UsernamePasswordToken
        // a
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        //获取用户名
        String userName = usernamePasswordToken.getUsername();
        //查询数据库库，是否查询到用户名和密码的用户
        Map<String, Object> userInfo = myCasService.queryInfoByName(userName);

        if (userInfo != null){
            Object principal = userInfo.get("user_name");
            Object credentials = userInfo.get("user_psw");
            // 获取盐值，即用户名
            ByteSource salt = ByteSource.Util.bytes(userName);
            String realmName = this.getName();
            // 将账户名，密码，盐值，realmName实例化到SimpleAuthenticationInfo中交给Shiro来管理
            info = new SimpleAuthenticationInfo(principal,credentials,salt,realmName);



        }else {
            //若没有查询到，抛出一个异常
            throw new AuthenticationException();
        }

        return info;
    }
}
