package app.truefleet.com.truefleet.Models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.sql.Date;

/**
 * Created by night_000 on 10/28/2014.
 */
@Table(name = "Order")
public class Order extends Model {

    @Column
    public  Account account;

    @Column
    public  Contact contact;

    @Column
    public  String orderid;

    @Column
    public String externalId;

    @Column
    public String notes;

    @Column
    public Date receiptDate;

    @Column
    public  String orderType;


    public Order() { super(); }


}
