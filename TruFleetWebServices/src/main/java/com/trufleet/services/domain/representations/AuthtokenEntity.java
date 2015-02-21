package com.trufleet.services.domain.representations;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Richard Morgan on 2/9/2015.
 */
@Entity
@Table(name = "authtoken", schema = "public", catalog = "trufleet")
public class AuthtokenEntity {
    private int appuserid;
    private String token;
    private Timestamp expirationdate;

    public AuthtokenEntity() {
    }

    @Basic
    @Column(name = "appuserid", nullable = false, insertable = true, updatable = true)
    public int getAppuserid() {
        return appuserid;
    }

    public void setAppuserid(int appuserid) {
        this.appuserid = appuserid;
    }

    @Id
    @Column(name = "token", nullable = false, insertable = true, updatable = true, length = 2147483647)
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Basic
    @Column(name = "expirationdate", nullable = false, insertable = true, updatable = true)
    public Timestamp getExpirationdate() {
        return expirationdate;
    }

    public void setExpirationdate(Timestamp expirationdate) {
        this.expirationdate = expirationdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthtokenEntity that = (AuthtokenEntity) o;

        if (appuserid != that.appuserid) return false;
        if (expirationdate != null ? !expirationdate.equals(that.expirationdate) : that.expirationdate != null)
            return false;
        if (token != null ? !token.equals(that.token) : that.token != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = appuserid;
        result = 31 * result + (token != null ? token.hashCode() : 0);
        result = 31 * result + (expirationdate != null ? expirationdate.hashCode() : 0);
        return result;
    }
}
