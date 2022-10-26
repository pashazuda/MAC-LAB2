package pr4.df;

import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DfHelper {
    public static List<AID> findAgents(String type, Agent a){
        DFAgentDescription dfd = new DFAgentDescription();
        ServiceDescription sd = new ServiceDescription();
        sd.setType(type);
        dfd.addServices(sd);
        try{
            DFAgentDescription[] search = DFService.search(a, dfd);
            return Arrays.stream(search).map(el -> el.getName()).collect(Collectors.toList());
        } catch (FIPAException e){
            e.printStackTrace();
        }
        return null;
    }

    public static void registerAgent(AID aid, Agent a, String type){
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(aid);
        ServiceDescription sd = new ServiceDescription();
        sd.setType(type);
        sd.setName(aid.getLocalName());
        dfd.addServices(sd);
        try{
            DFService.register(a, dfd);
        } catch (FIPAException e){
            e.printStackTrace();
        }
    }
}
