package com.project;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.62.2)",
    comments = "Source: soilSensor.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class SoilSensorServiceGrpc {

  private SoilSensorServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "SoilSensorService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.project.SoilSensorProto.SoilInfo,
      com.project.SoilSensorProto.SoilInfoSummary> getSendSoilInfoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SendSoilInfo",
      requestType = com.project.SoilSensorProto.SoilInfo.class,
      responseType = com.project.SoilSensorProto.SoilInfoSummary.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<com.project.SoilSensorProto.SoilInfo,
      com.project.SoilSensorProto.SoilInfoSummary> getSendSoilInfoMethod() {
    io.grpc.MethodDescriptor<com.project.SoilSensorProto.SoilInfo, com.project.SoilSensorProto.SoilInfoSummary> getSendSoilInfoMethod;
    if ((getSendSoilInfoMethod = SoilSensorServiceGrpc.getSendSoilInfoMethod) == null) {
      synchronized (SoilSensorServiceGrpc.class) {
        if ((getSendSoilInfoMethod = SoilSensorServiceGrpc.getSendSoilInfoMethod) == null) {
          SoilSensorServiceGrpc.getSendSoilInfoMethod = getSendSoilInfoMethod =
              io.grpc.MethodDescriptor.<com.project.SoilSensorProto.SoilInfo, com.project.SoilSensorProto.SoilInfoSummary>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SendSoilInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.project.SoilSensorProto.SoilInfo.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.project.SoilSensorProto.SoilInfoSummary.getDefaultInstance()))
              .setSchemaDescriptor(new SoilSensorServiceMethodDescriptorSupplier("SendSoilInfo"))
              .build();
        }
      }
    }
    return getSendSoilInfoMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SoilSensorServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SoilSensorServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SoilSensorServiceStub>() {
        @java.lang.Override
        public SoilSensorServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SoilSensorServiceStub(channel, callOptions);
        }
      };
    return SoilSensorServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SoilSensorServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SoilSensorServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SoilSensorServiceBlockingStub>() {
        @java.lang.Override
        public SoilSensorServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SoilSensorServiceBlockingStub(channel, callOptions);
        }
      };
    return SoilSensorServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SoilSensorServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SoilSensorServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SoilSensorServiceFutureStub>() {
        @java.lang.Override
        public SoilSensorServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SoilSensorServiceFutureStub(channel, callOptions);
        }
      };
    return SoilSensorServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     * <pre>
     * A client-to-server streaming RPC
     * The Server accepts a stream of SoilInfo from the SoilSensor, then return a response
     * </pre>
     */
    default io.grpc.stub.StreamObserver<com.project.SoilSensorProto.SoilInfo> sendSoilInfo(
        io.grpc.stub.StreamObserver<com.project.SoilSensorProto.SoilInfoSummary> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getSendSoilInfoMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service SoilSensorService.
   */
  public static abstract class SoilSensorServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return SoilSensorServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service SoilSensorService.
   */
  public static final class SoilSensorServiceStub
      extends io.grpc.stub.AbstractAsyncStub<SoilSensorServiceStub> {
    private SoilSensorServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SoilSensorServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SoilSensorServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * A client-to-server streaming RPC
     * The Server accepts a stream of SoilInfo from the SoilSensor, then return a response
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.project.SoilSensorProto.SoilInfo> sendSoilInfo(
        io.grpc.stub.StreamObserver<com.project.SoilSensorProto.SoilInfoSummary> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncClientStreamingCall(
          getChannel().newCall(getSendSoilInfoMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service SoilSensorService.
   */
  public static final class SoilSensorServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<SoilSensorServiceBlockingStub> {
    private SoilSensorServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SoilSensorServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SoilSensorServiceBlockingStub(channel, callOptions);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service SoilSensorService.
   */
  public static final class SoilSensorServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<SoilSensorServiceFutureStub> {
    private SoilSensorServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SoilSensorServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SoilSensorServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_SEND_SOIL_INFO = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SEND_SOIL_INFO:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.sendSoilInfo(
              (io.grpc.stub.StreamObserver<com.project.SoilSensorProto.SoilInfoSummary>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getSendSoilInfoMethod(),
          io.grpc.stub.ServerCalls.asyncClientStreamingCall(
            new MethodHandlers<
              com.project.SoilSensorProto.SoilInfo,
              com.project.SoilSensorProto.SoilInfoSummary>(
                service, METHODID_SEND_SOIL_INFO)))
        .build();
  }

  private static abstract class SoilSensorServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SoilSensorServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.project.SoilSensorProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("SoilSensorService");
    }
  }

  private static final class SoilSensorServiceFileDescriptorSupplier
      extends SoilSensorServiceBaseDescriptorSupplier {
    SoilSensorServiceFileDescriptorSupplier() {}
  }

  private static final class SoilSensorServiceMethodDescriptorSupplier
      extends SoilSensorServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    SoilSensorServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (SoilSensorServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SoilSensorServiceFileDescriptorSupplier())
              .addMethod(getSendSoilInfoMethod())
              .build();
        }
      }
    }
    return result;
  }
}
