package com.hwc.poc.inventoryservice.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringContextConfiguration {

    @Bean
    public SpringContextUtil getSpringContextUtil() {
        return new SpringContextUtil();
    }
}
