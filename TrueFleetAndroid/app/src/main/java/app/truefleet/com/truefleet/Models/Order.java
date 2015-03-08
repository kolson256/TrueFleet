package app.truefleet.com.truefleet.Models;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;


/**
 * Created by night_000 on 10/28/2014.
 */
@Table(name = "Orders")
public class Order extends BaseModel {

    @Column(name = "Account",
            onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
    public  Account account;

    @Column(name = "Contact",
            onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
    public  Contact contact;

    @Column(name = "serverid", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    public int id;

    @Column
    public  int orderid;

    @Column
    public String externalid;

    @Column
    public String notes;

    @Column
    public long receiptdate;

    @Column
    public  String ordertype;

    @Column public String assignedUser;


    public Order() { super(); }

    public Order(int id, Account account, Contact contact, int orderid,
                  String externalid, String notes, long receiptdate, String ordertype,
                  String assignedUser) {
        super();
        this.id = id;
        this.account = account;
        this.contact = contact;
        this.orderid = orderid;
        this.externalid = externalid;
        this.notes = notes;
        this.receiptdate = receiptdate;
        this.ordertype = ordertype;
        this.assignedUser = assignedUser;
    }
    public static List<Order> getOrders(String user) {
        return new Select()
                .from(Order.class)
                .where("assignedUser = ?", user)
                        //   .orderBy("order ASC")
                .execute();
    }

    public static List<Order> getOrderByOrderId(int  orderid) {
        return new Select()
                .from(Order.class)
                .where("orderid = ?", orderid)
                        //   .orderBy("order ASC")
                .execute();
    }

}
