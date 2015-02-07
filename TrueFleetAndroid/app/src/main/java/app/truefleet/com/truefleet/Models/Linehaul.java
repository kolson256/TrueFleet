package app.truefleet.com.truefleet.Models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Chris Lacy on 2/4/2015.
 */
@Table(name = "Linehaul")
public class Linehaul extends Model {

    @Column(name = "order", index = true)
    public int orderId;

    @Column(name = "order", index = true)
    public int externalId;

    @Column(name = "order", index = true)
    public int routeId;

    @Column(name = "order", index = true)
    public int shipdate;

    @Column(name = "order", index = true)
    public int pickupStartDate;

    @Column(name = "order", index = true)
    public int pickupEndDate;

    @Column(name = "order", index = true)
    public int deliveryDeadline;

    public Linehaul() { super(); }


}
