package com.example;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import java.io.IOException;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

//class MyMessage{
//	int value;
//	MyMessage(int value){
//		this.value = value;
//	}
//}

//class Counter extends AbstractActor {
//    private int count = 0;
//
//    public static Props props() {
//        return Props.create(Counter.class, Counter::new);
//    }
//
//    @Override
//    public Receive createReceive() {
//        return receiveBuilder()
//                .matchEquals("increment", msg -> {
//                    count++;
//                    System.out.println("Counter incremented. Count: " + count);
//                })
//                .build();
//    }
//}



class Main {

    public static void main(String[] args) {
        Config customConfig = ConfigFactory.parseString("akka.log-dead-letters = off\n" +
                "akka.log-dead-letters-during-shutdown = off");
        
        ActorSystem system = ActorSystem.create("MySystem",customConfig);

        ActorRef actorARef = system.actorOf(Props.create(ActorA.class), "actorA");
        actorARef.tell("start", ActorRef.noSender());
        
    	
//        ActorSystem system = ActorSystem.create();
//        
//        ActorRef counter = system.actorOf(Counter.props(), "counter");
//        
//        
//        
//        for (int i = 0; i < 20; i++) {
//            ActorRef actorARef = system.actorOf(ActorA.props(counter), "actorA" + i);
//            actorARef.tell("incrementCounter", ActorRef.noSender());
//        }
//
//
//        system.terminate();
//        ActorRef actorARef = system.actorOf(Props.create(ActorA.class));
//      actorARef.tell(new MessageA("Starting"),actorARef);
//        actorARef.tell("Starting", actorARef); 
//        actorARef.tell(123, actorARef); 
//        actorARef.tell(456L, actorARef); 
//        actorARef.tell(3.14f, actorARef); 
//        actorARef.tell(6.28, actorARef); 
//        actorARef.tell(true, actorARef); 
//        actorARef.tell('A', actorARef);
//        actorARef.tell((byte) 30, actorARef);
        
        
        
        

//        try {
//            System.out.println("Press ENTER twice to end program.");
//            System.in.read();
//        }
//        catch (IOException ignored) { }
//        finally {
//            system.terminate();
//            System.out.println("Terminated.");
//        }
        
    }

}
