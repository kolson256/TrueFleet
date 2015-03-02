package app.truefleet.com.truefleet.Models.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import app.truefleet.com.truefleet.Models.Order;
import app.truefleet.com.truefleet.R;

/**
 * Created by Chris Lacy on 3/2/2015.
 */
public class OrderAdapter extends ArrayAdapter<Order> {

     TextView orderNotes;
    TextView receiptDate;

    public OrderAdapter(Context context, List<Order> orders) {
        super(context, 0, orders);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Order order = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_order,
                    parent, false);
        }

        orderNotes = (TextView) convertView.findViewById(R.id.order_notes);
        receiptDate = (TextView) convertView.findViewById(R.id.order_receipt_date);
        orderNotes.setText(order.notes);
        receiptDate.setText(order.receiptDate.toString());

        return convertView;

    }
}
