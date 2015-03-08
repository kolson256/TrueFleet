package app.truefleet.com.truefleet.Activitieis.Events;

/**
 * Created by Chris Lacy on 3/7/2015.
 */
public class OrderSearchEvent {

    private String query;

    public OrderSearchEvent(String query) {
        this.query = query;
    }

    public String getSearch() {
        return query;
    }
}
