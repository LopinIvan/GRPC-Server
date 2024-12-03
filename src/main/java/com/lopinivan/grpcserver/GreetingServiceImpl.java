package com.lopinivan.grpcserver;

import io.grpc.stub.StreamObserver;

public class GreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase {
    @Override
    public void greeting(GreetingServiceOuterClass.HelloRequest request,
                         StreamObserver<GreetingServiceOuterClass.HelloResponse> responseObserver) {

        for(int i = 0; i < 100; i++){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            GreetingServiceOuterClass.HelloResponse response = GreetingServiceOuterClass
                    .HelloResponse.newBuilder().setGreeting("Hello from server " + request.getName())
                    .build();


            System.out.println(request + " -- " + i);

            responseObserver.onNext(response);
        }

        responseObserver.onCompleted();
    }
}
