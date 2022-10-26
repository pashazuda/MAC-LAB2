package lc6;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class SendRequestsAndWaitResponses extends ParallelBehaviour {
    private long timeout;
    private List<AID> rec;
    final List<ACLMessage> answers;

    public SendRequestsAndWaitResponses(Agent a, long timeout, List<AID> receivers, List<ACLMessage> answers) {
        super(a, WHEN_ANY);
        this.timeout = timeout;
        this.rec = receivers;
        this.answers = answers;
    }

    @Override
    public void onStart() {
        WakerBehaviour waker = new WakerBehaviour(myAgent, this.timeout) {
            @Override
            protected void onWake() {
                log.info("Time out is over");
            }
        };

        Behaviour worker = new Behaviour() {
            final MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.PROPOSE);

            @Override
            public void onStart() {
                ACLMessage m = new ACLMessage(ACLMessage.REQUEST);
                rec.forEach(el -> m.addReceiver(el));
                m.setProtocol("Prices");
                myAgent.send(m);
            }

            @Override
            public void action() {
                ACLMessage ans = myAgent.receive(mt);
                if (ans != null){
                    answers.add(ans);
                    log.info("I received answer form {} with price {}",ans.getSender().getLocalName(), ans.getContent());
                } else {
                    block();
                }
            }

            @Override
            public boolean done() {
                return answers.size() == rec.size();
            }
        };

        addSubBehaviour(waker);
        addSubBehaviour(worker);
    }

    @Override
    public int onEnd() {
        log.info("Parallel beh onEnd = {}", answers.size() == 0 ? 1 : 2);
        return answers.size() == 0 ? 1 : 2;
    }
}
