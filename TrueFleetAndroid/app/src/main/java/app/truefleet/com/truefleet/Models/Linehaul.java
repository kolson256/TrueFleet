package app.truefleet.com.truefleet.Models;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by Chris Lacy on 2/4/2015.
 */
@Table(name = "Linehaul")
public class Linehaul extends BaseModel {

    @Column(name = "orderid", index = true)
    public int orderid;

    @Column(name ="serverid")
    public int id;
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

    @Column(name = "shipperid")
    public int shipperid;

    @Column(name = "terminalid")
    public int terminalid;

    @Column(name = "receiverid")
    public int receiverid;



    @Column(name = "shipdate")
    public long shipdate;

    @Column(name = "pickupStartDate")
    public long pickupStartDate;

    @Column(name = "pickupEndDate")
    public long pickupEndDate;

    @Column(name = "deliveryDeadline")
    public long deliveryDeadline;

    public Linehaul() { super(); }

    public Linehaul(int orderid, Order order, int routeId, Account shipper,
                    Account terminal, Account receiver, String notes,
                    long shipdate, long pickupStartDate, long pickupEndDate,
                    long deliveryDeadline) {
        super();

        this.orderid = orderid;
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

    public static List<Linehaul> getLinehauls(Order order) {
        return new Select()
                .from(Linehaul.class)
                .where("Orders = ?", order.getId())
             //   .orderBy("order ASC")
                .execute();
    }

    //TODO: Implement when status is in DB
    public static boolean isAactive(Linehaul linehaul) {
        return true;
    }
}
