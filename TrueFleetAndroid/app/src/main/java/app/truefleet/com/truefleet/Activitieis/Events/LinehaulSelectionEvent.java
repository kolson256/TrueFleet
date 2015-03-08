package app.truefleet.com.truefleet.Activitieis.Events;

import app.truefleet.com.truefleet.Models.LinehaulType;

/**
 * Created by Chris Lacy on 3/8/2015.
 */
public class LinehaulSelectionEvent {
    private final LinehaulType linehaulSelection;

    public LinehaulSelectionEvent(LinehaulType selection) {
        this.linehaulSelection = selection;
    }

    public LinehaulType getSelectionType() {
        return linehaulSelection;
    }
}
