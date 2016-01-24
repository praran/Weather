package com.monitor;

/**
 * Created by pradeep on 23/01/2016.
 */
public class Emailer implements Alerter {


    private static final Alerter INSTANCE = new Emailer();

    public static Alerter getInstance() {
        return INSTANCE;
    }

    @Override
    public void send(String to, String msg) {
        System.out.println("Email: " + to + " : "+msg);
    }
}
