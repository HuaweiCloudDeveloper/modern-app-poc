package com.hwc.poc.orderservice.infra.config;

import feign.Client;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

@Configuration
public class FeignHttpsConfig {

    @Value("${spring.cloud.openfeign.httpclient.max-connections}")
    private int maxConnections;

    @Value("${spring.cloud.openfeign.httpclient.max-connections-per-route}")
    private int maxPerRoute;

    /**
     * 配置Feign的HTTPS客户端（信任所有证书）
     */
    @Bean
    public Client feignClient() {
        try {
            // 1. 创建信任所有证书的策略
            TrustStrategy trustAllStrategy = new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true; // 信任所有证书
                }
            };

            // 2. 构建SSL上下文
            SSLContext sslContext = SSLContexts.custom()
                    .loadTrustMaterial(null, trustAllStrategy)
                    .build();

            // 3. 创建SSL连接工厂（忽略主机名验证）
            SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(
                    sslContext,
                    NoopHostnameVerifier.INSTANCE // 不验证主机名
            );

            // 4. 构建Apache HttpClient
            org.apache.http.impl.client.CloseableHttpClient httpClient = org.apache.http.impl.client.HttpClients.custom()
                    .setSSLSocketFactory(sslSocketFactory)
                    .setMaxConnTotal(maxConnections)
                    .setMaxConnPerRoute(maxPerRoute)
                    .build();

            // 5. 包装为Feign的Client
            return new feign.httpclient.ApacheHttpClient(httpClient);
        } catch (Exception e) {
            throw new RuntimeException("配置Feign HTTPS客户端失败", e);
        }
    }
}