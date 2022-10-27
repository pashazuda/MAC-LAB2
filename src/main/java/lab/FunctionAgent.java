package lab;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Random;

@Slf4j
public class FunctionAgent extends Agent {
    double x = Math.random();
    double delta = 0.5;
    Random r = new Random();

    List<String> agents = List.of("Agent2", "Agent3");
    @Override
    protected void setup() {

        if (getLocalName().equals("Agent1")){
            System.out.printf("Значение x=%.3f\n",x);
            System.out.printf("Значение delta=%.3f\n",delta);
            ACLMessage initTrans = new ACLMessage(ACLMessage.INFORM);
            initTrans.setProtocol("Initiator");
            initTrans.setContent(x + ", " + delta);
            initTrans.addReceiver(new AID(agents.get(r.nextInt(agents.size())), false));
            send(initTrans);
//            addBehaviour(new InitiatorTransferBehaviour(this,10000, x, delta));
        }
        addBehaviour((new SlaveBehaviour()));
        addBehaviour((new InitiatorBehaviour()));
    }
}