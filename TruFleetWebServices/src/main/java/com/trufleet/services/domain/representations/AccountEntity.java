package com.trufleet.services.domain.representations;

import javax.persistence.*;

/**
 * Created by Richard Morgan on 2/9/2015.
 */
@Entity
@Table(name = "account", schema = "public", catalog = "trufleet")
public class AccountEntity {
    private String name;
    private String mailingstreet;
    private String mailingcity;
    private String mailingstate;
    private String mailingpostalcode;
    private String mailingcountry;
    private String types;
    private String notes;
    private String phone;
    private String fax;
    private int id;

    public AccountEntity() {
    }

    @Basic
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 2147483647)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "mailingstreet", nullable = true, insertable = true, updatable = true, length = 2147483647)
    public String getMailingstreet() {
        return mailingstreet;
    }

    public void setMailingstreet(String mailingstreet) {
        this.mailingstreet = mailingstreet;
    }

    @Basic
    @Column(name = "mailingcity", nullable = true, insertable = true, updatable = true, length = 2147483647)
    public String getMailingcity() {
        return mailingcity;
    }

    public void setMailingcity(String mailingcity) {
        this.mailingcity = mailingcity;
    }

    @Basic
    @Column(name = "mailingstate", nullable = true, insertable = true, updatable = true, length = 2147483647)
    public String getMailingstate() {
        return mailingstate;
    }

    public void setMailingstate(String mailingstate) {
        this.mailingstate = mailingstate;
    }

    @Basic
    @Column(name = "mailingpostalcode", nullable = true, insertable = true, updatable = true, length = 2147483647)
    public String getMailingpostalcode() {
        return mailingpostalcode;
    }

    public void setMailingpostalcode(String mailingpostalcode) {
        this.mailingpostalcode = mailingpostalcode;
    }

    @Basic
    @Column(name = "mailingcountry", nullable = true, insertable = true, updatable = true, length = 2147483647)
    public String getMailingcountry() {
        return mailingcountry;
    }

    public void setMailingcountry(String mailingcountry) {
        this.mailingcountry = mailingcountry;
    }

    @Basic
    @Column(name = "types", nullable = true, insertable = true, updatable = true, length = 2147483647)
    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    @Basic
    @Column(name = "notes", nullable = true, insertable = true, updatable = true, length = 2147483647)
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Basic
    @Column(name = "phone", nullable = true, insertable = true, updatable = true, length = 2147483647)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "fax", nullable = true, insertable = true, updatable = true, length = 2147483647)
    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountEntity that = (AccountEntity) o;

        if (id != that.id) return false;
        if (fax != null ? !fax.equals(that.fax) : that.fax != null) return false;
        if (mailingcity != null ? !mailingcity.equals(that.mailingcity) : that.mailingcity != null) return false;
        if (mailingcountry != null ? !mailingcountry.equals(that.mailingcountry) : that.mailingcountry != null)
            return false;
        if (mailingpostalcode != null ? !mailingpostalcode.equals(that.mailingpostalcode) : that.mailingpostalcode != null)
            return false;
        if (mailingstate != null ? !mailingstate.equals(that.mailingstate) : that.mailingstate != null) return false;
        if (mailingstreet != null ? !mailingstreet.equals(that.mailingstreet) : that.mailingstreet != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (notes != null ? !notes.equals(that.notes) : that.notes != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (types != null ? !types.equals(that.types) : that.types != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (mailingstreet != null ? mailingstreet.hashCode() : 0);
        result = 31 * result + (mailingcity != null ? mailingcity.hashCode() : 0);
        result = 31 * result + (mailingstate != null ? mailingstate.hashCode() : 0);
        result = 31 * result + (mailingpostalcode != null ? mailingpostalcode.hashCode() : 0);
        result = 31 * result + (mailingcountry != null ? mailingcountry.hashCode() : 0);
        result = 31 * result + (types != null ? types.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (fax != null ? fax.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }
}
