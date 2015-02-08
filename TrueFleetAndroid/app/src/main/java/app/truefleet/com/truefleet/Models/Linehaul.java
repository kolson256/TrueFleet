package app.truefleet.com.truefleet.Models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.sql.Date;

/**
 * Created by Chris Lacy on 2/4/2015.
 */
@Table(name = "Linehaul")
public class Linehaul extends Model {

    @Column(name = "orderid", index = true)
    public int orderId;

    @Column(name = "Orders", index = true)
    public Order order;


    @Column(name = "routeId", index = true)
    public int routeId;

    @Column
    public Account shipper;

    @Column
    public Account terminal;

    @Column
    public Account receiver;

    @Column(name = "shipdate")
    public Date shipdate;

    @Column(name = "pickupStartDate")
    public Date pickupStartDate;

    @Column(name = "pickupEndDate")
    public Date pickupEndDate;

    @Column(name = "deliveryDeadline")
    public Date deliveryDeadline;

    public Linehaul() { super(); }

    public Linehaul(int orderId, Order order, int routeId, Account shipper,
                    Account terminal, Account receiver, Date shipdate,
                    Date pickupStartDate, Date pickupEndDate, Date deliveryDeadline) {
        super();

        this.orderId = orderId;
        this.order = order;
        this.routeId = routeId;
        this.shipper = shipper;
        this.terminal = terminal;
        this.receiver = receiver;
        this.shipdate = shipdate;
        this.pickupStartDate = pickupStartDate;
        this.pickupEndDate = pickupEndDate;
        this.deliveryDeadline = deliveryDeadline;
    }

}
