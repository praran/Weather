package com.monitor;

/**
 * Created by pradeep on 24/01/2016.
 */
public interface Alerter {
    void send(String to, String msg);
}
