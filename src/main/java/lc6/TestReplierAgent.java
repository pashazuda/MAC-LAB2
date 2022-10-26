package lc6;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

import java.util.Random;

public class TestReplierAgent extends Agent {

    @Override
    protected void setup() {
        addBehaviour(new Behaviour() {
            @Override
            public void action() {
                ACLMessage a = myAgent.receive();
                if(a != null){
                    ACLMessage reply = a.createReply();
                    reply.setContent((new Random().nextDouble() * 10 -5)+"");
                    reply.setPerformative(ACLMessage.PROPOSE);
                    myAgent.send(reply);
                } else {
                    block();
                }
            }

            @Override
            public boolean done() {
                return false;
            }
        });
    }
}
