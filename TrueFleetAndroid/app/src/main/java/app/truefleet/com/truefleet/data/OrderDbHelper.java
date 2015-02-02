package app.truefleet.com.truefleet.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import app.truefleet.com.truefleet.data.OrderContract.OrderEntry;
import app.truefleet.com.truefleet.data.OrderContract.UserEntry;
/**
 * Created by Chris Lacy on 1/26/2015.
 */
public class OrderDbHelper extends SQLiteOpenHelper {

    /*
    Increment if changing schema
    Update onUpgrade in order to not lose data when changing schema
     */
    private static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME =  OrderContract.DATABASE_NAME;

    public OrderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_ORDER_TABLE = "CREATE TABLE " +
                OrderEntry.TABLE_NAME +  " ( " +
                OrderEntry._ID + " INTEGER PRIMARY KEY, " +
                OrderEntry.COLUMN_INTERNAL_ID + " TEXT NOT NULL, " +

                OrderEntry.COLUMN_EXTERNAL_ID + " TEXT NOT NULL, " +

                OrderEntry.COLUMN_ROUTE_KEY + " INTEGER NOT NULL, " + //FK ROUTE TABLE
                OrderEntry.COLUMN_RECEIPT_TIME + " TEXT NOT NULL, " +

                OrderEntry.COLUMN_ORDER_TYPE + " TEXT NOT NULL, " +
                OrderEntry.COLUMN_RAIL_LINE+ " TEXT NOT NULL, " +
                OrderEntry.COLUMN_PICKUP_CONTRACT + " TEXT NOT NULL, " +

                OrderEntry.COLUMN_DROPOFF_CONTRACT + " TEXT NOT NULL, " +
                OrderEntry.COLUMN_DELIVERY_WINDOW_OPEN + " TEXT NOT NULL, " +
                OrderEntry.COLUMN_DELIVERY_WINDOW_CLOSE + " TEXT NOT NULL, " +

                " UNIQUE (" + OrderEntry.COLUMN_INTERNAL_ID  + ") ON CONFLICT REPLACE);";

        final String SQL_CREATE_USER_TABLE = "CREATE TABLE " +
                UserEntry.TABLE_NAME + " ( " +
                UserEntry._ID + " INTEGER PRIMARY KEY, " +
                UserEntry.COLUMN_USERNAME + " TEXT NOT NULL) ";

        final String SQL_CREATE_ROUTE_TABLE = "CREATE TABLE " +
                OrderContract.RouteDriverEntry.TABLE_NAME + " ( " +
                OrderContract.RouteDriverEntry._ID + " INTEGER PRIMARY KEY, " +
                OrderContract.RouteDriverEntry.COLUMN_USER_KEY + " INTEGER NOT NULL) "; //FK USER


        sqLiteDatabase.execSQL(SQL_CREATE_USER_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_ORDER_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_ROUTE_TABLE);

    }

    //Drops table if Version changes
    //Must change to alter table versions in order to not lose user data and transfer over
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + OrderEntry.TABLE_NAME);

    }
}
