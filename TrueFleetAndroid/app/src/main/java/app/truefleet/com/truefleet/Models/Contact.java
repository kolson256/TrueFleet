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
    public String firstname;

    @Column
    public String lastname;

    @Column
    public String suffix;

    @Column
    public String mailingstreet;

    @Column
    public String mailingcity;

    @Column
    public String mailingstate;

    @Column
    public String mailingpostalcode;

    @Column
    public String mailingcountry;

    @Column
    public String phone;

    @Column
    public String mobilephone;

    @Column
    public String fax;

    @Column
    public String notes;

    @Column(name="Account",
            onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
    public Account account;

    public Contact() { super();  }

    public Contact(int accountid, String firstName, String lastName, String suffix,
                   String mailingStreet, String mailingState, String mailingCity, String mailingPostalCode,
                   String mailingCountry, String phone,
                   String mobilePhone, String fax, String notes, Account account) {
        super();
        this.accountid = accountid;
        this.firstname = firstName;
        this.lastname = lastName;
        this.suffix = suffix;
        this.mailingstreet = mailingStreet;
        this.mailingstate = mailingState;
        this.mailingcity = mailingCity;
        this.mailingpostalcode = mailingPostalCode;
        this.mailingcountry = mailingCountry;
        this.phone = phone;
        this.mobilephone = mobilePhone;
        this.fax = fax;
        this.notes = notes;
        this.account = account;
    }

}
