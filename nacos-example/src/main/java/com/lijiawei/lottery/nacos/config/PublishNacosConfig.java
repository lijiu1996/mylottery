package com.lijiawei.lottery.nacos.config;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import lombok.extern.slf4j.Slf4j;

import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/**
 *  测试
 */
@Slf4j
public class PublishNacosConfig {

    public static final String NACOS_SERVER = "nacos-server:8848";
    public static final String NAMESPACE = "740be8e0-7f7f-444f-ac22-f004f4790558";
    public static final String GROUP = "TEST_GROUP";
    public static final String DATA_ID = "testNacos"; //应用名称

    public static void main(String[] args) throws NacosException, InterruptedException {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR,NACOS_SERVER);
        properties.put(PropertyKeyConst.NAMESPACE,NAMESPACE);
        ConfigService configService = NacosFactory.createConfigService(properties);
        String content = "testNacos=false";
        boolean b = configService.publishConfig(DATA_ID, GROUP, content);
        System.out.println(b? "发布成功" : "发布失败");
        String content1 = configService.getConfig(DATA_ID,GROUP,50000);
        log.info("[初始化配置]" + DATA_ID + "=" + content1);
        configService.addListener(DATA_ID, GROUP, new Listener() {
            @Override
            public Executor getExecutor() {
                return null;
            }

            @Override
            public void receiveConfigInfo(String configInfo) {
                log.info("[配置更新]" + DATA_ID + "=" + configInfo);
            }
        });
        TimeUnit.SECONDS.sleep(Long.MAX_VALUE);
    }
}
