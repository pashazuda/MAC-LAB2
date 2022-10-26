package lc6;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
@Slf4j
public class ChooseWinner extends OneShotBehaviour {
    private List<ACLMessage> answers;

    public ChooseWinner(List<ACLMessage> answers) {
        this.answers = answers;
    }

    @Override
    public void action() {
        double price = 0;
        AID bestSeller = null;
        for (ACLMessage answer : answers) {
            String content = answer.getContent();
            double val = Double.parseDouble(content);
            if (val > 0){
                if (val < price || bestSeller == null){
                    price = val;
                    bestSeller = answer.getSender();
                }
            }
        }
        if (bestSeller != null){
            ACLMessage winnerInfo = new ACLMessage(ACLMessage.INFORM);
            winnerInfo.addReceiver(bestSeller);
            winnerInfo.setProtocol("Win");
            myAgent.send(winnerInfo);
            log.info("Winner is {} with price {}", bestSeller.getLocalName(), price);
        } else {
            log.warn("no appropriate price was received");
        }
    }
}
