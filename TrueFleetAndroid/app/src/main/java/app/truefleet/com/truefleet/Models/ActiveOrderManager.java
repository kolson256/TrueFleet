package app.truefleet.com.truefleet.Models;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import app.truefleet.com.truefleet.Fragments.Updater;

/**
 * Created by Chris Lacy on 2/23/2015.
 */
public class ActiveOrderManager {

    private final String LOG_TAG = ActiveOrderManager.class.getSimpleName();
    private static volatile ActiveOrderManager instance = new ActiveOrderManager();

    private Order order;
    private List<Linehaul> linehauls;
    private int activeLinehaulIndex;
    private Updater linehaulUdater;
    private Updater contentUpdater;

    private ActiveOrderManager() {
        linehaulUdater = null;
        contentUpdater = null;
        linehauls = new ArrayList<Linehaul>();
        order = null;
        activeLinehaulIndex = 0;
    }
    public void setLinehaulUpdater(Updater linehaulUpdater) {
        this.linehaulUdater = linehaulUpdater;
    }
    public void setContentUpdater(Updater updater) {
        contentUpdater = updater;
    }
    public static ActiveOrderManager getInstance() { return instance; }

    public void setOrder(Order order) {
        this.order = order;
        linehauls = Linehaul.getLinehauls(order);
        activeLinehaulIndex = 0;
    }

    public List<Linehaul> getLinehauls() { return linehauls; }

    public int getActiveLinehaulIndex() { return activeLinehaulIndex; }

    public void setActiveLinehaulIndex(int index) {
        if (index > (linehauls.size() -1 ))
        {
            Log.e(LOG_TAG, "Attempted to set index greater then list size");
            return;
        }
        this.activeLinehaulIndex =  index;
        if (contentUpdater!=null)
            contentUpdater.updateUI();
    }

    public Linehaul getActiveLinehaul() { return linehauls.get(activeLinehaulIndex); }

    public List<Freight> getActiveFreights() {
        try {
            return linehauls.get(activeLinehaulIndex).freights();
        } catch (Exception e) {
            Log.e(LOG_TAG, "Unable to get freights for linehaul index: " + activeLinehaulIndex);
        }
        return null;
    }

    public Containers getActiveContainer() {
        try {
            return linehauls.get(activeLinehaulIndex).freights().get(0).container;
        } catch (Exception e) {
            Log.e(LOG_TAG, "Unable to get container for linehaul index: " + activeLinehaulIndex);
        }
        return null;

    }

    public Account getActivePickupAccount() {
        try {
            return linehauls.get(activeLinehaulIndex).terminal;
        } catch (Exception e) {
            Log.e(LOG_TAG, "Unable to get active pickup account for linehaul index: " + activeLinehaulIndex);
        }
        return null;
    }

    public Account getActiveDeliveryAccount() {
        try {
            return linehauls.get(activeLinehaulIndex).receiver;
        } catch (Exception e) {
            Log.e(LOG_TAG, "Unable to get active delivery account for linehaul index: " + activeLinehaulIndex);
        }
        return null;
    }

    public Order getOrder() { return order; }

}