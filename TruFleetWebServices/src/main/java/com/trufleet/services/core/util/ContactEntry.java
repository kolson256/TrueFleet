package com.trufleet.services.core.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Richard on 11/12/2014.
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ContactEntry {

    @JsonProperty
    private String id;

    @NotNull
    private String name;

    @NotNull
    private String address;

    /*
        Needs to be JSON tree of format
        { entries:
            type: number
            type: number}
     */
    @JsonProperty("phone")
    private String phoneEntries;

    @JsonProperty
    private String notes;

    public ContactEntry(@JsonProperty String name, @JsonProperty String address) {
        this.name = name;
        this.address = address;
    }

    public ContactEntry(String notes, String id, String name, String address, String phoneEntries) {
        this.notes = notes;
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneEntries = phoneEntries;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneEntries() {
        return phoneEntries;
    }

    public void setPhoneEntries(String entries) {
        phoneEntries = entries;
    }

}
