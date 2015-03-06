package app.truefleet.com.truefleet.Fragments;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import app.truefleet.com.truefleet.Activitieis.HomeActivity;
import app.truefleet.com.truefleet.Models.ActiveOrderManager;
import app.truefleet.com.truefleet.Models.Adapters.OrderAdapter;
import app.truefleet.com.truefleet.Models.Order;
import app.truefleet.com.truefleet.Models.OrderOverviewManager;
import app.truefleet.com.truefleet.Models.User;
import app.truefleet.com.truefleet.R;
import app.truefleet.com.truefleet.Resources.LoginManager;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;
import butterknife.OnItemSelected;

/**
 * Created by Chris Lacy on 11/22/2014.
 */
public class HomeFragment extends Fragment implements Updater {
    final int ACTIVE_SELECTION = 0;
    final int INACTIVE_SELECTION = 1;
    List<Order> activeOrders;
    OrderAdapter activeAdapter;
    OrderAdapter completedAdapter;
    OrderOverviewManager orderOverviewManager;
    LoginManager loginManager;
    ActiveOrderManager activeOrderManager;

    @InjectView(R.id.welcome_text)
    TextView welcomeText;
    @InjectView(R.id.orderreceived_text)
    TextView ordersReceived;
    @InjectView(R.id.listviewActiveOrders)
    ListView listviewActiveOrders;
    @InjectView(R.id.buttonLogout)
    com.gc.materialdesign.views.ButtonRectangle mButtonLogout;

    BroadcastReceiver broadcastReceiver;

    private final String LOG_TAG = HomeFragment.class.getSimpleName();

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public class FragmentReceiverHome extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateUI();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        loginManager = new LoginManager(getActivity().getApplicationContext());

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.inject(this, rootView);

        activeOrderManager = activeOrderManager.getInstance();

        if (loginManager.checkLogin())
            getActivity().finish();
        else
            setup(rootView);

        return rootView;

    }

    @OnItemSelected(R.id.order_type_spinner)
    void onItemSelected(int position) {
        Log.d(LOG_TAG, "SELECTED POSITION + " + position);
        if (position == ACTIVE_SELECTION) {
            listviewActiveOrders.setAdapter(activeAdapter);
        } else if (position == INACTIVE_SELECTION) {
            listviewActiveOrders.setAdapter(completedAdapter);
        }
    }

    @OnItemClick(R.id.listviewActiveOrders)
    void onItemClickz(AdapterView<?> parent, View view,
                      int position, long id) {
        Order o = (Order) parent.getAdapter().getItem(position);
        view.setSelected(true);
        Log.i(LOG_TAG, "ListviewActive index: " + position + " clicked");
        activeOrderManager.setOrder(o);
        ((HomeActivity) getActivity()).showOrders();
    }

    public void setup(View rootView) {
        orderOverviewManager = new OrderOverviewManager(getActivity().getApplicationContext());
        updateUI();
    }

    public void updateUI() {


        User user = loginManager.getUser();
        Log.i(LOG_TAG, "USER: " + user.getUsername());

        orderOverviewManager.reset();
        orderOverviewManager.getCompletedOrders();

        activeOrders = orderOverviewManager.getActiveOrders();
        List<Order> completeOrders = orderOverviewManager.getCompletedOrders();

        Log.i(LOG_TAG, "total active:" + activeOrders);
        Log.i(LOG_TAG, "total complete: " + completeOrders);
        activeAdapter = new OrderAdapter(getActivity(), activeOrders);
        completedAdapter = new OrderAdapter(getActivity(), completeOrders);

        listviewActiveOrders.setAdapter(activeAdapter);

        broadcastReceiver = new FragmentReceiverHome();
        getActivity().registerReceiver(broadcastReceiver, new IntentFilter("fragmentupdater"));
        welcomeText.setText("Welcome, " + user.getUsername() + "!");

    }

    @Override
    public void onStop() {
        try {
            getActivity().unregisterReceiver(broadcastReceiver);
        } catch (Exception e) {
            Log.d(LOG_TAG, "Receiver already unregistered");
        }
        super.onStop();
    }

    @Override
    public void onDestroyView() {

        synchronized (this) {
            try {
                getActivity().unregisterReceiver(broadcastReceiver);
            } catch (Exception e) {
                Log.d(LOG_TAG, "Receiver already unregistered");
            }
        }
        super.onDestroyView();
        ButterKnife.reset(this);
    }

}
