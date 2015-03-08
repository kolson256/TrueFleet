package app.truefleet.com.truefleet.Models;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import app.truefleet.com.truefleet.Resources.LoginManager;

/**
 * Created by Chris Lacy on 3/2/2015.
 */
public class OrderOverviewManager {

    LoginManager loginManager;

    private final String LOG_TAG = OrderOverviewManager.class.getSimpleName();

    public List<Order> getActiveOrders() {
        return activeOrders;
    }

    private List<Order> activeOrders;

    public List<Order> getCompletedOrders() {
        return completedOrders;
    }

    private List<Order> completedOrders;

    public OrderOverviewManager(Context context) {
        loginManager = new LoginManager(context);
        activeOrders = new ArrayList<>();
        completedOrders = new ArrayList<>();
        reset();
    }

    private void setupOrders(List<Order> orders) {
        for (Order o : orders) {
            if (isActive(o)) {
                activeOrders.add(o);
            } else {
                completedOrders.add(o);
            }
            Log.i(LOG_TAG, "Active orders found : " + activeOrders.size());
            Log.i(LOG_TAG, "Completed orders found : " + completedOrders.size());
        }
    }

    //Returns true if there is one active linehaul in order
    private boolean isActive(Order order) {
        List<Linehaul> linehauls = Linehaul.getLinehauls(order);

        if (linehauls.size() == 0)
            return false;
        try {
        for (Linehaul l : linehauls) {
            if (l.linehaulStatus.isActive()) {
                return true;
            }
        } } catch (Exception e) { Log.e(LOG_TAG, "Linehaul status was null");
        }

        return false;
    }

    public void reset() {
        try {
            User user = loginManager.getUser();
            List<Order> orders = Order.getOrders(loginManager.getUser().getUsername());
            Log.i(LOG_TAG, "Orders found for: " + user.getUsername() + " : " + orders.size());
            activeOrders.clear();
            completedOrders.clear();
            setupOrders(orders);

        } catch (NullPointerException exception) {
            Log.i(LOG_TAG, "User not logged in");
        }
    }

    public void addOrder(Order order) {
        try {
            User user = loginManager.getUser();


            if (order.assignedUser == loginManager.getUser().getUsername()) {
                if (isActive(order)) {
                    activeOrders.add(order);
                } else {
                    completedOrders.add(order);
                }
            }

        } catch (NullPointerException exception) {
            Log.i(LOG_TAG, "User not logged in");
        }
    }

}
