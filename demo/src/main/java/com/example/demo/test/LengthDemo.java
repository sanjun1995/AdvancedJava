package com.example.demo.test;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;

/**
 * @author caozhixin
 * @date 2022/10/11 10:26 AM
 */
public class LengthDemo {
    public static void main(String[] args) {
        String batchName = "交易单号:15794191956222987161579145805296488649";
        // batchName最大长度为32
        int maxLength = 32;
        if (StringUtils.isNotBlank(batchName) && batchName.length() > maxLength) {
            System.out.println(batchName.substring(0, maxLength).substring(0, maxLength - 3) + "...");
        }
    }
}
