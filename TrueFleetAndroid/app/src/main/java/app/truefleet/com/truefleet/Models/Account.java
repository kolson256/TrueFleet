package app.truefleet.com.truefleet.Models;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Chris Lacy on 2/5/2015.
 */
@Table(name = "Account")
public class Account {
    @Column(name = "name")
    public String name;

    @Column
    public String mailingstreet;

    @Column
    public String mailingcity;

    @Column
    public String mailingstate;

    @Column
    public String mailingpostalcode;

    @Column
    public String mailingCountry;

    @Column
    public String types;

    @Column
    public String notes;

    @Column
    public String phone;

    @Column
    public String fax;

    public Account() { super(); }
}
