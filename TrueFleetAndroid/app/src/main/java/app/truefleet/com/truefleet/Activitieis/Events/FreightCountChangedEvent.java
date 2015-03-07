package app.truefleet.com.truefleet.Activitieis.Events;

/**
 * Created by Chris Lacy on 3/6/2015.
 */
public class FreightCountChangedEvent {

    private int count;

    public FreightCountChangedEvent(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}
