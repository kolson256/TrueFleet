package app.truefleet.com.truefleet.Models;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Chris Lacy on 3/7/2015.
 */
@Table(name = "linehaulstatus")
public class LinehaulStatus extends BaseModel {
    @Column(name = "serverid", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    public int id;

    @Column
    public String status;

    @Column
    public LinehaulStatus futurestatus1;

    public LinehaulStatus(int id, String status, LinehaulStatus futurestatus1, LinehaulStatus futurestatus2, LinehaulStatus futurestatus3) {
        this.id = id;
        this.status = status;
        this.futurestatus1 = futurestatus1;
        this.futurestatus2 = futurestatus2;
        this.futurestatus3 = futurestatus3;
    }

    @Column

    public LinehaulStatus futurestatus2;
    @Column
    public LinehaulStatus futurestatus3;

    public LinehaulStatus() {}

    public boolean isActive() {
        if (status.equalsIgnoreCase("Unassigned") || status.equalsIgnoreCase("Rejected") ||
                status.equalsIgnoreCase("Completed")) {
            return false;
        }
        return true;
    }
    //Completed in this case includes rejected
    public boolean isCompleted() {
        if (status.equalsIgnoreCase("rejected") || status.equalsIgnoreCase("completed")) {
            return true;
        }
        return false;
    }
}
