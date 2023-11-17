package com.example.demo.extensibility03;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author caozhixin
 * @date 2023/11/17 17:02
 */
public class PiiDomainFieldHandlerFactory {

    /**
     * 创建领域处理器
     *
     * @return
     */
    public static List<PiiDomainFieldHandler> createPiiDomainFieldHandler() {
        List<PiiDomainFieldHandler> piiDomainFieldHandlerList = Lists.newArrayList();

        //
        piiDomainFieldHandlerList.add(new ForTestSupportFieldHandler());
        piiDomainFieldHandlerList.add(new ForTestNotSupportFieldHandler());

        return piiDomainFieldHandlerList;
    }
}
