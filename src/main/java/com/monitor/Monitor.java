package com.monitor;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by pradeep on 23/01/2016.
 */
public class Monitor {

    private List<Probe> probes;
    private Alerter emailer ;
    private Alerter smsSender;
    private final Clock clock;

    public Monitor() {
        this(Emailer.getInstance(), new SmsSender(), new SystemClock(),
                new WebPageProbe("http://www.google.com/", "Google"),
                new WebPageProbe("http://www.imperial.ac.uk/", "Imperial College"));
    }

    public Monitor(Alerter emailer, Alerter smsSender, Clock clock, Probe... probes) {
        this.smsSender = smsSender;
        this.probes = new ArrayList<>();
        this.probes.addAll(Arrays.asList(probes));
        this.emailer = emailer;
        this.clock = clock;
    }




    public void check(){
        for(Probe probe : probes){
           if(! probe.passes()){
               emailer.send("support@example.com", probe.getFailureDescription());

               LocalTime now = clock.now();
               if(now.getHour() >= BusinessHour.START_OF_BUSINESS && now.getHour() <= BusinessHour.CLOSE_OF_BUSINESS)
                   smsSender.send("+12345",probe.getFailureDescription());
           }
        }
    }



    public static void main(String[] args) {
        new Monitor().check();
        System.out.println("Completed");
    }
}
