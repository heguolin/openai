package org.example.openai.Configration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 作者: hgl
 * 描述:API配置类
 * 创建时间: 2025/9/2
 */
@Configuration
public class ApiConfig {

    @Value("${api.base-url}")
    private String baseUrl;

    @Value("${api.key")
    private String apikey;

    @Value("${api.timeout}")
    private int timeout;

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getApikey() {
        return apikey;
    }

    public int getTimeout() {
        return timeout;
    }
}
