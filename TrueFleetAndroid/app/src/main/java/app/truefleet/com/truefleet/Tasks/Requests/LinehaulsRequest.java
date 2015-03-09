package app.truefleet.com.truefleet.Tasks.Requests;

/**
 * Created by Chris Lacy on 3/9/2015.
 */
public class LinehaulsRequest {
    final private int routeid;
    final private int orderid;

    public LinehaulsRequest(int routeid, int orderid) {
        this.routeid = routeid;
        this.orderid = orderid;
    }
}
