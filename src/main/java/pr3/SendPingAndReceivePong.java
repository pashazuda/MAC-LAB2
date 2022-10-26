package pr3;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SendPingAndReceivePong extends Behaviour {
    private AID receiver;
    public SendPingAndReceivePong(AID receiver) {
        this.receiver = receiver;
    }

    private MessageTemplate mt = MessageTemplate.and(
                MessageTemplate.MatchPerformative(ACLMessage.PROPOSE),
                MessageTemplate.MatchProtocol("Pong"));
    private boolean finish = false;
    @Override
    public void onStart() {
        myAgent.addBehaviour(new SendDelayedPing(myAgent, 3000, receiver));
    }

    @Override
    public void action() {
        ACLMessage pong = myAgent.receive(mt);
        if(pong != null){
            log.info("i received msg pong from {} with content {}", pong.getSender().getLocalName(), pong.getContent());
            finish =true;
        } else {
            block();
        }
    }

    @Override
    public boolean done() {
        return finish;
    }
}
