package pr4.df;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;

public class SellerAgent extends Agent {

    @Override
    protected void setup() {
        DfHelper.registerAgent(this.getAID(), this, AgentType.Seller.toString());
    }
}
