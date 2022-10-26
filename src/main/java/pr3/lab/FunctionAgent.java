package pr3.lab;

import jade.core.Agent;


public class FunctionAgent extends Agent {
    double x = Math.random();
    double delta = 0.5;
    @Override
    protected void setup() {

        if (getLocalName().equals("Agent1")){
            addBehaviour(new TransferOfInitiation(this,15000, x, delta));
        }
        addBehaviour((new AcceptRequestAndCalcFunction()));
        addBehaviour((new AcceptOfInitiation()));



    }
}