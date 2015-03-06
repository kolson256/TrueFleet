package app.truefleet.com.truefleet.Models;

import com.activeandroid.Model;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Chris Lacy on 3/5/2015.
 */
public abstract class BaseModel extends Model {

    public String convertTime(long time){
        Date date = new Date(time);
        DateFormat shortDf = DateFormat.getTimeInstance(DateFormat.SHORT);

        return shortDf.format(date);
    }
    public String convertDateTime(long time){
        Date date = new Date(time);
        DateFormat shortDf = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);

        return shortDf.format(date);
    }
    public String convertDate(long time){
        Date date = new Date(time);
        DateFormat shortDf = DateFormat.getDateInstance(DateFormat.SHORT);

        return shortDf.format(date);
    }
}
