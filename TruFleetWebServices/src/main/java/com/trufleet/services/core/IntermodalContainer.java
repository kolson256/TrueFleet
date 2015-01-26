package com.trufleet.services.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.Size;

/**
 * Created by Richard on 11/12/2014.
 */

public class IntermodalContainer {

    /*
        ISO 6346 Identifier
        Crude validation for now.
        ID should have 4 Capital letters, then 6 digits.
        There may be a 7th "Checksum digit"  we do not need this for now.

    */
    @Size(min=10, max = 10, message = "Container ID must be 10 characters, 4 letters, 6 digits")
    private String id;

    //Length and Height can be implemented later.
//    private int length;
//    private int height;

    @JsonCreator
    public IntermodalContainer(@JsonProperty("id") String id ){
        this.id = id;
    }

    public String getId() {
        return id;
    }


/*    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }*/
}
