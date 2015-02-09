package app.truefleet.com.truefleet.Models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.sql.Date;


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


    public Order() { super(); }

    public Order(Account account, Contact contact, int orderid,
                  String externalId, String notes, Date receiptDate, String orderType) {
        super();

        this.account = account;
        this.contact = contact;
        this.orderid = orderid;
        this.externalId = externalId;
        this.notes = notes;
        this.receiptDate = receiptDate;
        this.orderType = orderType;
    }

}
