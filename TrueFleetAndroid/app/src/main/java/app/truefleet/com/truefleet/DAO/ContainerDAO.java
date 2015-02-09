package app.truefleet.com.truefleet.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import app.truefleet.com.truefleet.Models.Container;
import app.truefleet.com.truefleet.data.OrderContract.ContainerEntry;
import app.truefleet.com.truefleet.data.OrderDbHelper;
/**
 * Created by Chris Lacy on 2/4/2015.
 */
public class ContainerDAO {

    private final String LOG_TAG = ContainerDAO.class.getSimpleName();

    // Database fields
    private SQLiteDatabase database;
    private OrderDbHelper dbHelper;

    private String[] allColumns = {
            ContainerEntry.COLUMN_ID,
            ContainerEntry.COLUMN_DESCRIPTION,
            ContainerEntry.COLUMN_VOLUME,
            ContainerEntry.COLUMN_LENGTH,
            ContainerEntry.COLUMN_WIDTH,
            ContainerEntry.COLUMN_HEIGHT,
            ContainerEntry.COLUMN_WEIGHT,
            ContainerEntry.COLUMN_NOTES
            };

    public ContainerDAO(Context context) {
        dbHelper = new OrderDbHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void deleteContainer(Container container) {
        long id = container.getId();

        database.delete(ContainerEntry.TABLE_NAME, ContainerEntry.COLUMN_ID
                + "='" + id + "'", null);
        Log.d(LOG_TAG, "Container deleted with id: " + id);

    }

    public Container insertContainer(Container container) {
        ContentValues values = new ContentValues();
        values.put(ContainerEntry.COLUMN_DESCRIPTION, container.getDescription());
        values.put(ContainerEntry.COLUMN_VOLUME, container.getVolume());
        values.put(ContainerEntry.COLUMN_LENGTH, container.getLength());
        values.put(ContainerEntry.COLUMN_WIDTH, container.getWidth());
        values.put(ContainerEntry.COLUMN_HEIGHT, container.getHeight());
        values.put(ContainerEntry.COLUMN_WEIGHT, container.getWeight());
        values.put(ContainerEntry.COLUMN_NOTES, container.getNotes());


        long insertId = database.insert(ContainerEntry.TABLE_NAME, null,
                values);

        Cursor cursor =selectContainerById(insertId);
        Container newContainer = null;
        if (cursor.moveToFirst()) {
            newContainer = cursorToContainer(cursor);
        }
        cursor.close();
        return newContainer;
    }

    public Container getContainer(long id) {

        Cursor cursor =selectContainerById(id);

        Container container = null;

        if (cursor.moveToFirst()) {
            container = cursorToContainer(cursor);
        }

        cursor.close();
        return container;
    }
    public List<Container> getAllContainers() {
        List<Container> containers = new ArrayList<Container>();

        Cursor cursor = database.query(ContainerEntry.TABLE_NAME,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Container container = cursorToContainer(cursor);
            containers.add(container);
            cursor.moveToNext();
        }

        cursor.close();
        return containers;
    }

    private Container cursorToContainer(Cursor cursor) {
        Container container = new Container();
        int idIndex = cursor.getColumnIndex(ContainerEntry.COLUMN_ID);
        int descriptionIndex = cursor.getColumnIndex(ContainerEntry.COLUMN_DESCRIPTION);
        int volumeIndex = cursor.getColumnIndex(ContainerEntry.COLUMN_VOLUME);
        int lengthIndex = cursor.getColumnIndex(ContainerEntry.COLUMN_LENGTH);
        int widthIndex = cursor.getColumnIndex(ContainerEntry.COLUMN_WIDTH);
        int heightIndex = cursor.getColumnIndex(ContainerEntry.COLUMN_HEIGHT);
        int weightIndex = cursor.getColumnIndex(ContainerEntry.COLUMN_WEIGHT);
        int notesIndex = cursor.getColumnIndex(ContainerEntry.COLUMN_NOTES);

        container.setId(cursor.getInt(idIndex));
        container.setDescription(cursor.getString(descriptionIndex));
        container.setVolume(cursor.getInt(volumeIndex));
        container.setLength(cursor.getInt(lengthIndex));
        container.setWidth(cursor.getInt(widthIndex));
        container.setHeight(cursor.getInt(heightIndex));
        container.setWeight(cursor.getInt(weightIndex));
        container.setNotes(cursor.getString(notesIndex));

        return container;
    }

    public Cursor selectContainerById(long id) {
        return database.rawQuery("SELECT * FROM " + ContainerEntry.TABLE_NAME + " WHERE " + ContainerEntry.COLUMN_ID + "='" + id + "'", null);
    }
}
