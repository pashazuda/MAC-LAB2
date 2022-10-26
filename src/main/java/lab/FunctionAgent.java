package lab;

import jade.core.Agent;


public class FunctionAgent extends Agent {
    double x = Math.random();
    double delta = 1;
    @Override
    protected void setup() {

        if (getLocalName().equals("Agent1")){
            addBehaviour(new InitiatorTransfer(this,10000, x, delta));
        }
        addBehaviour((new AcceptRequestAndCalcFunction()));
        addBehaviour((new AcceptOfInitiation()));



    }
}