package app.truefleet.com.truefleet.Models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Chris Lacy on 2/5/2015.
 */
@Table(name = "Contact")
public class Contact extends Model {

    @Column
    public int accountid;

    @Column
    public String firstName;

    @Column
    public String lastName;

    @Column
    public String suffix;

    @Column
    public String mailingNtreet;

    @Column
    public String mailingCity;

    @Column
    public String mailingState;

    @Column
    public String mailingPostalCode;

    @Column
    public String mailingCountry;

    @Column
    public String phone;

    @Column
    public String mobilePhone;

    @Column
    public String fax;

    @Column
    public String notes;

    public Contact() { super();  }
}
