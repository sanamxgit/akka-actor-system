package com.example;

import java.util.Random;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.ReceiveTimeout;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import jdk.internal.org.jline.utils.Log;


public class ActorA extends AbstractActor {
    private final Random random = new Random();

    public static Props props() {
        return Props.create(ActorA.class, ActorA::new);
    }

//    
//    private ActorA(ActorRef counter) {
//        this.counter = counter;
//    }
//    LoggingAdapter log = Logging.getLogger(getContext(). getSystem(), this);

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .matchEquals("start", msg -> startSending())
                .match(ReceiveTimeout.class, timeout -> {
                    getContext().stop(getSender());
                    getContext().getSystem().actorOf(Props.create(ActorB.class));
                })
                .build();

    }
    private void startSending() {
        for (int i = 0; i < 100; i++) {
            int randomNumber = random.nextInt(5) + 1; 
            ActorRef actorB = getContext().getSystem().actorOf(Props.create(ActorB.class));
            actorB.tell(randomNumber, getSelf());
        }
    }

//    private void onMessageA(MessageA msg) {
//        System.out.println("Actor A received Message A : "+ msg.text + " from " + getSender());
//        if(msg.text.equalsIgnoreCase("Goodbye!")) {
//          getContext().getSystem().terminate();
//        }
//        else {
//            ActorRef actorBRef = getContext().getSystem().actorOf(Props.create(ActorB.class));
//            actorBRef.tell(new MessageA("Hello!"), getSelf());
//        }
//        for (int i=0; i<10; i++){
//            System.out.println("Actor A doing work "+i);
//        }
//    }
//
//    private void onMessageB(MessageB msg) {
//        System.out.println("Actor A received Message B : "+ msg.number + " from " + getSender());
//        getSender().tell(new MessageB(999),getSelf());
//        for (int i=0; i<10; i++){
//            System.out.println("Actor A doing more work "+i);
//        }
//
//    }
}