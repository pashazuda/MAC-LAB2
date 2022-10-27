package lab;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.List;
import java.util.Random;

public class SendParamsBehaviour extends WakerBehaviour {
    List<AID> receivers;
    double x;
    double delta;
    private Random r = new Random();

    public SendParamsBehaviour(Agent a, long time, List<AID> receivers, double x, double delta) {
        super(a, time);
        this.receivers = receivers;
        this.x = x;
        this.delta = delta;
    }

    @Override
    public void onWake() {
        for (AID receiver : receivers) {
            ACLMessage transInit = new ACLMessage(ACLMessage.REQUEST);
            transInit.setProtocol("CalcFunction");
            transInit.setContent(x + ", " + delta);
            transInit.addReceiver(receiver);
            myAgent.send(transInit);
        }
    }
}