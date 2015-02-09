package app.truefleet.com.truefleet.tests;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.util.Log;

import app.truefleet.com.truefleet.Models.Container;
import app.truefleet.com.truefleet.data.OrderContract;
import app.truefleet.com.truefleet.data.OrderContract.UserEntry;
import app.truefleet.com.truefleet.data.OrderContract.ContainerEntry;
import app.truefleet.com.truefleet.data.OrderDbHelper;

/**
 * Created by Chris Lacy on 1/26/2015.
 */
public class TestDb extends AndroidTestCase {
    public static final String LOG_TAG = TestDb.class.getSimpleName();

    public void testCreateDb() throws Throwable {
        mContext.deleteDatabase(OrderDbHelper.DATABASE_NAME);
        SQLiteDatabase db = new OrderDbHelper(this.mContext).getWritableDatabase();
        assertEquals(true, db.isOpen());
        db.close();
    }

    public void testInsertReadDb() {
        Container container = new Container();
        container.setDescription("description");
        String username = "test";
        String orderid = "12345";
        String externalid = "56789";
        String shipdate = "1423058445";
        String ordertype = "type";
        String pickupstartdate = "1423058445";
        String pickupenddate = "1423058485";
        String deliverydeadline = "20141005";
        String routeDriverNotes = "route driver notes";


        OrderDbHelper dbHelper = new OrderDbHelper(mContext);

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        //CONTAINER
        ContentValues containerValues = new ContentValues();
        containerValues.put(ContainerEntry.COLUMN_DESCRIPTION, container.getDescription());

        long containerId = db.insert(ContainerEntry.TABLE_NAME, null, containerValues);
        assert (containerId != -1);
        //USER
        ContentValues userValues = new ContentValues();
        userValues.put(UserEntry.COLUMN_USERNAME, username);


        long userRowId = db.insert(UserEntry.TABLE_NAME, null, userValues);

        assertTrue(userRowId != -1);

        //ROUTE DRIVER TABLE
        ContentValues routeValues = new ContentValues();
        routeValues.put(OrderContract.RouteDriverEntry.COLUMN_USER_KEY, userRowId);
        routeValues.put(OrderContract.RouteDriverEntry.COLUMN_NOTES, routeDriverNotes);
        long routeId = db.insert(OrderContract.RouteDriverEntry.TABLE_NAME, null, routeValues);

        assertTrue(routeId != -1);


        ContentValues values = new ContentValues();
        values.put(OrderContract.LinehaulEntry.COLUMN_ORDER_ID, orderid);
        values.put(OrderContract.LinehaulEntry.COLUMN_ROUTE_KEY, routeId);
        values.put(OrderContract.LinehaulEntry.COLUMN_SHIP_DATE, shipdate);
        values.put(OrderContract.LinehaulEntry.COLUMN_ORDER_TYPE, ordertype);
        values.put(OrderContract.LinehaulEntry.COLUMN_PICKUP_START_DATE, pickupstartdate);
        values.put(OrderContract.LinehaulEntry.COLUMN_PICKUP_END_DATE, pickupenddate);
        values.put(OrderContract.LinehaulEntry.COLUMN_DELIVERY_DEADLINE, deliverydeadline);

        long rowId = db.insert(OrderContract.LinehaulEntry.TABLE_NAME, null, values);

        assertTrue(rowId != -1);
        Log.d(LOG_TAG, "Row ID: " + rowId);

        String[] columns = {
                OrderContract.LinehaulEntry._ID,
                OrderContract.LinehaulEntry.COLUMN_ORDER_ID,
                OrderContract.LinehaulEntry.COLUMN_ROUTE_KEY,
                OrderContract.LinehaulEntry.COLUMN_SHIP_DATE,
                OrderContract.LinehaulEntry.COLUMN_ORDER_TYPE,
                OrderContract.LinehaulEntry.COLUMN_PICKUP_START_DATE,
                OrderContract.LinehaulEntry.COLUMN_PICKUP_END_DATE,
                OrderContract.LinehaulEntry.COLUMN_DELIVERY_DEADLINE,
        };

        Cursor cursor = db.query(OrderContract.LinehaulEntry.TABLE_NAME,
                columns, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int internalIdIndex = cursor.getColumnIndex(OrderContract.LinehaulEntry.COLUMN_ORDER_ID);
            int routeIdIndex = cursor.getColumnIndex(OrderContract.LinehaulEntry.COLUMN_ROUTE_KEY);
            int shipDateIndex = cursor.getColumnIndex(OrderContract.LinehaulEntry.COLUMN_SHIP_DATE);
            int pickupStartDateIndex = cursor.getColumnIndex(OrderContract.LinehaulEntry.COLUMN_PICKUP_START_DATE);
            int pickupEndDateIndex = cursor.getColumnIndex(OrderContract.LinehaulEntry.COLUMN_PICKUP_END_DATE);
            int deliveryDeadlineIndex = cursor.getColumnIndex(OrderContract.LinehaulEntry.COLUMN_DELIVERY_DEADLINE);

            assertEquals(orderid, cursor.getString(internalIdIndex));
            assertEquals(routeId, cursor.getInt(routeIdIndex));
            assertEquals(pickupstartdate, cursor.getString(pickupStartDateIndex));
            assertEquals(shipdate, cursor.getString(shipDateIndex));
            assertEquals(pickupenddate, cursor.getString(pickupEndDateIndex));
            assertEquals(deliverydeadline, cursor.getString(deliveryDeadlineIndex));

            assertNotNull(OrderContract.getDateFromDb(pickupstartdate));

        }
        //No values returned
        else {
            assertEquals("test", "failed");
        }
    }
}