package app.truefleet.com.truefleet.Models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

/**
 * Created by Chris Lacy on 2/5/2015.
 */

@Table(name = "Account")
public class Account extends Model {
    @Column(name = "name")
    public String name;

    @Column(name = "serverid", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    public int id;

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
    public String types;

    @Column
    public String notes;

    @Column
    public String phone;

    @Column
    public String fax;

    public Account() {
        super();
    }

    public Account(int id, String name, String mailingstreet, String mailingcity, String mailingstate,
                   String mailingpostalcode, String mailingcountry, String types,
                   String notes, String phone, String fax) {
        super();
        this.id = id;
        this.name = name;
        this.mailingstreet = mailingstreet;
        this.mailingcity = mailingcity;
        this.mailingstate = mailingstate;
        this.mailingpostalcode = mailingpostalcode;
        this.mailingcountry = mailingcountry;
        this.types = types;
        this.notes = notes;
        this.phone = phone;
        this.fax = fax;
    }

    public List<Contact> contacts() {

        return getMany(Contact.class, "Account");
    }

}
