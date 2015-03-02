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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.truefleet.com.truefleet.Activitieis.HomeActivity;
import app.truefleet.com.truefleet.Models.ActiveOrderManager;
import app.truefleet.com.truefleet.Models.Adapters.OrderAdapter;
import app.truefleet.com.truefleet.Models.IMTOrder;
import app.truefleet.com.truefleet.Models.Order;
import app.truefleet.com.truefleet.Models.OrderOverviewManager;
import app.truefleet.com.truefleet.Models.User;
import app.truefleet.com.truefleet.R;
import app.truefleet.com.truefleet.Resources.LoginManager;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Chris Lacy on 11/22/2014.
 */
public class HomeFragment extends Fragment implements Updater {
    OrderOverviewManager orderOverviewManager;

    LoginManager loginManager;
    ActiveOrderManager activeOrderManager;

    ArrayAdapter<String> columnAdapter;
    @InjectView(R.id.welcome_text)
    TextView welcomeText;
    @InjectView(R.id.orderreceived_text)
    TextView ordersReceived;

    @InjectView(R.id.listviewActiveOrders)
    ListView listviewActiveOrders;

    @InjectView(R.id.completed_text)
    TextView mCompletedText;
    @InjectView(R.id.listviewCompletedOrders)
    ListView listviewCompletedOrders;
    @InjectView(R.id.buttonLogout)
    Button mButtonLogout;

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
        Intent intent = getActivity().getIntent();
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.inject(this, rootView);

        activeOrderManager = activeOrderManager.getInstance();

      //  try {
            if (loginManager.checkLogin())
                getActivity().finish();
            else
                setup(rootView);
     //   } catch (NullPointerException exception) {
      //      Log.i(LOG_TAG, "User not logged in");
     //       System.out.println(exception);
      //      getActivity().finish();
      //  }

        return rootView;

    }

    public void setup(View rootView) {
        orderOverviewManager = new OrderOverviewManager(getActivity().getApplicationContext());

        User user = loginManager.getUser();
        Log.i(LOG_TAG, "USER: " + user.getUsername());



        orderOverviewManager.reset();
        orderOverviewManager.getCompletedOrders();

        List testOrders = Order.getOrders("test");

        Log.i(LOG_TAG, "GOFKURSELF" + testOrders);

        List<Order> activeOrders =  orderOverviewManager.getActiveOrders();
        List<Order> completeOrders = orderOverviewManager.getCompletedOrders();

        Log.i(LOG_TAG, "total active:" + activeOrders);
        Log.i(LOG_TAG, "total complete: " + completeOrders);
        OrderAdapter activeAdapter = new OrderAdapter(getActivity(), activeOrders);
        OrderAdapter completedAdapter = new OrderAdapter(getActivity(), completeOrders);

        listviewCompletedOrders.setAdapter(completedAdapter);
        listviewActiveOrders.setAdapter(activeAdapter);

        listviewCompletedOrders.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                view.setSelected(true);
                Log.i(LOG_TAG, "ListviewCompleted index: " + position + " clicked");
                activeOrderManager.setOrder(orderOverviewManager
                        .getCompletedOrders()
                        .get(position));
                ((HomeActivity) getActivity()).showOrders();
            }
        });

        listviewActiveOrders.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                view.setSelected(true);
                Log.i(LOG_TAG, "ListviewActive index: " + position + " clicked");
                activeOrderManager.setOrder(orderOverviewManager
                        .getActiveOrders()
                        .get(position));
                ((HomeActivity) getActivity()).showOrders();
            }
        });
        broadcastReceiver = new FragmentReceiverHome();
        getActivity().registerReceiver(broadcastReceiver, new IntentFilter("fragmentupdater"));



        // LoginManager lm = new LoginManager(getActivity().getApplicationContext());
        welcomeText.setText("Welcome, " + user.getUsername() + "!");
        updateUI();
    }

    public void SetListViewOrders(View rootView) {
        ArrayList<String> orders = new ArrayList<>();
        IMTOrder order = IMTOrder.getInstance();
        columnAdapter = new ArrayAdapter<String>
                (getActivity(), R.layout.order_column, R.id.order_column_textview, orders);
        ListView lvColumn = (ListView) rootView.findViewById(R.id.listviewActiveOrders);

        if (order.getOrderType() != null) {
            Log.i(LOG_TAG, "Creating non null order");
            orders.add(order.toString());
        } else {
            orders.add("No orders");
            lvColumn.setAdapter(columnAdapter);
            Log.i(LOG_TAG, "Order was null");
        }

        lvColumn.setAdapter(columnAdapter);

        lvColumn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                view.setSelected(true);
                Log.i(LOG_TAG, "Listview index: " + position + " clicked");
                ((HomeActivity) getActivity()).showOrders();
            }
        });

    }

    public void updateAdapter() {
        if (columnAdapter != null) {
            columnAdapter.clear();
            columnAdapter.add(IMTOrder.getInstance().toString());
        }
    }

    public void updateUI() {
        IMTOrder order = IMTOrder.getInstance();

        if (order.getOrderType() != null) {
            ordersReceived.setText("One order received.");
            updateAdapter();
        } else {
            ordersReceived.setText("No orders received.");
        }
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
