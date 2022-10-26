package lc5;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class HelloReceivingBehaviour extends Behaviour {
    private Agent myAgent;
    private MessageTemplate mt;
    public HelloReceivingBehaviour(Agent a) {
        super(a);
        this.myAgent = a;
//        this.mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
    }

    private int count = 0;

    @Override
    public void action() {
        ACLMessage msg = myAgent.receive();
        if (msg != null){
            System.err.println("I received msg from "+msg.getSender().getLocalName()+" with content "+msg.getContent());
            count++;
        } else {
            block();
        }
    }

    @Override
    public boolean done() {
        return count > 10;
    }

    @Override
    public void onStart() {
        System.out.println("Starting receiving msgs");
    }

    @Override
    public int onEnd() {
        System.out.println("Finishing task");
        return -1;
    }
}
