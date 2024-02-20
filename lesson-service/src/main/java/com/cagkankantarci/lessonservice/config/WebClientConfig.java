package com.cagkankantarci.lessonservice.config;

import com.cagkankantarci.lessonservice.client.StudentClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfig {

    @Autowired
    private LoadBalancedExchangeFilterFunction filterFunction;

    @Bean
    public WebClient studentWebClient() {
        return WebClient.builder().baseUrl("http://student-service").filter(filterFunction).build();
    }

    @Bean
    public StudentClient studentClient() {
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(WebClientAdapter.create(studentWebClient())).build();
        return httpServiceProxyFactory.createClient(StudentClient.class);
    }
}
