package com.project;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.62.2)",
    comments = "Source: mobilePhone.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MobilePhoneServiceGrpc {

  private MobilePhoneServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "MobilePhoneService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.project.MobilePhoneProto.UserID,
      com.project.MobilePhoneProto.UserIDConfirmation> getSetUserIDMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetUserID",
      requestType = com.project.MobilePhoneProto.UserID.class,
      responseType = com.project.MobilePhoneProto.UserIDConfirmation.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.project.MobilePhoneProto.UserID,
      com.project.MobilePhoneProto.UserIDConfirmation> getSetUserIDMethod() {
    io.grpc.MethodDescriptor<com.project.MobilePhoneProto.UserID, com.project.MobilePhoneProto.UserIDConfirmation> getSetUserIDMethod;
    if ((getSetUserIDMethod = MobilePhoneServiceGrpc.getSetUserIDMethod) == null) {
      synchronized (MobilePhoneServiceGrpc.class) {
        if ((getSetUserIDMethod = MobilePhoneServiceGrpc.getSetUserIDMethod) == null) {
          MobilePhoneServiceGrpc.getSetUserIDMethod = getSetUserIDMethod =
              io.grpc.MethodDescriptor.<com.project.MobilePhoneProto.UserID, com.project.MobilePhoneProto.UserIDConfirmation>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetUserID"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.project.MobilePhoneProto.UserID.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.project.MobilePhoneProto.UserIDConfirmation.getDefaultInstance()))
              .setSchemaDescriptor(new MobilePhoneServiceMethodDescriptorSupplier("SetUserID"))
              .build();
        }
      }
    }
    return getSetUserIDMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.project.MobilePhoneProto.InfoRequest,
      com.project.MobilePhoneProto.InfoResponse> getMobilePhoneRequestMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MobilePhoneRequest",
      requestType = com.project.MobilePhoneProto.InfoRequest.class,
      responseType = com.project.MobilePhoneProto.InfoResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.project.MobilePhoneProto.InfoRequest,
      com.project.MobilePhoneProto.InfoResponse> getMobilePhoneRequestMethod() {
    io.grpc.MethodDescriptor<com.project.MobilePhoneProto.InfoRequest, com.project.MobilePhoneProto.InfoResponse> getMobilePhoneRequestMethod;
    if ((getMobilePhoneRequestMethod = MobilePhoneServiceGrpc.getMobilePhoneRequestMethod) == null) {
      synchronized (MobilePhoneServiceGrpc.class) {
        if ((getMobilePhoneRequestMethod = MobilePhoneServiceGrpc.getMobilePhoneRequestMethod) == null) {
          MobilePhoneServiceGrpc.getMobilePhoneRequestMethod = getMobilePhoneRequestMethod =
              io.grpc.MethodDescriptor.<com.project.MobilePhoneProto.InfoRequest, com.project.MobilePhoneProto.InfoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MobilePhoneRequest"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.project.MobilePhoneProto.InfoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.project.MobilePhoneProto.InfoResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MobilePhoneServiceMethodDescriptorSupplier("MobilePhoneRequest"))
              .build();
        }
      }
    }
    return getMobilePhoneRequestMethod;
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
     * A unary RPC
     * The Server accepts a UserID from the MobilePhone, then return a confirmation response
     * </pre>
     */
    default void setUserID(com.project.MobilePhoneProto.UserID request,
        io.grpc.stub.StreamObserver<com.project.MobilePhoneProto.UserIDConfirmation> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSetUserIDMethod(), responseObserver);
    }

    /**
     * <pre>
     * A server-to-client streaming RPC
     * The server accepts an InfoRequest from the MobilePhone, then return a stream of InfoResponse
     * </pre>
     */
    default void mobilePhoneRequest(com.project.MobilePhoneProto.InfoRequest request,
        io.grpc.stub.StreamObserver<com.project.MobilePhoneProto.InfoResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getMobilePhoneRequestMethod(), responseObserver);
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
     * A unary RPC
     * The Server accepts a UserID from the MobilePhone, then return a confirmation response
     * </pre>
     */
    public void setUserID(com.project.MobilePhoneProto.UserID request,
        io.grpc.stub.StreamObserver<com.project.MobilePhoneProto.UserIDConfirmation> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSetUserIDMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * A server-to-client streaming RPC
     * The server accepts an InfoRequest from the MobilePhone, then return a stream of InfoResponse
     * </pre>
     */
    public void mobilePhoneRequest(com.project.MobilePhoneProto.InfoRequest request,
        io.grpc.stub.StreamObserver<com.project.MobilePhoneProto.InfoResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getMobilePhoneRequestMethod(), getCallOptions()), request, responseObserver);
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
     * A unary RPC
     * The Server accepts a UserID from the MobilePhone, then return a confirmation response
     * </pre>
     */
    public com.project.MobilePhoneProto.UserIDConfirmation setUserID(com.project.MobilePhoneProto.UserID request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSetUserIDMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * A server-to-client streaming RPC
     * The server accepts an InfoRequest from the MobilePhone, then return a stream of InfoResponse
     * </pre>
     */
    public java.util.Iterator<com.project.MobilePhoneProto.InfoResponse> mobilePhoneRequest(
        com.project.MobilePhoneProto.InfoRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getMobilePhoneRequestMethod(), getCallOptions(), request);
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
     * A unary RPC
     * The Server accepts a UserID from the MobilePhone, then return a confirmation response
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.project.MobilePhoneProto.UserIDConfirmation> setUserID(
        com.project.MobilePhoneProto.UserID request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSetUserIDMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SET_USER_ID = 0;
  private static final int METHODID_MOBILE_PHONE_REQUEST = 1;

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
        case METHODID_SET_USER_ID:
          serviceImpl.setUserID((com.project.MobilePhoneProto.UserID) request,
              (io.grpc.stub.StreamObserver<com.project.MobilePhoneProto.UserIDConfirmation>) responseObserver);
          break;
        case METHODID_MOBILE_PHONE_REQUEST:
          serviceImpl.mobilePhoneRequest((com.project.MobilePhoneProto.InfoRequest) request,
              (io.grpc.stub.StreamObserver<com.project.MobilePhoneProto.InfoResponse>) responseObserver);
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
          getSetUserIDMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.project.MobilePhoneProto.UserID,
              com.project.MobilePhoneProto.UserIDConfirmation>(
                service, METHODID_SET_USER_ID)))
        .addMethod(
          getMobilePhoneRequestMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              com.project.MobilePhoneProto.InfoRequest,
              com.project.MobilePhoneProto.InfoResponse>(
                service, METHODID_MOBILE_PHONE_REQUEST)))
        .build();
  }

  private static abstract class MobilePhoneServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MobilePhoneServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.project.MobilePhoneProto.getDescriptor();
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
              .addMethod(getSetUserIDMethod())
              .addMethod(getMobilePhoneRequestMethod())
              .build();
        }
      }
    }
    return result;
  }
}
