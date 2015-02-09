package app.truefleet.com.truefleet.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import app.truefleet.com.truefleet.Models.Adapters.LinehaulAdapter;
import app.truefleet.com.truefleet.Models.Linehaul;
import app.truefleet.com.truefleet.Models.Order;
import app.truefleet.com.truefleet.R;

/**
 * Created by Chris Lacy on 2/9/2015.
 */
public class OrderDetailsFragmentNew extends Fragment {
    private final String LOG_TAG = OrderDetailsFragmentNew.class.getSimpleName();

    TextView orderid;
    TextView receiptDate;
    TextView orderNotes;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_new, container, false);

        orderid = (TextView)view.findViewById(R.id.order_id);
        receiptDate = (TextView)view.findViewById(R.id.order_receipt_date);
        orderNotes = (TextView)view.findViewById(R.id.order_notes);
        //update order info w temp data in DB
        Order o = Order.load(Order.class, 1);

        orderid.setText(String.valueOf(o.orderid));
        receiptDate.setText(o.receiptDate.toString());
        orderNotes.setText(o.notes);

        //Update linehaul w temp data in DB
        ArrayList<Linehaul> arrayOfLinehauls = new ArrayList<Linehaul>();

        arrayOfLinehauls.add(Linehaul.load(Linehaul.class, 1));
        arrayOfLinehauls.add(Linehaul.load(Linehaul.class, 2));
        arrayOfLinehauls.add(Linehaul.load(Linehaul.class, 3));
        arrayOfLinehauls.add(Linehaul.load(Linehaul.class, 3));
        arrayOfLinehauls.add(Linehaul.load(Linehaul.class, 3));
        arrayOfLinehauls.add(Linehaul.load(Linehaul.class, 3));
        arrayOfLinehauls.add(Linehaul.load(Linehaul.class, 3));

        LinehaulAdapter adapter= new LinehaulAdapter(getActivity(), arrayOfLinehauls);

        ListView listView = (ListView) view.findViewById(R.id.listview_linehauls);
        listView.setAdapter(adapter);


        return view;
    }
}