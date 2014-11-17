package com.trufleet.services.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by Richard on 11/12/2014.
 */
public class ContainerLoad {

    @NotNull
    @Valid
    private String containerID;

    @NotNull
    private String seal;

    private int pieces;
    private int weight;

    private DateTime shipDate;

    @JsonCreator
    public ContainerLoad(@JsonProperty("containerid")String containerID ,@JsonProperty("seal") String seal) {
        this.containerID = containerID;
        this.seal = seal;
    }

    public String getContainer() {
        return containerID;
    }

    public String getSeal() {
        return seal;
    }

    public DateTime getShipDate() {
        return shipDate;
    }

    public void setShipDate(DateTime shipDate) {
        this.shipDate = shipDate;
    }

    public int getPieces() {
        return pieces;
    }

    public void setPieces(int pieces) {
        this.pieces = pieces;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
