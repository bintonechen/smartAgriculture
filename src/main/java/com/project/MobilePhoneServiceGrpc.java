package com.project;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.62.2)",
    comments = "Source: smartAgriculture.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MobilePhoneServiceGrpc {

  private MobilePhoneServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "MobilePhoneService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.project.SmartAgricultureProto.MobilePhoneRequest,
      com.project.SmartAgricultureProto.MobilePhoneResponse> getMobilePhoneService1Method;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "mobilePhoneService1",
      requestType = com.project.SmartAgricultureProto.MobilePhoneRequest.class,
      responseType = com.project.SmartAgricultureProto.MobilePhoneResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.project.SmartAgricultureProto.MobilePhoneRequest,
      com.project.SmartAgricultureProto.MobilePhoneResponse> getMobilePhoneService1Method() {
    io.grpc.MethodDescriptor<com.project.SmartAgricultureProto.MobilePhoneRequest, com.project.SmartAgricultureProto.MobilePhoneResponse> getMobilePhoneService1Method;
    if ((getMobilePhoneService1Method = MobilePhoneServiceGrpc.getMobilePhoneService1Method) == null) {
      synchronized (MobilePhoneServiceGrpc.class) {
        if ((getMobilePhoneService1Method = MobilePhoneServiceGrpc.getMobilePhoneService1Method) == null) {
          MobilePhoneServiceGrpc.getMobilePhoneService1Method = getMobilePhoneService1Method =
              io.grpc.MethodDescriptor.<com.project.SmartAgricultureProto.MobilePhoneRequest, com.project.SmartAgricultureProto.MobilePhoneResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "mobilePhoneService1"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.project.SmartAgricultureProto.MobilePhoneRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.project.SmartAgricultureProto.MobilePhoneResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MobilePhoneServiceMethodDescriptorSupplier("mobilePhoneService1"))
              .build();
        }
      }
    }
    return getMobilePhoneService1Method;
  }

  private static volatile io.grpc.MethodDescriptor<com.project.SmartAgricultureProto.MobilePhoneRequest,
      com.project.SmartAgricultureProto.MobilePhoneResponse> getMobilePhoneService2Method;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "mobilePhoneService2",
      requestType = com.project.SmartAgricultureProto.MobilePhoneRequest.class,
      responseType = com.project.SmartAgricultureProto.MobilePhoneResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.project.SmartAgricultureProto.MobilePhoneRequest,
      com.project.SmartAgricultureProto.MobilePhoneResponse> getMobilePhoneService2Method() {
    io.grpc.MethodDescriptor<com.project.SmartAgricultureProto.MobilePhoneRequest, com.project.SmartAgricultureProto.MobilePhoneResponse> getMobilePhoneService2Method;
    if ((getMobilePhoneService2Method = MobilePhoneServiceGrpc.getMobilePhoneService2Method) == null) {
      synchronized (MobilePhoneServiceGrpc.class) {
        if ((getMobilePhoneService2Method = MobilePhoneServiceGrpc.getMobilePhoneService2Method) == null) {
          MobilePhoneServiceGrpc.getMobilePhoneService2Method = getMobilePhoneService2Method =
              io.grpc.MethodDescriptor.<com.project.SmartAgricultureProto.MobilePhoneRequest, com.project.SmartAgricultureProto.MobilePhoneResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "mobilePhoneService2"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.project.SmartAgricultureProto.MobilePhoneRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.project.SmartAgricultureProto.MobilePhoneResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MobilePhoneServiceMethodDescriptorSupplier("mobilePhoneService2"))
              .build();
        }
      }
    }
    return getMobilePhoneService2Method;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MobilePhoneServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MobilePhoneServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MobilePhoneServiceStub>() {
        @java.lang.Override
        public MobilePhoneServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MobilePhoneServiceStub(channel, callOptions);
        }
      };
    return MobilePhoneServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MobilePhoneServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MobilePhoneServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MobilePhoneServiceBlockingStub>() {
        @java.lang.Override
        public MobilePhoneServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MobilePhoneServiceBlockingStub(channel, callOptions);
        }
      };
    return MobilePhoneServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MobilePhoneServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MobilePhoneServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MobilePhoneServiceFutureStub>() {
        @java.lang.Override
        public MobilePhoneServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MobilePhoneServiceFutureStub(channel, callOptions);
        }
      };
    return MobilePhoneServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     * <pre>
     *Server streaming RPC
     * </pre>
     */
    default void mobilePhoneService1(com.project.SmartAgricultureProto.MobilePhoneRequest request,
        io.grpc.stub.StreamObserver<com.project.SmartAgricultureProto.MobilePhoneResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getMobilePhoneService1Method(), responseObserver);
    }

    /**
     * <pre>
     *Unary RPC
     * </pre>
     */
    default void mobilePhoneService2(com.project.SmartAgricultureProto.MobilePhoneRequest request,
        io.grpc.stub.StreamObserver<com.project.SmartAgricultureProto.MobilePhoneResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getMobilePhoneService2Method(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service MobilePhoneService.
   */
  public static abstract class MobilePhoneServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return MobilePhoneServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service MobilePhoneService.
   */
  public static final class MobilePhoneServiceStub
      extends io.grpc.stub.AbstractAsyncStub<MobilePhoneServiceStub> {
    private MobilePhoneServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MobilePhoneServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MobilePhoneServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     *Server streaming RPC
     * </pre>
     */
    public void mobilePhoneService1(com.project.SmartAgricultureProto.MobilePhoneRequest request,
        io.grpc.stub.StreamObserver<com.project.SmartAgricultureProto.MobilePhoneResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getMobilePhoneService1Method(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *Unary RPC
     * </pre>
     */
    public void mobilePhoneService2(com.project.SmartAgricultureProto.MobilePhoneRequest request,
        io.grpc.stub.StreamObserver<com.project.SmartAgricultureProto.MobilePhoneResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getMobilePhoneService2Method(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service MobilePhoneService.
   */
  public static final class MobilePhoneServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<MobilePhoneServiceBlockingStub> {
    private MobilePhoneServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MobilePhoneServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MobilePhoneServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     *Server streaming RPC
     * </pre>
     */
    public java.util.Iterator<com.project.SmartAgricultureProto.MobilePhoneResponse> mobilePhoneService1(
        com.project.SmartAgricultureProto.MobilePhoneRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getMobilePhoneService1Method(), getCallOptions(), request);
    }

    /**
     * <pre>
     *Unary RPC
     * </pre>
     */
    public com.project.SmartAgricultureProto.MobilePhoneResponse mobilePhoneService2(com.project.SmartAgricultureProto.MobilePhoneRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getMobilePhoneService2Method(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service MobilePhoneService.
   */
  public static final class MobilePhoneServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<MobilePhoneServiceFutureStub> {
    private MobilePhoneServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MobilePhoneServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MobilePhoneServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     *Unary RPC
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.project.SmartAgricultureProto.MobilePhoneResponse> mobilePhoneService2(
        com.project.SmartAgricultureProto.MobilePhoneRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getMobilePhoneService2Method(), getCallOptions()), request);
    }
  }

  private static final int METHODID_MOBILE_PHONE_SERVICE1 = 0;
  private static final int METHODID_MOBILE_PHONE_SERVICE2 = 1;

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
        case METHODID_MOBILE_PHONE_SERVICE1:
          serviceImpl.mobilePhoneService1((com.project.SmartAgricultureProto.MobilePhoneRequest) request,
              (io.grpc.stub.StreamObserver<com.project.SmartAgricultureProto.MobilePhoneResponse>) responseObserver);
          break;
        case METHODID_MOBILE_PHONE_SERVICE2:
          serviceImpl.mobilePhoneService2((com.project.SmartAgricultureProto.MobilePhoneRequest) request,
              (io.grpc.stub.StreamObserver<com.project.SmartAgricultureProto.MobilePhoneResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getMobilePhoneService1Method(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              com.project.SmartAgricultureProto.MobilePhoneRequest,
              com.project.SmartAgricultureProto.MobilePhoneResponse>(
                service, METHODID_MOBILE_PHONE_SERVICE1)))
        .addMethod(
          getMobilePhoneService2Method(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.project.SmartAgricultureProto.MobilePhoneRequest,
              com.project.SmartAgricultureProto.MobilePhoneResponse>(
                service, METHODID_MOBILE_PHONE_SERVICE2)))
        .build();
  }

  private static abstract class MobilePhoneServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MobilePhoneServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.project.SmartAgricultureProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("MobilePhoneService");
    }
  }

  private static final class MobilePhoneServiceFileDescriptorSupplier
      extends MobilePhoneServiceBaseDescriptorSupplier {
    MobilePhoneServiceFileDescriptorSupplier() {}
  }

  private static final class MobilePhoneServiceMethodDescriptorSupplier
      extends MobilePhoneServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    MobilePhoneServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (MobilePhoneServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MobilePhoneServiceFileDescriptorSupplier())
              .addMethod(getMobilePhoneService1Method())
              .addMethod(getMobilePhoneService2Method())
              .build();
        }
      }
    }
    return result;
  }
}
