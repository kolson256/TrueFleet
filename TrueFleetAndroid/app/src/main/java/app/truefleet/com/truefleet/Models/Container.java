package app.truefleet.com.truefleet.Models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Chris Lacy on 2/5/2015.
 */
@Table(name="Container")
public class Container extends Model {

    @Column(name = "serverid", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    public int id;

    @Column(name="description")
    public String description;

    @Column(name="volume")
    public int volume;

    @Column(name="length")
    public int length;

    @Column(name="width")
    public int width;

    @Column(name="height")
    public int height;

    @Column(name="weight")
    public int weight;

    @Column(name="notes")
    public String notes;

    public Container() { super(); }

    public Container(int id, String description, int volume, int length, int width, int height, int weight, String notes) {
        super();
        this.id = id;
        this.description = description;
        this.volume = volume;
        this.length = length;
        this.width = width;
        this.height = height;
        this.weight = weight;
        this.notes = notes;
    }
}
