package com.szmengran.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.szmengran.grpc.server.interceptor.GlobalServerInterceptorConfigurer;

@Configuration
public class GlobalInterceptorConfiguration {

    @Bean
    public GlobalServerInterceptorConfigurer globalInterceptorConfigurerAdapter() {
        return registry -> registry.addServerInterceptors(new LogGrpcInterceptor());
    }

}
