package com.example;

import java.util.concurrent.TimeUnit;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.ReceiveTimeout;


public class ActorB extends AbstractActor {

    public static Props props() {
        return Props.create(ActorB.class, ActorB::new);
    }

    @Override
    public void preStart() throws Exception {
        super.preStart();
        getContext().setReceiveTimeout(java.time.Duration.ofSeconds(2));
    }
    
    public Receive createReceive() {
        return receiveBuilder()
                .match(Integer.class, this::onReceiveInteger)
                .match(ReceiveTimeout.class, message -> {
                    getContext().stop(getSelf()); 
                })
                .build();
    }    private void onReceiveInteger(Integer message) {
        System.out.println("ActorB sleeping for " + message + " seconds.");
        try {
            Thread.sleep(message * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
//    private void processMessage(int seconds) {
//        try {
//            System.out.println("ActorB sleeping for " + seconds + " seconds.");
//            TimeUnit.SECONDS.sleep(seconds);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }


//    
//    private void onMessageA(MessageA msg) {
//        System.out.println("Actor B received Message A : "+ msg.text + " from " + getSender());
//        getSender().tell(new MessageB(42), getSelf());
//        for (int i=0; i<10; i++){
//            System.out.println("Actor B doing work "+i);
//        }
//    }
//    
//
//    private void onMessageB(MessageB msg) {
//        System.out.println("Actor B received Message B : "+ msg.number + " from " + getSender());
//        if(msg.number == 999) {
//            getSender().tell(new MessageA("Goodbye!"), getSelf());
//        }
//        else {
//            getSender().tell(new MessageB(999), getSelf());
//        }
//        for (int i=0; i<10; i++){
//            System.out.println("Actor B doing more work "+i);
//        }
//
//    }
}