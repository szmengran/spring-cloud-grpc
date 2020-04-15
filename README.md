# spring cloud 与 grpc集成

## 版本

1.x.x.RELEASE 支持 Spring Boot 2.2.x 和 Spring Cloud Hoxton。

最新版本： `1.0.0.RELEASE`

## 用法

### grpc 服务端 + 客户端
添加Maven依赖项

```yaml
<dependency>
    <groupId>com.szmengran</groupId>
    <artifactId>spring-cloud-grpc-server</artifactId>
    <version>1.0.0.RELEASE</version>
</dependency>
<dependency>
    <groupId>com.szmengran</groupId>
    <artifactId>spring-cloud-grpc-client</artifactId>
    <version>1.0.0.RELEASE</version>
</dependency>
```

### grpc 服务端
添加Maven依赖项

```yaml
<dependency>
    <groupId>com.szmengran</groupId>
    <artifactId>spring-cloud-grpc-server</artifactId>
    <version>1.0.0.RELEASE</version>
</dependency>
```

在服务端接口实现类上添加 `@GrpcService` 注解

```java
@GrpcService
public class ExampleServiceImpl extends SimpleGrpc.SimpleImplBase {

    @Override
    public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
        HelloReply reply = HelloReply.newBuilder().setMessage("Hello ==> " + req.getName()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
```

### grpc 客户端
添加Maven依赖项

```yaml
<dependency>
    <groupId>com.szmengran</groupId>
    <artifactId>spring-cloud-grpc-client</artifactId>
    <version>1.0.0.RELEASE</version>
</dependency>
```

在 grpc 客户端的的 stub 字段上添加 `@GrpcClient(serverName)` 注解

```java
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
```