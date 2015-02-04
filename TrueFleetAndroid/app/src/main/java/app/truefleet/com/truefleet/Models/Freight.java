package app.truefleet.com.truefleet.Models;

import javax.validation.constraints.NotNull;

/**
 * Created by Chris Lacy on 2/4/2015.
 */
public class Freight {

    @NotNull
    private int id;
    @NotNull
    private int containerid;
    private String description;
    private int quantity;
    private int weight;
    private String seal;
    private String notes;

    public Freight(int containerid) {
        this.containerid =containerid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getContainerid() {
        return containerid;
    }

    public void setContainerid(int containerid) {
        this.containerid = containerid;
    }

    public String getDescription() {
        return description;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getSeal() {
        return seal;
    }

    public void setSeal(String seal) {
        this.seal = seal;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
