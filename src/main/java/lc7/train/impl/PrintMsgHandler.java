package lc7.train.impl;

import lc7.train.MsgHandler;
import lc7.train.MyHandler;

@MyHandler(name = "Print")
public class PrintMsgHandler implements MsgHandler {
    private String n = "hello";
    @Override
    public void handle(String msg) {
        System.err.println(msg);
    }
}
