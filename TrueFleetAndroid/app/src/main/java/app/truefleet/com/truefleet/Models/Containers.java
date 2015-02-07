package app.truefleet.com.truefleet.Models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;

/**
 * Created by Chris Lacy on 2/5/2015.
 */
public class Containers extends Model {

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

    public Containers() { super(); }

    public Containers(String description, int volume, int length, int width, int height, int weight, String notes) {
        super();
        this.description = description;
        this.volume = volume;
        this.length = length;
        this.width = width;
        this.height = height;
        this.weight = weight;
        this.notes = notes;
    }
}
