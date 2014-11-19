package com.trufleet.services.core.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Richard on 11/17/2014.
 * Utility Class
 */

public class PhoneEntry {

    /*
        Enum later?  Intention is that this field will have values such as
        main, mobile, fax, office, home, etc.
     */
    @NotNull
    private String type;

    /*
        Cheap validation on size - as min valid phone would be
        123-4567 (without dash)
     */
    @NotNull
    @Size(min = 7)
    private String number;

    @JsonCreator
    public PhoneEntry(@JsonProperty String type, @JsonProperty String number){
        this.number = number;
        this.type = type;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return type + ": " + number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhoneEntry that = (PhoneEntry) o;

        if (!number.equals(that.number)) return false;
        if (!type.equals(that.type)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + number.hashCode();
        return result;
    }
}
