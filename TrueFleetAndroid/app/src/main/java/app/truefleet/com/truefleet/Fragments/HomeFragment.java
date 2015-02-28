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
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import app.truefleet.com.truefleet.Activitieis.HomeActivity;
import app.truefleet.com.truefleet.Models.IMTOrder;
import app.truefleet.com.truefleet.Models.User;
import app.truefleet.com.truefleet.R;
import app.truefleet.com.truefleet.Resources.LoginManager;

/**
 * Created by Chris Lacy on 11/22/2014.
 */
public class HomeFragment extends Fragment {
    ArrayAdapter<String> columnAdapter;
    private TextView ordersReceived = null;
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

        LoginManager loginManager = new LoginManager(getActivity().getApplicationContext());
        Intent intent = getActivity().getIntent();

        if (loginManager.checkLogin())
            getActivity().finish();

        broadcastReceiver = new FragmentReceiverHome();
        getActivity().registerReceiver(broadcastReceiver, new IntentFilter("fragmentupdater"));

        User user = loginManager.getUser();
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        TextView tvWelcome = (TextView) rootView.findViewById(R.id.welcome_text);

        LoginManager lm = new LoginManager(getActivity().getApplicationContext());
        tvWelcome.setText("Welcome, " + user.getUsername() + "!");
        ordersReceived = (TextView) rootView.findViewById(R.id.orderreceived_text);
        updateUI();

    /*
            if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)) {
                String welcome = intent.getStringExtra(Intent.EXTRA_TEXT);
                System.out.println(welcome);
                TextView tvWelcome = (TextView) rootView.findViewById(R.id.welcome_text);
                tvWelcome.setText(welcome);
            }*/
        SetListViewOrders(rootView);
        return rootView;

    }

    public void SetListViewOrders(View rootView) {
        ArrayList<String> orders = new ArrayList<>();
        IMTOrder order = IMTOrder.getInstance();
        columnAdapter = new ArrayAdapter<String>
                (getActivity(), R.layout.order_column, R.id.order_column_textview, orders);
        ListView lvColumn = (ListView) rootView.findViewById(R.id.listViewOrders);

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
                    ((HomeActivity)getActivity()).showOrders();
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
    public void onStop()
    {
        try {
            getActivity().unregisterReceiver(broadcastReceiver);
        } catch (Exception e) {
            Log.d(LOG_TAG, "Receiver already unregistered");
        }
        super.onStop();
    }

    @Override
    public void onDestroyView() {

        synchronized(this) {
                try {
                    getActivity().unregisterReceiver(broadcastReceiver);
                } catch (Exception e) {
                    Log.d(LOG_TAG, "Receiver already unregistered");
                }
            }
        super.onDestroyView();
    }

}
