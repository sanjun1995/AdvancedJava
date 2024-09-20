package com.example.demo.b2c.autoconfig;

import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.util.StringUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.core.env.Environment;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author caozhixin
 * @date 2024/9/20 14:04
 */
@Configuration
@ConditionalOnProperty(prefix = "fsmx", name = "enable", havingValue = "false")
//@ConditionalOnBean({SqlSessionTemplate.class})
@ConditionalOnClass({DispatcherGenericFsmEngine.class})
@AutoConfigureAfter({MybatisAutoConfiguration.class})
@EnableConfigurationProperties(FsmxProperties.class)
public class FsmxAutoConfiguration {

    @ConditionalOnClass(GenericFsmActionAdaptorBeanRegistrar.class)
    public static class GenericFsmActionAdaptorConfiguration {
        @Bean
        @ConditionalOnMissingBean
        public GenericFsmActionAdaptorBeanRegistrar genericFsmActionAdaptorBeanRegistrar(Environment environment) {
            String configScanBasePackages = environment.getProperty("fsmx.action-scan-base-packages");

            String[] scanBasePackages = StringUtils.isEmpty(configScanBasePackages)
                    ? new String[]{"com.example"} // 默认包名
                    : StringUtils.tokenizeToStringArray(configScanBasePackages, ",");

            return new GenericFsmActionAdaptorBeanRegistrar(scanBasePackages);
        }
    }
}
