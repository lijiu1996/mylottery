package com.lijiawei.lottery.consumer.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author Li JiaWei
 * @ClassName: RestTemplateConfig
 * @Description:
 * @Date: 2023/3/6 14:14
 * @Version: 1.0
 */
@SpringBootConfiguration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
