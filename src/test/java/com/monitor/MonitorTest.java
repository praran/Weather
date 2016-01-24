package com.monitor;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import java.time.LocalTime;

/**
 * Created by pradeep on 24/01/2016.
 */
public class MonitorTest  {


    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    private Alerter emailer = context.mock(Alerter.class, "emailer");

    private Alerter smsSender = context.mock(Alerter.class, "smsSender");
    private ControllableClock controllableClock;

    @Test
    public void shouldSendEmailWhenProbeFails(){

        context.checking(new Expectations(){{
            exactly(1).of(emailer).send("support@example.com","always fails");
        }});

        Monitor monitor = new Monitor( emailer, smsSender, new SystemClock(),new FailingProbe());
        monitor.check();
    }

    @Test
    public void sendsEmailAndSMSWhenDuringBusinessHours(){
        controllableClock = new ControllableClock();
        controllableClock.currentTime(15,00);
        context.checking(new Expectations(){{
            exactly(1).of(emailer).send("support@example.com","always fails");
            exactly(1).of(smsSender).send("+12345","always fails");
        }});
        Monitor monitor = new Monitor( emailer, smsSender, controllableClock, new FailingProbe());
        monitor.check();

    }

    private class FailingProbe implements Probe {
        @Override
        public boolean passes() {
            return false;
        }

        @Override
        public String getFailureDescription() {
            return "always fails";
        }
    }

    private class ControllableClock implements Clock {

        private LocalTime localTime;

        @Override
        public LocalTime now() {
            return localTime;
        }

        public void currentTime(int hr, int min) {
            localTime = LocalTime.of(hr, min);
        }
    }
}