package com.hwc.poc.orderservice.infra.config;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringContextConfiguration {

    @Bean
    public SpringContextUtil getSpringContextUtil() {
        return new SpringContextUtil();
    }

    @Autowired
    private HikariDataSource dataSource;

    @Value("${spring.cloud.openfeign.httpclient.max-connections}")
    private int maxConnections;

    @Value("${spring.cloud.openfeign.httpclient.max-connections-per-route}")
    private int maxPerRoute;

    @PostConstruct
    public  void logHikariConfig() {
        System.out.println("================== HikariCP Config ==================");
        System.out.println("Pool Name:" + dataSource.getPoolName());
        System.out.println("Maximum Pool Size:" + dataSource.getMaximumPoolSize());
        System.out.println("Minimum Idle:" + dataSource.getMinimumIdle());
        System.out.println("Connection Timeout:" + dataSource.getConnectionTimeout());
        System.out.println("Idle Timeout:" + dataSource.getIdleTimeout());
        System.out.println("Max LifeTime:" + dataSource.getMaxLifetime());
        System.out.println("======================================================");
        System.out.println("================= FeignClient Config =================");
        System.out.println("Max Connections:" + maxConnections);
        System.out.println("Max Connections Per Route:" + maxPerRoute);
        System.out.println("======================================================");

    }
}
