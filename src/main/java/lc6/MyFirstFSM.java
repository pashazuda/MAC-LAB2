package lc6;

import jade.core.AID;
import jade.core.behaviours.DataStore;
import jade.core.behaviours.FSMBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.ArrayList;
import java.util.List;

public class MyFirstFSM extends FSMBehaviour {
    private final static String RECEIVE ="receive", ASSESS="assess", FAILED = "failed";
    private List<AID> receivers;

    public MyFirstFSM(List<AID> receivers) {
        this.receivers = receivers;
    }

    @Override
    public void onStart() {
        /*
            1. - parallel brhavoiur - send request, receive reponses
            2.1 - responses > 0
               4. Success
            2.2 responses = 0
                3.Failed
         */

        List<ACLMessage> ans = new ArrayList<>();
        registerFirstState(new SendRequestsAndWaitResponses(myAgent, 1000, receivers, ans), RECEIVE);

        registerLastState(new ChooseWinner(ans), ASSESS);
        registerLastState(new FailedAuction(), FAILED);

        registerTransition(RECEIVE, FAILED, 1);
        registerTransition(RECEIVE, ASSESS, 2);

    }


}
