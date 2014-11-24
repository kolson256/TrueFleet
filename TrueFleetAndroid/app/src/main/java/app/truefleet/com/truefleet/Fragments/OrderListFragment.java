package app.truefleet.com.truefleet.Fragments;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import app.truefleet.com.truefleet.Activitieis.HomeActivity;
import app.truefleet.com.truefleet.Models.IMTOrder;
import app.truefleet.com.truefleet.R;

/**
 * Created by Chris Lacy on 11/22/2014.
 */
public class OrderListFragment extends Fragment {
    private final String LOG_TAG = OrderListFragment.class.getSimpleName();
    ArrayAdapter<String> columnAdapter;
    ArrayList<String> orders;
    IMTOrder order;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_list, container, false);

        orders = new ArrayList<>();
         order = IMTOrder.getInstance();

        String toAdd = "No orders";
        if (order.getOrderType() != null) {
            toAdd = order.toString();
        }
            orders.add(toAdd);

            columnAdapter = new ArrayAdapter<String>
                    (getActivity(), R.layout.order_column, R.id.order_column_textview, orders);

            ListView lvColumn = (ListView) view.findViewById(R.id.orderList);
            lvColumn.setAdapter(columnAdapter);

            lvColumn.setOnItemClickListener(new AdapterView.OnItemClickListener()  {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    view.setSelected(true);
                    Log.i(LOG_TAG, "Listview index: " + position + " clicked");
                    ((HomeActivity)getActivity()).showOrders();
                }
            });

        return view;
    }
    public BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            orders.clear();
            orders.add(order.toString());
        }
    };


}
