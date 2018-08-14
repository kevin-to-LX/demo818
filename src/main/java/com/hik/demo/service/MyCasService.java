package com.hik.demo.service;

import java.util.Map;

public interface MyCasService {

    /**
     * 根据用户名查询用户信息
     * @param userName 用户名
     * @return 将数据封装到Map类型中
     */
    Map<String, Object> queryInfoByName(String userName);

    /**
     * 注册功能
     * @param username 用户名
     * @param password 密码
     * @return
     */
    public boolean registerData(String username, String password);

}
