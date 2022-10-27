package lab;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class SlaveBehaviour extends Behaviour {
    double x;
    double delta;
    private MessageTemplate mt = MessageTemplate.and(
            MessageTemplate.MatchPerformative(ACLMessage.REQUEST),
            MessageTemplate.MatchProtocol("CalcFunction"));
    private Random r = new Random();
    boolean finish;



    @Override
    public void action() {
        ACLMessage initiator = myAgent.receive(mt);
        if(initiator!=null) {
            List<String> params = List.of(initiator.getContent().split(", "));
            x = Double.parseDouble(params.get(0));
            delta = Double.parseDouble(params.get(1));
            List<Double> points = List.of(x - delta, x, x + delta);
            List<Double> funcResult = new ArrayList<>();
            for (Double x : points) {
                switch (myAgent.getLocalName()) {
                    case "Agent1":
                        funcResult.add(CalculateFunction.funAgent1(x));
                        break;
                    case "Agent2":
                        funcResult.add(CalculateFunction.funAgent2(x));
                        break;
                    case "Agent3":
                        funcResult.add(CalculateFunction.funAgent3(x));
                        break;
                }
            }
            ACLMessage response = new ACLMessage(ACLMessage.REQUEST_WHEN);
            response.setProtocol("ResponseFromSlave");
            response.setContent(funcResult.get(0) + ", " + funcResult.get(1) + ", " + funcResult.get(2));
            response.addReceiver(new AID(initiator.getSender().getLocalName(), false));
            myAgent.send(response);
        }
        else {
            block();
        }
    }

    @Override
    public boolean done() {
        return finish;
    }
}