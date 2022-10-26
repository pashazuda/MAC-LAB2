package pr3;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.Date;

public class SendDelayedPing extends WakerBehaviour {
    private AID receiver;
    public SendDelayedPing(Agent a, long wakeupDate, AID receiver) {
        super(a, wakeupDate);
        this.receiver = receiver;
    }

    @Override
    protected void onWake() {
        ACLMessage msg = new ACLMessage(ACLMessage.CFP);
        msg.setContent("Ping");
        msg.setProtocol("Ping");
        msg.addReceiver(receiver);
        myAgent.send(msg);
    }
}
