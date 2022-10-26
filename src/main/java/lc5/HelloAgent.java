package lc5;

import jade.core.Agent;

public class HelloAgent  extends Agent {

    @Override
    protected void setup() {
        this.addBehaviour(new PeriodicPrintingBehaviour(this, 1000));
        this.addBehaviour(new PeriodicPrintingBehaviour(this, 1000));
    }
}
