package app.truefleet.com.truefleet.Activitieis.Events;

import app.truefleet.com.truefleet.Models.LinehaulStatus;

/**
 * Created by Chris Lacy on 3/10/2015.
 */
public class ActiveOrderLinehauLStatusChangedEvent {
    private LinehaulStatus newStatus;
    public ActiveOrderLinehauLStatusChangedEvent(LinehaulStatus newStatus) {
        this.newStatus = newStatus;
    }

    public LinehaulStatus getNewStatus() {
        return newStatus;
    }
}
