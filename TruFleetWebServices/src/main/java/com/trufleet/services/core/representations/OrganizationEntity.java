package com.trufleet.services.core.representations;

import javax.persistence.*;

/**
 * Created by Richard on 2/6/2015.
 */
@Entity
@Table(name = "organization", schema = "public", catalog = "TruFleetAdmin")
public class OrganizationEntity {
    private String name;
    private String tenantid;
    private String databaseurl;
    private String apiversion;

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Id
    @Column(name = "tenantid")
    public String getTenantid() {
        return tenantid;
    }

    public void setTenantid(String tenantid) {
        this.tenantid = tenantid;
    }

    @Basic
    @Column(name = "databaseurl")
    public String getDatabaseurl() {
        return databaseurl;
    }

    public void setDatabaseurl(String databaseurl) {
        this.databaseurl = databaseurl;
    }

    @Basic
    @Column(name = "apiversion")
    public String getApiversion() {
        return apiversion;
    }

    public void setApiversion(String apiversion) {
        this.apiversion = apiversion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganizationEntity that = (OrganizationEntity) o;

        if (apiversion != null ? !apiversion.equals(that.apiversion) : that.apiversion != null) return false;
        if (databaseurl != null ? !databaseurl.equals(that.databaseurl) : that.databaseurl != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (tenantid != null ? !tenantid.equals(that.tenantid) : that.tenantid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (tenantid != null ? tenantid.hashCode() : 0);
        result = 31 * result + (databaseurl != null ? databaseurl.hashCode() : 0);
        result = 31 * result + (apiversion != null ? apiversion.hashCode() : 0);
        return result;
    }
}
