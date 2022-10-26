package pr4.df;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BuyerAgent extends Agent {
    @Override
    protected void setup() {
        DfHelper.registerAgent(this.getAID(), this, AgentType.Buyer.toString());
        addBehaviour(new WakerBehaviour(this, 1000) {
            @Override
            protected void onWake() {
                List<AID> agents = DfHelper.findAgents(AgentType.Seller.toString(), myAgent);
                System.out.println(agents.stream().map(el ->el.getLocalName()).collect(Collectors.toList()));
            }
        });
    }
}
