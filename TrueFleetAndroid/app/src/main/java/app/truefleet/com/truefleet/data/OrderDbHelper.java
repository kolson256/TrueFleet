package app.truefleet.com.truefleet.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import app.truefleet.com.truefleet.data.OrderContract.UserEntry;
import app.truefleet.com.truefleet.data.OrderContract.LinehaulEntry;
import app.truefleet.com.truefleet.data.OrderContract.FreightEntry;
import app.truefleet.com.truefleet.data.OrderContract.ContainerEntry;
import app.truefleet.com.truefleet.data.OrderContract.OrderEntry;
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
                OrderEntry.TABLE_NAME + " ( " +
                OrderEntry._ID  + " INTEGER PRIMARY KEY, " +
                OrderEntry.COLUMN_EXTERNAL_ID + " TEXT NOT NULL, " +
                OrderEntry.COLUMN_ORDER_ID + " TEXT NOT NULL, " +
                OrderEntry.COLUMN_NOTES + " TEXT) ";

        final String SQL_CREATE_LINEHAUL_TABLE = "CREATE TABLE " +
                LinehaulEntry.TABLE_NAME +  " ( " +
                LinehaulEntry._ID + " INTEGER PRIMARY KEY, " +
                LinehaulEntry.COLUMN_ORDER_ID + " TEXT NOT NULL, " +

                LinehaulEntry.COLUMN_ROUTE_KEY + " INTEGER NOT NULL, " + //FK ROUTE TABLE
                LinehaulEntry.COLUMN_SHIP_DATE + " TIMESTAMP NOT NULL, " +

                LinehaulEntry.COLUMN_ORDER_TYPE + " TEXT NOT NULL, " +
                LinehaulEntry.COLUMN_PICKUP_START_DATE + " TIMESTAMP NOT NULL, " +

                LinehaulEntry.COLUMN_PICKUP_END_DATE + " TIMESTAMP NOT NULL, " +
                LinehaulEntry.COLUMN_DELIVERY_DEADLINE + " TIMESTAMP NOT NULL, " +

                " UNIQUE (" + LinehaulEntry.COLUMN_ORDER_ID + ") ON CONFLICT REPLACE);";

        final String SQL_CREATE_USER_TABLE = "CREATE TABLE " +
                UserEntry.TABLE_NAME + " ( " +
                UserEntry._ID + " INTEGER PRIMARY KEY, " +
                UserEntry.COLUMN_USERNAME + " TEXT NOT NULL) ";

        final String SQL_CREATE_ROUTE_TABLE = "CREATE TABLE " +
                OrderContract.RouteDriverEntry.TABLE_NAME + " ( " +
                OrderContract.RouteDriverEntry._ID + " INTEGER PRIMARY KEY, " +
                OrderContract.RouteDriverEntry.COLUMN_USER_KEY + " INTEGER NOT NULL, " + //FK USER
                OrderContract.RouteDriverEntry.COLUMN_NOTES + " TEXT)";

        final String SQL_CREATE_FREIGHT_TABLE = "CREATE TABLE " +
                FreightEntry.TABLE_NAME + " ( " +
                FreightEntry._ID + " INTEGER PRIMARY KEY, " +
                FreightEntry.COLUMN_CONTAINER_ID + " TEXT NOT NULL, " +
                FreightEntry.COLUMN_LINEHAUL_ID + " TEXT NOT NULL, " +
                FreightEntry.COLUMN_DESCRIPTION + " TEXT, " +
                FreightEntry.COLUMN_QUANTITY + " TEXT NOT NULL, " +
                FreightEntry.COLUMN_WEIGHT + " INTEGER NOT NULL, " +
                FreightEntry.COLUMN_SEAL + " TEXT NOT NULL, " +
                FreightEntry.COLUMN_NOTES + " TEXT) ";

        final String SQL_CREATE_CONTAINER_TABLE = "CREATE TABLE " +
                ContainerEntry.TABLE_NAME + " ( " +
                ContainerEntry._ID + " INTEGER PRIMARY KEY, " +
                ContainerEntry.COLUMN_DESCRIPTION + " TEXT, " +
                ContainerEntry.COLUMN_VOLUME + " INTEGER NOT NULL, " +
                ContainerEntry.COLUMN_LENGTH + " INTEGER NOT NULL, " +
                ContainerEntry.COLUMN_WIDTH + " INTEGER NOT NULL, " +
                ContainerEntry.COLUMN_HEIGHT + " INTEGER NOT NULL, " +
                ContainerEntry.COLUMN_WEIGHT + " INTEGER NOT NULL, " +
                ContainerEntry.COLUMN_NOTES + " TEXT) ";


        sqLiteDatabase.execSQL(SQL_CREATE_ORDER_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_CONTAINER_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_FREIGHT_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_USER_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_LINEHAUL_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_ROUTE_TABLE);

    }

    //Drops table if Version changes
    //Must change to alter table versions in order to not lose user data and transfer over
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + OrderContract.LinehaulEntry.TABLE_NAME);

    }
}
