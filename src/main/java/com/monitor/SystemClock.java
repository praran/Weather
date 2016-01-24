package com.monitor;

import java.time.LocalTime;

/**
 * Created by pradeep on 24/01/2016.
 */
public class SystemClock implements Clock {
    @Override
    public LocalTime now() {
        return LocalTime.now();
    }
}
