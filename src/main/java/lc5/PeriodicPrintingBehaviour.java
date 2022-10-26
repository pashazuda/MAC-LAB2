package lc5;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

public class PeriodicPrintingBehaviour extends TickerBehaviour {
    private final Agent myAgent;
    private int count =0;
    public PeriodicPrintingBehaviour(Agent a, long period) {
        super(a, period);
        this.myAgent = a;
    }

    @Override
    protected void onTick() {
        System.out.println("i sent msg to ReplierAgent");
        ACLMessage m = new ACLMessage(ACLMessage.REQUEST);
        m.addReceiver(new AID("ReplierAgent", false));
        m.setContent("Hello from "+myAgent.getLocalName());
        myAgent.send(m);
        count++;
        if (count > 10){
            stop();
        }
    }
}
