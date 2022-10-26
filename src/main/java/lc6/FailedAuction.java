package lc6;

import jade.core.behaviours.OneShotBehaviour;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FailedAuction extends OneShotBehaviour {
    @Override
    public void action() {
        log.warn("No answer was received");
    }
}
