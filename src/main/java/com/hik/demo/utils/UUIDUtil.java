package com.hik.demo.utils;

import java.util.UUID;

/**
 * Created by kevin on 2018/8/10
 */

public class UUIDUtil {
    /**
     *获取一个UUID
     */
    public static String getOneUUID(){
        //获取UUID
        String string = UUID.randomUUID().toString();
        //删去"-"符号
        return string.substring(0,8)+string.substring(9,13)+string.substring(14,18)+string.substring(19,23)+string.substring(24);

    }

    /**
     * 获得指定数目的UUID
     * @param number int 需要获得的UUID数量
     * @return String[] UUID数组
     */
    public static String[] getUUID(int number){
        if(number < 1){
            return null;
        }
        String[] ss = new String[number];
        for(int i=0;i<number;i++){
            ss[i] = getOneUUID();
        }
        return ss;
    }
}
