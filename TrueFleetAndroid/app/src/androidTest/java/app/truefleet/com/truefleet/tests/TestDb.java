package app.truefleet.com.truefleet.tests;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.util.Log;

import app.truefleet.com.truefleet.data.OrderContract.OrderEntry;
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
        String internalid = "12345";
        String externalid = "56789";
        String containerid = "54321";
        String receipttime = "20141205";
        String ordertype = "type";
        String railLine = "rail";
        String pickupcontact = "pickup";
        String dropoffcontact = "dropoff";
        String deliverywindowopen = "20141005";
        String deliverywindowclose = "20141006";

        OrderDbHelper dbHelper = new OrderDbHelper(mContext);

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(OrderEntry.COLUMN_INTERNAL_ID, internalid);
        values.put(OrderEntry.COLUMN_EXTERNAL_ID, externalid);
        values.put(OrderEntry.COLUMN_CONTAINER_ID, containerid);
        values.put(OrderEntry.COLUMN_RECEIPT_TIME, receipttime);
        values.put(OrderEntry.COLUMN_ORDER_TYPE, ordertype);
        values.put(OrderEntry.COLUMN_RAIL_LINE, railLine);
        values.put(OrderEntry.COLUMN_PICKUP_CONTRACT, pickupcontact);
        values.put(OrderEntry.COLUMN_DROPOFF_CONTRACT, dropoffcontact);
        values.put(OrderEntry.COLUMN_DELIVERY_WINDOW_OPEN, deliverywindowopen);
        values.put(OrderEntry.COLUMN_DELIVERY_WINDOW_CLOSE, deliverywindowclose);

        long rowId = db.insert(OrderEntry.TABLE_NAME, null, values);

        assertTrue(rowId != -1);
        Log.d(LOG_TAG, "Row ID: " + rowId);

        String[] columns = {
                OrderEntry._ID,
                OrderEntry.COLUMN_INTERNAL_ID,
                OrderEntry.COLUMN_EXTERNAL_ID,
                OrderEntry.COLUMN_CONTAINER_ID,
                OrderEntry.COLUMN_RECEIPT_TIME,
                OrderEntry.COLUMN_ORDER_TYPE,
                OrderEntry.COLUMN_RAIL_LINE,
                OrderEntry.COLUMN_PICKUP_CONTRACT,
                OrderEntry.COLUMN_DROPOFF_CONTRACT,
                OrderEntry.COLUMN_DELIVERY_WINDOW_OPEN,
                OrderEntry.COLUMN_DELIVERY_WINDOW_CLOSE
        };

        Cursor cursor = db.query(OrderEntry.TABLE_NAME,
                columns, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int internalIdIndex = cursor.getColumnIndex(OrderEntry.COLUMN_INTERNAL_ID);
            int externalIdIndex = cursor.getColumnIndex(OrderEntry.COLUMN_EXTERNAL_ID);
            int containerIdIndex = cursor.getColumnIndex(OrderEntry.COLUMN_CONTAINER_ID);
            int receiptTimeIndex = cursor.getColumnIndex(OrderEntry.COLUMN_RECEIPT_TIME);
            int orderTypeIndex = cursor.getColumnIndex(OrderEntry.COLUMN_ORDER_TYPE);
            int railLineIndex = cursor.getColumnIndex(OrderEntry.COLUMN_RAIL_LINE);
            int pickupContractIndex = cursor.getColumnIndex(OrderEntry.COLUMN_PICKUP_CONTRACT);
            int dropoffContractIndex = cursor.getColumnIndex(OrderEntry.COLUMN_DROPOFF_CONTRACT);
            int deliveryWindowOpenIndex = cursor.getColumnIndex(OrderEntry.COLUMN_DELIVERY_WINDOW_OPEN);
            int deliveryWindowCloseIndex = cursor.getColumnIndex(OrderEntry.COLUMN_DELIVERY_WINDOW_CLOSE);

            assertEquals(internalid, cursor.getString(internalIdIndex));
            assertEquals(internalid, cursor.getString(externalIdIndex));
            assertEquals(internalid, cursor.getString(containerIdIndex));

        }
        //No values returned
        else {
            assertEquals("test", "failed");
        }

        }
    }

