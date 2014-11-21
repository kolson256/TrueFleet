package app.truefleet.com.truefleet.Models;

/**
 * Created by Chris Lacy on 11/19/2014.
 */

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotNull;


public class ContactEntry {

    private String id;

    @NotNull
    private String name;

    @NotNull
    private String address;

    private Map<String, String> phoneEntries = new HashMap<>();

    private String notes;

    public ContactEntry(String name, String address) {
        this.name = name;
        this.address = address;
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

    public Map<String, String> getPhoneEntries() {
        return phoneEntries;
    }

    public void setPhoneEntries(Map<String, String> phoneEntries) {
        this.phoneEntries = phoneEntries;
    }
}

