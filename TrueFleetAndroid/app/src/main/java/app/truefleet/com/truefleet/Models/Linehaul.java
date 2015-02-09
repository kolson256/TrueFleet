package app.truefleet.com.truefleet.Models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.sql.Date;
import java.util.List;

/**
 * Created by Chris Lacy on 2/4/2015.
 */
@Table(name = "Linehaul")
public class Linehaul extends Model {

    @Column(name = "orderid", index = true)
    public int orderId;

    @Column(name = "Orders",
            onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
    public Order order;


    @Column(name = "routeId", index = true)
    public int routeId;

    @Column(name = "Shipper",
            onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
    public Account shipper;

    @Column(name = "Terminal",
            onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
    public Account terminal;

    @Column(name = "Receiver",
            onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
    public Account receiver;

    @Column(name = "notes")
    public String notes;

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
                    Account terminal, Account receiver, String notes,
                    Date shipdate, Date pickupStartDate, Date pickupEndDate,
                    Date deliveryDeadline) {
        super();

        this.orderId = orderId;
        this.order = order;
        this.routeId = routeId;
        this.shipper = shipper;
        this.terminal = terminal;
        this.receiver = receiver;
        this.notes = notes;
        this.shipdate = shipdate;
        this.pickupStartDate = pickupStartDate;
        this.pickupEndDate = pickupEndDate;
        this.deliveryDeadline = deliveryDeadline;
    }

    public List<Freight> freights() {
        return getMany(Freight.class, "Linehaul");
    }
}
