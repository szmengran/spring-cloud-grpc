package com.szmengran.client.service;

import org.springframework.stereotype.Service;

import com.szmengran.examples.grpc.api.HelloReply;
import com.szmengran.examples.grpc.api.HelloRequest;
import com.szmengran.examples.grpc.api.SimpleGrpc;
import com.szmengran.grpc.client.inject.GrpcClient;

import io.grpc.StatusRuntimeException;

/**
 * @Description grpc服务调用例子
 * @Date 2020/4/15 9:08 上午
 * @Author <a href="mailto:android_li@sina.cn">Joe</a>
 **/
@Service
public class ExampleService {

    @GrpcClient("grpc-server")
    private SimpleGrpc.SimpleBlockingStub simpleStub;

    public String sendMessage(String name) {
        try {
            final HelloReply response = this.simpleStub.sayHello(HelloRequest.newBuilder().setName(name).build());
            return response.getMessage();
        } catch (final StatusRuntimeException e) {
            return "FAILED with " + e.getStatus().getCode();
        }
    }
}
