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
    public static final String PATH_USER = "user";
    public static final String PATH_ROUTE = "route";
    public static final String PATH_ORDER = "order";
    public static final String PATH_FREIGHT = "freight";
    public static final String PATH_CONTAINER = "container";
    public static final String PATH_LINEHAUL = "linehaul";

    public static final String DATABASE_NAME = "TruFleet.db";

    public static final String CONTENT_AUTHORITY = "app.truefleet.com";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

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

    public static final class RouteDriverEntry implements BaseColumns {
        public static final String TABLE_NAME = "routedriver";

        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_USER_KEY = "userid";
        public static final String COLUMN_NOTES = "notes";

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_ROUTE).build();

        public static final String CONTENT_TYPE =
                "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + PATH_ROUTE;
        public static final String CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + PATH_ROUTE;

        public static Uri buildOrderUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

    }

    public static final class FreightEntry implements BaseColumns {
        public static final String TABLE_NAME = "freight";

        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_CONTAINER_ID = "containerid";

        public static final String COLUMN_LINEHAUL_ID = "internalid";

        public static final String COLUMN_DESCRIPTION = "description";

        public static final String COLUMN_QUANTITY = "quantity";

        public static final String COLUMN_WEIGHT = "weight";

        public static final String COLUMN_SEAL = "seal";
        public static final String COLUMN_NOTES = "notes";

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_FREIGHT).build();

        public static final String CONTENT_TYPE =
                "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + PATH_FREIGHT;
        public static final String CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + PATH_FREIGHT;

        public static Uri buildOrderUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    public static final class ContainerEntry implements BaseColumns {
        public static final String TABLE_NAME = "container";

        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_DESCRIPTION = "description";

        public static final String COLUMN_VOLUME = "volume";

        public static final String COLUMN_LENGTH = "length";
        public static final String COLUMN_WIDTH = "width";
        public static final String COLUMN_HEIGHT = "height";

        public static final String COLUMN_WEIGHT = "weight";
        public static final String COLUMN_NOTES = "notes";

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_CONTAINER).build();

        public static final String CONTENT_TYPE =
                "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + PATH_CONTAINER;
        public static final String CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + PATH_CONTAINER;

        public static Uri buildOrderUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    public static final class OrderEntry implements BaseColumns {
        public static final String TABLE_NAME = "orders";

        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_ORDER_ID = "orderid";
        public static final String COLUMN_EXTERNAL_ID = "externalid";
        public static final String COLUMN_NOTES = "notes";

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
    public static final class LinehaulEntry implements BaseColumns {


        public static final String TABLE_NAME = "linehaul";

        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_ORDER_ID = "orderid";
        public static final String COLUMN_EXTERNAL_ID = "externalid";
        //Foreign Key for RouteDriver
        public static final String COLUMN_ROUTE_KEY = "routeid";

        public static final String COLUMN_SHIP_DATE = "shipdate";
        public static final String COLUMN_ORDER_TYPE = "ordertype";
        public static final String COLUMN_PICKUP_START_DATE= "pickupstartdate";
        public static final String COLUMN_PICKUP_END_DATE = "pickupenddate";
        public static final String COLUMN_DELIVERY_DEADLINE = "deliverydeadline";

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_LINEHAUL).build();

        public static final String CONTENT_TYPE =
                "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + PATH_LINEHAUL;
        public static final String CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + PATH_LINEHAUL;

        public static Uri buildOrderUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }
    public static final class UserEntry implements BaseColumns {

        public static final String TABLE_NAME = "users";

        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_USERNAME = "username";

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_USER).build();

        public static final String CONTENT_TYPE =
                "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + PATH_USER;
        public static final String CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + PATH_USER;

        public static Uri buildOrderUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

    }
    }
