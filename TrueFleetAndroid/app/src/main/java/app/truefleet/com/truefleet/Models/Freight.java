package app.truefleet.com.truefleet.Models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Chris Lacy on 2/4/2015.
 * Model.load(Freight.class, id)   Freight.load(Freight.class, id)
 */
@Table(name = "Freight")
public class Freight extends Model {

    @Column(name = "Container",
            onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
    public Containers container;

    @Column(name = "Linehaul",
            onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
    public Linehaul linehaul;

    @Column(name = "description")
    public String description;

    @Column(name = "quantity")
    public int quantity;

    @Column(name = "weight")
    public int weight;

    @Column(name = "seal")
    public String seal;

    @Column(name = "notes")
    public String notes;


    public Freight() {
        super();
    }

    public Freight(Containers container, Linehaul linehaul, String description, int quantity, int weight, String seal, String notes) {
        super();
        this.container =container;
        this.linehaul = linehaul;
        this.description =  description;
        this.quantity = quantity;
        this.weight = weight;
        this.seal = seal;
        this.notes = notes;
    }



}
