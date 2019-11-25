package com.jiebai.framework.common.configurer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * http请求工具RestTemplate配置
 *
 * @author lizhihui
 * @version 1.0.3
 */
@Configuration
public class RestTemplateConfigurer {

    @Bean
    public RestTemplate OKHttp3RestTemplate() {
        RestTemplate restTemplate = new RestTemplate(new OkHttp3ClientHttpRequestFactory());
        return restTemplate;
    }
}
