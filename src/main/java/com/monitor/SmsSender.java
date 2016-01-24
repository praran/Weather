package com.monitor;

/**
 * Created by pradeep on 23/01/2016.
 */
public class SmsSender implements Alerter {
    public void send(String to, String failureDescription) {
        System.out.println("SMS : " + to + " : " + failureDescription);
    }
}
