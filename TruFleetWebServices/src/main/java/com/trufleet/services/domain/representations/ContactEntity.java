package com.trufleet.services.domain.representations;

import javax.persistence.*;

/**
 * Created by Richard Morgan on 2/9/2015.
 */
@Entity
@Table(name = "contact", schema = "public", catalog = "trufleet")
public class ContactEntity {
    private String firstname;
    private String lastname;
    private String suffix;
    private String mailingstreet;
    private String mailingcity;
    private String mailingstate;
    private String mailingpostalcode;
    private String mailingcountry;
    private String phone;
    private String mobilephone;
    private String fax;
    private String notes;
    private int id;

    @Basic
    @Column(name = "firstname", nullable = true, insertable = true, updatable = true, length = 2147483647)
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "lastname", nullable = true, insertable = true, updatable = true, length = 2147483647)
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "suffix", nullable = true, insertable = true, updatable = true, length = 2147483647)
    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
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
    @Column(name = "phone", nullable = true, insertable = true, updatable = true, length = 2147483647)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "mobilephone", nullable = true, insertable = true, updatable = true, length = 2147483647)
    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    @Basic
    @Column(name = "fax", nullable = true, insertable = true, updatable = true, length = 2147483647)
    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @Basic
    @Column(name = "notes", nullable = true, insertable = true, updatable = true, length = 2147483647)
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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

        ContactEntity that = (ContactEntity) o;

        if (id != that.id) return false;
        if (fax != null ? !fax.equals(that.fax) : that.fax != null) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
        if (mailingcity != null ? !mailingcity.equals(that.mailingcity) : that.mailingcity != null) return false;
        if (mailingcountry != null ? !mailingcountry.equals(that.mailingcountry) : that.mailingcountry != null)
            return false;
        if (mailingpostalcode != null ? !mailingpostalcode.equals(that.mailingpostalcode) : that.mailingpostalcode != null)
            return false;
        if (mailingstate != null ? !mailingstate.equals(that.mailingstate) : that.mailingstate != null) return false;
        if (mailingstreet != null ? !mailingstreet.equals(that.mailingstreet) : that.mailingstreet != null)
            return false;
        if (mobilephone != null ? !mobilephone.equals(that.mobilephone) : that.mobilephone != null) return false;
        if (notes != null ? !notes.equals(that.notes) : that.notes != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (suffix != null ? !suffix.equals(that.suffix) : that.suffix != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = firstname != null ? firstname.hashCode() : 0;
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (suffix != null ? suffix.hashCode() : 0);
        result = 31 * result + (mailingstreet != null ? mailingstreet.hashCode() : 0);
        result = 31 * result + (mailingcity != null ? mailingcity.hashCode() : 0);
        result = 31 * result + (mailingstate != null ? mailingstate.hashCode() : 0);
        result = 31 * result + (mailingpostalcode != null ? mailingpostalcode.hashCode() : 0);
        result = 31 * result + (mailingcountry != null ? mailingcountry.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (mobilephone != null ? mobilephone.hashCode() : 0);
        result = 31 * result + (fax != null ? fax.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }
}
