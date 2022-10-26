package pr3;

import jade.core.AID;
import jade.core.Agent;
//import lombok.extern.slf4j.Slf4j;

//@Slf4j
public class PingPongAgent extends Agent {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(PingPongAgent.class);

    @Override
    protected void setup() {
        log.info("I was born");
        this.addBehaviour(new ReceivePing());
        if (this.getLocalName().equals("A1")){
            this.addBehaviour(new SendPingAndReceivePong(new AID("A2", false)));
        }
    }
}
