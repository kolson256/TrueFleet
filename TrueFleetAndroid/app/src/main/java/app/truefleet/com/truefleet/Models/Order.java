package app.truefleet.com.truefleet.Models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.sql.Date;
import java.util.List;


/**
 * Created by night_000 on 10/28/2014.
 */
@Table(name = "Orders")
public class Order extends Model {

    @Column(name = "Account",
            onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
    public  Account account;

    @Column(name = "Contact",
            onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
    public  Contact contact;

    @Column
    public  int orderid;

    @Column
    public String externalId;

    @Column
    public String notes;

    @Column
    public Date receiptDate;

    @Column
    public  String orderType;

    @Column public String assignedUser;


    public Order() { super(); }

    public Order(Account account, Contact contact, int orderid,
                  String externalId, String notes, Date receiptDate, String orderType,
                  String assignedUser) {
        super();

        this.account = account;
        this.contact = contact;
        this.orderid = orderid;
        this.externalId = externalId;
        this.notes = notes;
        this.receiptDate = receiptDate;
        this.orderType = orderType;
        this.assignedUser = assignedUser;
    }
    public static List<Order> getOrders(String user) {
        return new Select()
                .from(Order.class)
                .where("assignedUser = ?", user)
                        //   .orderBy("order ASC")
                .execute();
    }

}
