package app.truefleet.com.truefleet.Fragments;


import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import app.truefleet.com.truefleet.Models.IMTOrder;
import app.truefleet.com.truefleet.R;
import app.truefleet.com.truefleet.Tasks.SendStatusTask;

/**
 * Created by Chris Lacy on 11/21/2014.
 */
public class OrderDetailsFragment extends Fragment {
    private final String LOG_TAG = MainPanelFragment.class.getSimpleName();
    private TextView railLine=null;
    private TextView pickupContact = null;
    private TextView dropoffContact = null;
    private TextView receiptTime = null;
    private TextView orderType = null;
    private TextView startDeliveryTime = null;
    private TextView endDeliveryTime = null;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment_order_detail, container, false);

        receiptTime = (TextView)view.findViewById(R.id.receiptTimestamp);
        railLine = (TextView)view.findViewById(R.id.txtRailLine);
        pickupContact = (TextView)view.findViewById(R.id.txtPickupContact);
        dropoffContact = (TextView)view.findViewById(R.id.txtDropOffContact);
        orderType = (TextView)view.findViewById(R.id.orderType);
        startDeliveryTime = (TextView)view.findViewById(R.id.startDelivery);
        endDeliveryTime = (TextView)view.findViewById(R.id.endDelivery);
        updateUI();
        return view;
    }
    public void updateOrderView(int position) {
        Log.i(LOG_TAG, "Main panel fragment received update order from item selected");
    }

    public void updateUI() {
        IMTOrder order = IMTOrder.getInstance();

        if (order.getOrderType() != null) {

            System.out.println(order.getReceiptTimestamp());
            Date d = new Date(order.getReceiptTimestamp());
            SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
            receiptTime.setText(format.format(d));
            railLine.setText(order.getRailLine());
            pickupContact.setText(order.getPickupContact());
            dropoffContact.setText(order.getDropoffContact());
            orderType.setText(order.getOrderType());
            startDeliveryTime.setText(order.getDeliveryWindowOpen());
            endDeliveryTime.setText(order.getDeliveryWindowClose());
        }
    }

    public void statusUpdate(View view) {
        Log.i(LOG_TAG, "Status button clicked");
        SendStatusTask sendStatusTask = new SendStatusTask(getActivity());
        sendStatusTask.execute("ACCEPT");
    }


}

