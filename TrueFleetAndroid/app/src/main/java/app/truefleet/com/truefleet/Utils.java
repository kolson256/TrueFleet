package app.truefleet.com.truefleet;

import java.util.List;

/**
 * Created by Chris Lacy on 3/7/2015.
 */
public class Utils {
    /**
     * Is arraylist null or empty
     * @param list: ArrayList to check
     * @return <code>true</code> is arraylist is null or empty.
     */
    public static <E> boolean isNullOrEmpty(List<E> list) {
        return list == null || list.size() == 0;
    }
}
