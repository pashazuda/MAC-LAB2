package lc5;

import jade.core.Agent;

public class ReceiverAgent extends Agent {
    @Override
    protected void setup() {
        addBehaviour(new HelloReceivingBehaviour(this));
        addBehaviour(new WakerTest(this, 15_000));
    }
}
