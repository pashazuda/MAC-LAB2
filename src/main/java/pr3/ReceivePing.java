package pr3;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReceivePing extends Behaviour {
    private MessageTemplate mt = MessageTemplate.and(
            MessageTemplate.MatchPerformative(ACLMessage.CFP),
            MessageTemplate.MatchProtocol("Ping"));

    @Override
    public void action() {
        ACLMessage ping = myAgent.receive(mt);
        if (ping!= null){
            log.info("I received ping from {}", ping.getSender().getLocalName());
            ACLMessage pong = ping.createReply();
            pong.setPerformative(ACLMessage.PROPOSE);
            pong.setProtocol("Pong");
            pong.setContent("Pong");
            myAgent.send(pong);
            myAgent.addBehaviour(new SendPingAndReceivePong(ping.getSender()));
        } else {
            block();
        }
    }

    @Override
    public boolean done() {
        return false;
    }
}
