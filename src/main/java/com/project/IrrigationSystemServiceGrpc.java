package com.project;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.62.2)",
    comments = "Source: irrigationSystem.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class IrrigationSystemServiceGrpc {

  private IrrigationSystemServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "IrrigationSystemService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.project.IrrigationSystemProto.IrrigationStatus,
      com.project.IrrigationSystemProto.ServerInstruction> getInstructIrrigationSystemMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "InstructIrrigationSystem",
      requestType = com.project.IrrigationSystemProto.IrrigationStatus.class,
      responseType = com.project.IrrigationSystemProto.ServerInstruction.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<com.project.IrrigationSystemProto.IrrigationStatus,
      com.project.IrrigationSystemProto.ServerInstruction> getInstructIrrigationSystemMethod() {
    io.grpc.MethodDescriptor<com.project.IrrigationSystemProto.IrrigationStatus, com.project.IrrigationSystemProto.ServerInstruction> getInstructIrrigationSystemMethod;
    if ((getInstructIrrigationSystemMethod = IrrigationSystemServiceGrpc.getInstructIrrigationSystemMethod) == null) {
      synchronized (IrrigationSystemServiceGrpc.class) {
        if ((getInstructIrrigationSystemMethod = IrrigationSystemServiceGrpc.getInstructIrrigationSystemMethod) == null) {
          IrrigationSystemServiceGrpc.getInstructIrrigationSystemMethod = getInstructIrrigationSystemMethod =
              io.grpc.MethodDescriptor.<com.project.IrrigationSystemProto.IrrigationStatus, com.project.IrrigationSystemProto.ServerInstruction>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "InstructIrrigationSystem"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.project.IrrigationSystemProto.IrrigationStatus.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.project.IrrigationSystemProto.ServerInstruction.getDefaultInstance()))
              .setSchemaDescriptor(new IrrigationSystemServiceMethodDescriptorSupplier("InstructIrrigationSystem"))
              .build();
        }
      }
    }
    return getInstructIrrigationSystemMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.project.IrrigationSystemProto.HealthCheckRequest1,
      com.project.IrrigationSystemProto.HealthCheckResponse1> getHealthCheck1Method;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "HealthCheck1",
      requestType = com.project.IrrigationSystemProto.HealthCheckRequest1.class,
      responseType = com.project.IrrigationSystemProto.HealthCheckResponse1.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.project.IrrigationSystemProto.HealthCheckRequest1,
      com.project.IrrigationSystemProto.HealthCheckResponse1> getHealthCheck1Method() {
    io.grpc.MethodDescriptor<com.project.IrrigationSystemProto.HealthCheckRequest1, com.project.IrrigationSystemProto.HealthCheckResponse1> getHealthCheck1Method;
    if ((getHealthCheck1Method = IrrigationSystemServiceGrpc.getHealthCheck1Method) == null) {
      synchronized (IrrigationSystemServiceGrpc.class) {
        if ((getHealthCheck1Method = IrrigationSystemServiceGrpc.getHealthCheck1Method) == null) {
          IrrigationSystemServiceGrpc.getHealthCheck1Method = getHealthCheck1Method =
              io.grpc.MethodDescriptor.<com.project.IrrigationSystemProto.HealthCheckRequest1, com.project.IrrigationSystemProto.HealthCheckResponse1>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "HealthCheck1"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.project.IrrigationSystemProto.HealthCheckRequest1.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.project.IrrigationSystemProto.HealthCheckResponse1.getDefaultInstance()))
              .setSchemaDescriptor(new IrrigationSystemServiceMethodDescriptorSupplier("HealthCheck1"))
              .build();
        }
      }
    }
    return getHealthCheck1Method;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static IrrigationSystemServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<IrrigationSystemServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<IrrigationSystemServiceStub>() {
        @java.lang.Override
        public IrrigationSystemServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new IrrigationSystemServiceStub(channel, callOptions);
        }
      };
    return IrrigationSystemServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static IrrigationSystemServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<IrrigationSystemServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<IrrigationSystemServiceBlockingStub>() {
        @java.lang.Override
        public IrrigationSystemServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new IrrigationSystemServiceBlockingStub(channel, callOptions);
        }
      };
    return IrrigationSystemServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static IrrigationSystemServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<IrrigationSystemServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<IrrigationSystemServiceFutureStub>() {
        @java.lang.Override
        public IrrigationSystemServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new IrrigationSystemServiceFutureStub(channel, callOptions);
        }
      };
    return IrrigationSystemServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     * <pre>
     * A bidirectional streaming RPC
     * The IrrigationSystem accepts a stream of ServerInstruction, while sending a stream of IrrigationResponse.
     * </pre>
     */
    default io.grpc.stub.StreamObserver<com.project.IrrigationSystemProto.IrrigationStatus> instructIrrigationSystem(
        io.grpc.stub.StreamObserver<com.project.IrrigationSystemProto.ServerInstruction> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getInstructIrrigationSystemMethod(), responseObserver);
    }

    /**
     */
    default void healthCheck1(com.project.IrrigationSystemProto.HealthCheckRequest1 request,
        io.grpc.stub.StreamObserver<com.project.IrrigationSystemProto.HealthCheckResponse1> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getHealthCheck1Method(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service IrrigationSystemService.
   */
  public static abstract class IrrigationSystemServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return IrrigationSystemServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service IrrigationSystemService.
   */
  public static final class IrrigationSystemServiceStub
      extends io.grpc.stub.AbstractAsyncStub<IrrigationSystemServiceStub> {
    private IrrigationSystemServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected IrrigationSystemServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new IrrigationSystemServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * A bidirectional streaming RPC
     * The IrrigationSystem accepts a stream of ServerInstruction, while sending a stream of IrrigationResponse.
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.project.IrrigationSystemProto.IrrigationStatus> instructIrrigationSystem(
        io.grpc.stub.StreamObserver<com.project.IrrigationSystemProto.ServerInstruction> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncBidiStreamingCall(
          getChannel().newCall(getInstructIrrigationSystemMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public void healthCheck1(com.project.IrrigationSystemProto.HealthCheckRequest1 request,
        io.grpc.stub.StreamObserver<com.project.IrrigationSystemProto.HealthCheckResponse1> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getHealthCheck1Method(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service IrrigationSystemService.
   */
  public static final class IrrigationSystemServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<IrrigationSystemServiceBlockingStub> {
    private IrrigationSystemServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected IrrigationSystemServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new IrrigationSystemServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<com.project.IrrigationSystemProto.HealthCheckResponse1> healthCheck1(
        com.project.IrrigationSystemProto.HealthCheckRequest1 request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getHealthCheck1Method(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service IrrigationSystemService.
   */
  public static final class IrrigationSystemServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<IrrigationSystemServiceFutureStub> {
    private IrrigationSystemServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected IrrigationSystemServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new IrrigationSystemServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_HEALTH_CHECK1 = 0;
  private static final int METHODID_INSTRUCT_IRRIGATION_SYSTEM = 1;

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
        case METHODID_HEALTH_CHECK1:
          serviceImpl.healthCheck1((com.project.IrrigationSystemProto.HealthCheckRequest1) request,
              (io.grpc.stub.StreamObserver<com.project.IrrigationSystemProto.HealthCheckResponse1>) responseObserver);
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
        case METHODID_INSTRUCT_IRRIGATION_SYSTEM:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.instructIrrigationSystem(
              (io.grpc.stub.StreamObserver<com.project.IrrigationSystemProto.ServerInstruction>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getInstructIrrigationSystemMethod(),
          io.grpc.stub.ServerCalls.asyncBidiStreamingCall(
            new MethodHandlers<
              com.project.IrrigationSystemProto.IrrigationStatus,
              com.project.IrrigationSystemProto.ServerInstruction>(
                service, METHODID_INSTRUCT_IRRIGATION_SYSTEM)))
        .addMethod(
          getHealthCheck1Method(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              com.project.IrrigationSystemProto.HealthCheckRequest1,
              com.project.IrrigationSystemProto.HealthCheckResponse1>(
                service, METHODID_HEALTH_CHECK1)))
        .build();
  }

  private static abstract class IrrigationSystemServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    IrrigationSystemServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.project.IrrigationSystemProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("IrrigationSystemService");
    }
  }

  private static final class IrrigationSystemServiceFileDescriptorSupplier
      extends IrrigationSystemServiceBaseDescriptorSupplier {
    IrrigationSystemServiceFileDescriptorSupplier() {}
  }

  private static final class IrrigationSystemServiceMethodDescriptorSupplier
      extends IrrigationSystemServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    IrrigationSystemServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (IrrigationSystemServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new IrrigationSystemServiceFileDescriptorSupplier())
              .addMethod(getInstructIrrigationSystemMethod())
              .addMethod(getHealthCheck1Method())
              .build();
        }
      }
    }
    return result;
  }
}
