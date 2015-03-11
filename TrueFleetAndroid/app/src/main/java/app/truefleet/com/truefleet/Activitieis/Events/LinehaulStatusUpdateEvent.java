package app.truefleet.com.truefleet.Activitieis.Events;

import app.truefleet.com.truefleet.Models.Linehaul;

/**
 * Created by Chris Lacy on 3/10/2015.
 */
public class LinehaulStatusUpdateEvent {
    Linehaul linehaul;
    public LinehaulStatusUpdateEvent(Linehaul linehaul) {
        this.linehaul = linehaul;
    }

    public Linehaul getLinehaul() {
        return linehaul;
    }
}
