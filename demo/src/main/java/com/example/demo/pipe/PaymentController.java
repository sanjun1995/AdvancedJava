package com.example.demo.pipe;

import java.security.MessageDigest;
import java.util.Map;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
    private final String APP_SECRET = "xxx";

    @RequestMapping("/third_party_payment")
    @ResponseBody
    public String third_party_payment(@RequestParam Map<String, String> params) {
        String appId = params.get("app_id");
        String timestamp = params.get("timestamp");
        String paymentAmount = params.get("payment_amount");
        String sign = params.get("sign");

        // 进行参数合法性校验
        if (StringUtils.isEmpty(appId) || StringUtils.isEmpty(timestamp) || StringUtils.isEmpty(paymentAmount)
                || StringUtils.isEmpty(sign)) {
            return "{\"code\":400,\"msg\":\"参数不完整。\"}";
        }

        // 进行接口鉴权校验
        String data = String.format("%s%s%s%s", appId, timestamp, paymentAmount, APP_SECRET);
        String md5Sign = MD5(data);
        if (!md5Sign.equals(sign)) {
            return "{\"code\":400,\"msg\":\"签名不正确。\"}";
        }
        // 成功的业务逻辑处理
        return "{\"code\":200,\"msg\":\"ok\"}";
    }

    /**
     * 计算MD5值
     */
    private String MD5(String data) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(data.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                String hex = Integer.toHexString(b & 0xff);
                if (hex.length() == 1) {
                    sb.append("0");
                }
                sb.append(hex);
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
