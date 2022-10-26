package lc6;

import jade.core.AID;
import jade.core.Agent;
import lombok.SneakyThrows;

import java.util.List;

public class TestAgent  extends Agent {
    @SneakyThrows
    @Override
    protected void setup() {
        List<AID> rec = List.of(new AID("R1", false), new AID("R2", false));
        addBehaviour(new MyFirstFSM(rec));
        String name = this.getName();
        String[] addressesArray = this.getAID().getAddressesArray();
        System.out.println(name);


    }
}
