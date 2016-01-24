package com.monitor;

/**
 * Created by pradeep on 24/01/2016.
 */
public interface Probe {
    boolean passes();

    String getFailureDescription();
}
