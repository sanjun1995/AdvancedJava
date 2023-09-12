package com.example.demo.algo.zhuan;

import org.apache.commons.validator.routines.UrlValidator;

/**
 * @author caozhixin
 * @date 2023/9/12 16:35
 */
public class UrlDemo {
    public static boolean isURL(String str) {
        UrlValidator validator = new UrlValidator();
        return validator.isValid(str);
    }

    public static void main(String[] args) {
        String url= "https://s1-1255305554.cos.ap-beijing.myqcloud.com/21tEKNi0a1q/ecb34ba4-f562-4310-bb2c-c1d5f3e89fcf.pdf?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDNUKuPdFPQgfNmugWwN4US8dvylMAsupT%26q-sign-time%3D1694505952%3B1694509552%26q-key-time%3D1694505952%3B1694509552%26q-header-list%3Dhost%26q-url-param-list%3D%26q-signature%3Ddc298b1fd996c99c23ac0846c0f8633c399f24e7";
        System.out.println(isURL(url));
    }
}
