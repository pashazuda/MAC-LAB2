package lab;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
@Slf4j
public class InitiatorTransferBehaviour extends WakerBehaviour {
    private final List<String> agents = Arrays.asList("Agent1", "Agent2", "Agent3");
    private Random r = new Random();
    double x;
    double delta;

    int initiatorId;

    public InitiatorTransferBehaviour(Agent a, long time, double x, double delta) {
        super(a, time);
        this.x = x;
        this.delta = delta;
    }


    @Override
    protected void onWake() {
        initiatorId = r.nextInt(agents.size());

        while (agents.get(initiatorId).equals(myAgent.getLocalName())){
            initiatorId = r.nextInt(agents.size());
        }

        ACLMessage initTrans = new ACLMessage(ACLMessage.INFORM);
        initTrans.setProtocol("Initiator");
        initTrans.setContent(x + ", " + delta);
        initTrans.addReceiver(new AID(agents.get(initiatorId), false));
        myAgent.send(initTrans);


    }

}