package app.truefleet.com.truefleet.Activitieis;

import android.app.Fragment;
import android.content.Intent;
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

import app.truefleet.com.truefleet.Models.IMTOrder;
import app.truefleet.com.truefleet.Models.User;
import app.truefleet.com.truefleet.R;
import app.truefleet.com.truefleet.Resources.LoginManager;

/**
 * Created by Chris Lacy on 11/22/2014.
 */
public class HomeFragment extends Fragment {
    ArrayAdapter<String> columnAdapter;

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LoginManager loginManager = new LoginManager(getActivity().getApplicationContext());
        Intent intent = getActivity().getIntent();

        if (loginManager.checkLogin())
            getActivity().finish();

        User user = loginManager.getUser();
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        TextView tvWelcome = (TextView) rootView.findViewById(R.id.welcome_text);
        LoginManager lm = new LoginManager(getActivity().getApplicationContext());
        tvWelcome.setText("Welcome, " + user.getUsername() + "!");

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
                (getActivity(), R.layout.side_panel_column, R.id.side_panel_column_textview, orders);
        ListView lvColumn = (ListView) rootView.findViewById(R.id.listViewOrders);

        if (order.getOrderType() != null) {
            Log.i("ASDF", "Creating non null order");
            orders.add(order.toString());


            lvColumn.setAdapter(columnAdapter);

            lvColumn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    view.setSelected(true);
                    Log.i("ASDF", "Listview index: " + position + " clicked");
                    //mCallback.onColumnSelected(position);
                }
            });
        } else {
            orders.add("No orders");
            lvColumn.setAdapter(columnAdapter);
            Log.i("ASDF", "Order was null");
        }


    }
    public void updateAdapter() {
        columnAdapter.clear();
        columnAdapter.add(IMTOrder.getInstance().toString());
    }
}
