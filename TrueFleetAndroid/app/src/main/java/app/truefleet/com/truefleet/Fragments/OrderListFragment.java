package app.truefleet.com.truefleet.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import app.truefleet.com.truefleet.Models.IMTOrder;
import app.truefleet.com.truefleet.R;

/**
 * Created by Chris Lacy on 11/22/2014.
 */
public class OrderListFragment extends Fragment {
    private final String LOG_TAG = OrderListFragment.class.getSimpleName();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_list, container, false);

        ArrayList<String> orders = new ArrayList<>();
        IMTOrder order = IMTOrder.getInstance();


        if (order != null) {
            Log.i(LOG_TAG, "Creating non null order");
            orders.add(order.toString());

            ArrayAdapter<String> columnAdapter = new ArrayAdapter<String>
                    (getActivity(), R.layout.side_panel_column, R.id.side_panel_column_textview, orders);

            ListView lvColumn = (ListView) view.findViewById(R.id.orderList);
            lvColumn.setAdapter(columnAdapter);

            lvColumn.setOnItemClickListener(new AdapterView.OnItemClickListener()  {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    view.setSelected(true);
                    Log.i(LOG_TAG, "Listview index: " + position + " clicked");
                    //mCallback.onColumnSelected(position);
                }
            });
        }
        else {
            orders.add("No orders");
            Log.i(LOG_TAG, "Order was null");
        }

        return view;
    }
}
