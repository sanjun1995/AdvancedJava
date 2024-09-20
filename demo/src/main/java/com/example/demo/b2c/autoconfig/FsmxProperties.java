package com.example.demo.b2c.autoconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author caozhixin
 * @date 2024/9/20 13:57
 */
@ConfigurationProperties(prefix = "fsmx")
public class FsmxProperties {
    private boolean enable;

    // getter 和 setter
    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
