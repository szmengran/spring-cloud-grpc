/*
 * Copyright (c) 2016-2020 Michael Zhang <yidongnan@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 * Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.szmengran.grpc.server.autoconfigure;

import com.szmengran.grpc.server.cloud.ConsulGrpcRegistrationCustomizer;
import com.szmengran.grpc.server.config.GrpcServerProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.consul.discovery.ConsulDiscoveryProperties;
import org.springframework.cloud.consul.serviceregistry.ConsulRegistrationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ecwid.consul.v1.ConsulClient;

/**
 * Configuration class that configures the required beans for gRPC discovery via Consul.
 *
 * @author Michael (yidongnan@gmail.com)
 * @since 5/17/16
 */
@Configuration
@EnableConfigurationProperties
@ConditionalOnClass({ConsulDiscoveryProperties.class, ConsulClient.class, GrpcServerProperties.class})
public class GrpcMetadataConsulConfiguration {

    /**
     * Creates a bean that registers the gRPC endpoint via consul.
     *
     * @param grpcServerProperties The server properties used to fill in the blanks.
     * @return The newly created consulGrpcRegistrationCustomizer bean.
     */
    @ConditionalOnMissingBean
    @Bean
    public ConsulRegistrationCustomizer consulGrpcRegistrationCustomizer(
            final GrpcServerProperties grpcServerProperties) {
        return new ConsulGrpcRegistrationCustomizer(grpcServerProperties);
    }

}
