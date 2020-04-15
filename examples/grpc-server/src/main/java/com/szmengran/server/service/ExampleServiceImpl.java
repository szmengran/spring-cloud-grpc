package com.szmengran.server.service;

import com.szmengran.examples.grpc.api.HelloReply;
import com.szmengran.examples.grpc.api.HelloRequest;
import com.szmengran.examples.grpc.api.SimpleGrpc;
import com.szmengran.grpc.server.service.GrpcService;

import io.grpc.stub.StreamObserver;

@GrpcService
public class ExampleServiceImpl extends SimpleGrpc.SimpleImplBase {

    @Override
    public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
        HelloReply reply = HelloReply.newBuilder().setMessage("Hello ==> " + req.getName()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
