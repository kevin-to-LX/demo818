package com.hik.demo.service.impl;

import com.hik.demo.dao.mapper.UserLoginMapper;
import com.hik.demo.dao.pojo.UserLogin;
import com.hik.demo.service.MyCasService;
import com.hik.demo.utils.UUIDUtil;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.plugin.util.UIUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Creat by kevin on 2018/8/10
 */
@Service
public class MyCasServiceImpl implements MyCasService {

    @Autowired
    private UserLoginMapper userLoginMapper;


    @Override
    public boolean registerData(String username, String password) {
        //生成uuid
        String id = UUIDUtil.getOneUUID();
        //将用户名作为盐值
        ByteSource salt = ByteSource.Util.bytes(username);
        /**
         * sha1加密：
         * 使用SimpleHash类对原始密码进行加密。
         * 第一个参数代表使用sha1方式加密
         * 第二个参数为原始密码
         * 第三个参数为盐值，即用户名
         * 第四个参数为加密次数
         * 最后用toHex()方法将加密后的密码转成String
         * */
        String newPwd = new SimpleHash("SHA-1",password,salt,1024).toHex();
        UserLogin userLogin = new UserLogin();
        userLogin.setUserName(username);
        userLogin.setUserPsw(newPwd);
        userLogin.setUserLevel(123);

        Map<String, Object> userInfo = queryInfoByName(username);
        if(userInfo == null){
            userLoginMapper.insert(userLogin);
            return true;
        }
        return false;
    }

    @Override
    public  Map<String, Object> queryInfoByName(String userName) {
        return userLoginMapper.queryInfoByName(userName);
    }
}
