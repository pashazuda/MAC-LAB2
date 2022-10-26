package lc7.agents;

import jade.core.Agent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AutorunnableAgent(name = "John", copy = 10)
public class AutoAgent extends Agent {
    @Override
    protected void setup() {
        log.info("Hello, I'm {}", this.getLocalName());
    }
}
