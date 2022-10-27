package lab;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


@Slf4j
public class InitiatorBehaviour extends Behaviour {
    private final List<String> agents = Arrays.asList("Agent1", "Agent2", "Agent3");
    List<AID> receivers = new ArrayList<>();
    boolean finish;
    double x;
    double delta;
    private List<Double> funcResultA1 = new ArrayList<>();
    private List<Double>  funcResultA2 = new ArrayList<>();
    private List<Double>  funcResultA3 = new ArrayList<>();
    private MessageTemplate mt = MessageTemplate.or(
            MessageTemplate.MatchProtocol("ResponseFromSlave"),
            MessageTemplate.MatchProtocol("Initiator"));
    private Random r = new Random();

    @Override
    public void action() {
        ACLMessage msg = myAgent.receive(mt);
        if (msg != null){
            if (msg.getProtocol().equals("Initiator")){
                List<String> params = new ArrayList<>(List.of(msg.getContent().split(", ")));
                x = Double.parseDouble(params .get(0));
                delta = Double.parseDouble(params .get(1));
                List<Double> points = List.of(x - delta, x, x + delta);
                log.info("Я "+myAgent.getLocalName() + " являюсь иницатором");
                for (Double x : points) {
                    switch (myAgent.getLocalName()) {
                        case "Agent1":
                            funcResultA1.add(CalculateFunction.funAgent1(x));
                            break;
                        case "Agent2":
                            funcResultA2.add(CalculateFunction.funAgent2(x));
                            break;
                        case "Agent3":
                            funcResultA3.add(CalculateFunction.funAgent3(x));
                            break;
                    }
                }
                for (String agent : agents) {
                    if (!myAgent.getLocalName().equals(agent)) {
                        receivers.add(new AID(agent, false));
                    }
                }
                myAgent.addBehaviour(new SendParamsBehaviour(myAgent,1000, receivers,x,delta));
            }

            if (msg.getProtocol().equals("ResponseFromSlave")) {
                switch (msg.getSender().getLocalName()) {
                    case "Agent1":
                        for (String Fx1 : msg.getContent().split(", ")) {
                            funcResultA1.add(Double.parseDouble(Fx1));
                        }
                        break;
                    case "Agent2":
                        for (String Fx2 : msg.getContent().split(", ")) {
                            funcResultA2.add(Double.parseDouble(Fx2));
                        }
                        break;
                    case "Agent3":
                        for (String Fx3 : msg.getContent().split(", ")) {
                            funcResultA3.add(Double.parseDouble(Fx3));
                        }
                        break;
                }

            }

            if (funcResultA1.size() == 3 && funcResultA2.size() == 3 && funcResultA3.size() == 3) {
                ArrayList<Double> func = new ArrayList<>();// Формирование суммарного массива;
                for (int i = 0; i < 3; i++) {  // суммирует отдельно значения функций от { x - step, x, x + step}
                    func.add(funcResultA1.get(i) + funcResultA2.get(i) + funcResultA3.get(i));
                }
                funcResultA1.clear();
                funcResultA2.clear();
                funcResultA3.clear();
                receivers.clear();

                // Если достаточная точность расчетов достигнута, то завершаем поведение
                if (delta < 0.01) {
                    finish(func.get(1));
                } else {
                    double minRes = func.get(0);
                    int minId = 0;
                    for (int i = 0; i < func.size(); i++) {
                        if (func.get(i) < minRes) {
                            minRes = func.get(i);
                            minId = i;
                        }
                    }
                    switch (minId) {
                        case 0:
                            x = x - delta;
                            log.info("Промежуточное значение функции: " + String.format("%.3f", func.get(minId)));
                            log.info("Новое значение х=" + String.format("%.3f", x));
                            log.info("Значение delta=" + String.format("%.3f", delta));
                            log.info("--------------------------------------------------------");
                            if (delta < 0.01) {
                                finish(func.get(minId));
                            } else {
                                myAgent.addBehaviour(new InitiatorTransferBehaviour(myAgent,1000, x, delta));
                            }
                            break;
                        case 1:
                            delta = delta / 2;
                            log.info("Промежуточное значение функции: " + String.format("%.3f", func.get(minId)));
                            log.info("Значение х=" + String.format("%.3f", x));
                            log.info("Новое значение delta=" + String.format("%.3f", delta));
                            log.info("--------------------------------------------------------");
                            if (delta < 0.01) {
                                finish(func.get(minId));
                            } else {
                                myAgent.addBehaviour(new InitiatorTransferBehaviour(myAgent,1000, x, delta));
                            }
                            break;
                        case 2:
                            x = x + delta;
                            log.info("Промежуточное значение функции: " + String.format("%.3f", func.get(minId)));
                            log.info("Новое значение х=" + String.format("%.3f", x));
                            log.info("Значение delta=" + String.format("%.3f", delta));
                            log.info("--------------------------------------------------------");
                            if (delta < 0.01) {
                                finish(func.get(minId));
                            } else {
                                myAgent.addBehaviour(new InitiatorTransferBehaviour(myAgent,1000, x, delta));
                            }
                            break;
                    }
                }
            }

        }
        else{
            block();
        }
    }
    @Override
    public boolean done() {
        return finish;
    }

    public void finish(Double y) {
        log.info("+++++++++++++++++++++++++++++++++++++++++++++++++");
        log.info("Достаточная точность расчетов достигнута");
        log.info("Минимальное значение функции: " + String.format("%.3f", y));
        log.info("Значение х=" + String.format("%.3f", x));
        log.info("Значение delta=" + String.format("%.3f", delta));
        finish = true;
    }
}