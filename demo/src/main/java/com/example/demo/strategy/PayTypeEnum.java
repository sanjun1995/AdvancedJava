package com.example.demo.strategy;

    public enum PayTypeEnum {
        ALI_PAY(1, "支付宝"),
        WECHAT_PAY(2, "微信支付");

        private final Integer code;
        private final String desc;

        PayTypeEnum(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public Integer getCode() {
            return code;
        }
    }
