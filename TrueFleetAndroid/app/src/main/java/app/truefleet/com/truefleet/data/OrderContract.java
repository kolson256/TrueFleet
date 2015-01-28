package app.truefleet.com.truefleet.data;

import android.net.Uri;
import android.content.ContentUris;
import android.provider.BaseColumns;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Chris Lacy on 1/26/2015.
 */
public class OrderContract {
    public static final String CONTENT_AUTHORITY = "app.truefleet.com";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_ORDER = "order";

    public static final String DATE_FORMAT = "yyyyMMdd";

    //convert UNIX to date
    public static String getDbDateString(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(date);
    }

    //convert date to unix
    public static Date getDateFromDb(String dateText) {
        SimpleDateFormat dbDateFormat = new SimpleDateFormat(DATE_FORMAT);
        try {
            return dbDateFormat.parse(dateText);
        } catch ( ParseException e ) {
            e.printStackTrace();
            return null;
        }
    }

    public static final class OrderEntry implements BaseColumns {

        public static final String TABLE_NAME = "order";

        public static final String COLUMN_INTERNAL_ID = "internalid";
        public static final String COLUMN_EXTERNAL_ID = "externalid";
        public static final String COLUMN_CONTAINER_ID = "containerid";


        public static final String COLUMN_RECEIPT_TIME = "receiptTimeStamp";
        public static final String COLUMN_ORDER_TYPE = "ordertype";
        public static final String COLUMN_RAIL_LINE = "railline";
        public static final String COLUMN_PICKUP_CONTRACT = "pickupcontract";
        public static final String COLUMN_DROPOFF_CONTRACT = "dropoffcontract";
        public static final String COLUMN_DELIVERY_WINDOW_OPEN = "delvierywindowopen";
        public static final String COLUMN_DELIVERY_WINDOW_CLOSE = "deliverywindowdclose";

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_ORDER).build();

        public static final String CONTENT_TYPE =
                "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + PATH_ORDER;
        public static final String CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + PATH_ORDER;

        public static Uri buildOrderUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    }
