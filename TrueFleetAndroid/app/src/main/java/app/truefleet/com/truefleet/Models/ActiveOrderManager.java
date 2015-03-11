package app.truefleet.com.truefleet.Models;

import android.util.Log;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import app.truefleet.com.truefleet.Activitieis.Events.ActiveOrderLinehauLStatusChangedEvent;
import app.truefleet.com.truefleet.Activitieis.Events.FreightCountChangedEvent;
import app.truefleet.com.truefleet.Activitieis.Events.LinehaulSelectionEvent;
import app.truefleet.com.truefleet.Activitieis.Events.LinehaulStatusUpdateEvent;
import app.truefleet.com.truefleet.Fragments.Updater;
import app.truefleet.com.truefleet.TrueFleetApp;

/**
 * Created by Chris Lacy on 2/23/2015.
 */
public class ActiveOrderManager {

    private final String LOG_TAG = ActiveOrderManager.class.getSimpleName();
    private static volatile ActiveOrderManager instance = new ActiveOrderManager();

    private Order order;
    private List<Linehaul> activeLinehauls;
    private List<Linehaul> completedLinehauls;
    private List<Linehaul> inactiveLinehauls;
    private Updater linehaulUdater;
    private Updater contentUpdater;
    private Linehaul activeLinehaul;
    private List<Linehaul> allLinehauls;
    private LinehaulType linehaulSelection;
    @Inject
    Bus bus;

    private ActiveOrderManager() {
        linehaulSelection = LinehaulType.ACTIVE;
        TrueFleetApp.inject(this);
        bus.register(this);
        linehaulUdater = null;
        contentUpdater = null;
        activeLinehauls = new ArrayList<>();
        completedLinehauls = new ArrayList<>();
        inactiveLinehauls = new ArrayList<>();
        allLinehauls = new ArrayList<>();
        order = null;
    }
    private void clear() {
        linehaulSelection = LinehaulType.ACTIVE;
        activeLinehauls.clear();
        completedLinehauls.clear();
        inactiveLinehauls.clear();
        allLinehauls.clear();
    }

    public void setLinehaulUpdater(Updater linehaulUpdater) {
        this.linehaulUdater = linehaulUpdater;
    }

    public void setContentUpdater(Updater updater) {
        contentUpdater = updater;
    }

    public static ActiveOrderManager getInstance() {
        return instance;
    }

    public void setOrder(Order order) {
        clear();
        this.order = order;
        allLinehauls = Linehaul.getLinehauls(order);
        Log.i(LOG_TAG, allLinehauls.size() + " linehauls found for order");
        activeLinehaul = null;
        setupLinehauls();
        Log.i(LOG_TAG, inactiveLinehauls.size() + " inactive linehauls");

    }

    public List<String> getActiveLinehaulFutureStatuses() {
        List<String> statuses  = new ArrayList();

        try {
            Linehaul active = activeLinehauls.get(0);
            LinehaulStatus activeStatus = active.linehaulStatus;
            if (activeStatus.futurestatus1 != null)
                statuses.add(activeStatus.futurestatus1.status);
            if (activeStatus.futurestatus2 != null)
                statuses.add(activeStatus.futurestatus2.status);
            if (activeStatus.futurestatus3 != null)
                statuses.add(activeStatus.futurestatus3.status);

        } catch (Exception e) {
            Log.e(LOG_TAG, "No active linehaul");
            Log.e(LOG_TAG, e.toString());
        }
        return statuses;
    }

    private void setupLinehauls() {
        try {
            for (Linehaul linehaul : allLinehauls) {
                if (linehaul.linehaulStatus.isActive())
                    if (activeLinehauls.size() < 1) {
                        activeLinehauls.add(linehaul);
                        activeLinehaul = activeLinehauls.get(0);
                        continue;
                    }
                    else{

                        inactiveLinehauls.add(linehaul);
                        continue;
                    }
                else if (linehaul.linehaulStatus.isCompleted())
                    completedLinehauls.add(linehaul);
                else
                    inactiveLinehauls.add(linehaul);
            }
        } catch (Exception e) {
            Log.e(LOG_TAG, "Linehaul status was null in database");
        }
    }
    @Subscribe
    public void linehaulStatusChanged(LinehaulStatusUpdateEvent event) {
        setOrder(order);
        Log.i(LOG_TAG, "received LinehaulStatusUpdateEvent");
        bus.post(new ActiveOrderLinehauLStatusChangedEvent(event.getLinehaul().linehaulStatus));
    }
    public void linehaulStatusChanged(LinehaulStatus linehaulStatus) {
        setOrder(order);
        Log.i(LOG_TAG, "received LinehaulStatusUpdateEvent");
        bus.post(new ActiveOrderLinehauLStatusChangedEvent(linehaulStatus));
    }
    @Subscribe
    public void linehaulTypeSeelction(LinehaulSelectionEvent event) {
        linehaulSelection = event.getSelectionType();
        try {
            if (linehaulSelection == LinehaulType.ACTIVE)
                activeLinehaul = activeLinehauls.get(0);
            else if (linehaulSelection == LinehaulType.INACTIVE)
                activeLinehaul = inactiveLinehauls.get(0);
            else if (linehaulSelection == LinehaulType.COMPLETED)
                activeLinehaul = completedLinehauls.get(0);
        } catch (Exception e) {
            Log.i(LOG_TAG, "No active linehauls in order");
            activeLinehaul = null;
        }
        Log.e(LOG_TAG,"s: " + linehaulSelection);
        //bus.post(new FreightCountChangedEvent(getSelectedFreights().size()));
    }

    public List<Linehaul> getAllLinehauls() {
        return allLinehauls;
    }


    public List<Linehaul> getSelectedLinehauls() {
        if (linehaulSelection == LinehaulType.ACTIVE)
            return activeLinehauls;
        else if (linehaulSelection == LinehaulType.INACTIVE)
            return inactiveLinehauls;
        else if (linehaulSelection == LinehaulType.COMPLETED)
            return completedLinehauls;
        return null;
    }

    public List<Linehaul> getInactiveLinehauls() {
        return inactiveLinehauls;
    }

    public List<Linehaul> getCompletedLinehauls() {

        return completedLinehauls;

    }

    public void setActiveLinehaul(Linehaul linehaul) {
        Log.i(LOG_TAG, "Active linehaul changed");

        this.activeLinehaul = linehaul;
        if (contentUpdater != null)
            contentUpdater.updateUI();

        bus.post(new FreightCountChangedEvent(getSelectedFreights().size()));
    }

    public Linehaul getActiveLinehaul() {
        return activeLinehaul;
    }

    //Returns list of freights or empty list
    public List<Freight> getSelectedFreights() {
        try {
            return activeLinehaul.freights();
        } catch (Exception e) {
            Log.e(LOG_TAG, "Unable to get freights for linehaul ");
        }
        return new ArrayList<>();
    }


    public Container getActiveContainer() {
        try {
            return activeLinehaul.freights().get(0).container;
        } catch (Exception e) {
            Log.e(LOG_TAG, "Unable to get container for linehaul");
        }
        return null;

    }

    public Account getActivePickupAccount() {
        try {
            return activeLinehaul.terminal;
        } catch (Exception e) {
            Log.e(LOG_TAG, "Unable to get active pickup account for linehaul ");
        }
        return null;
    }

    public Account getActiveDeliveryAccount() {
        try {
            return activeLinehaul.receiver;
        } catch (Exception e) {
            Log.e(LOG_TAG, "Unable to get active delivery account for linehaul ");
        }
        return null;
    }

    public Order getOrder() {
        return order;
    }

}
