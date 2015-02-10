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
    public String mailingStreet;

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
        this.firstName = firstName;
        this.lastName = lastName;
        this.suffix = suffix;
        this.mailingStreet = mailingStreet;
        this.mailingState = mailingState;
        this.mailingCity = mailingCity;
        this.mailingPostalCode = mailingPostalCode;
        this.mailingCountry = mailingCountry;
        this.phone = phone;
        this.mobilePhone = mobilePhone;
        this.fax = fax;
        this.notes = notes;
        this.account = account;
    }

}
