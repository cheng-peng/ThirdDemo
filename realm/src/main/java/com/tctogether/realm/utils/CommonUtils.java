package com.tctogether.realm.utils;

import java.util.UUID;

/**
 * 文 件 名: CommonUtils
 * 创 建 人: CXP
 * 创建日期: 2017-01-13 14:18
 * 描    述: 工具类
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class CommonUtils {

    //生成唯一标识
    public static String getUUID(){
        String uuid = UUID.randomUUID().toString();
        return uuid.replace("-", "");
    }
}
