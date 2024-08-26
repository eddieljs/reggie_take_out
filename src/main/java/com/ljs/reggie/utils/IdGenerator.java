package com.ljs.reggie.utils;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

/**
 * 功能：
 * 作者：ljs
 * 日期：2024/8/26 10:43
 */
public class IdGenerator {
    // 初始化一个雪花算法实例，workerId和datacenterId根据实际情况设置
    private static final Snowflake snowflake = IdUtil.createSnowflake(1, 1);

    /**
     * 获取下一个ID（长整型）
     * @return 下一个ID
     */
    public static long nextId() {
        return snowflake.nextId();
    }

    // 你可以根据需要添加更多方法
}