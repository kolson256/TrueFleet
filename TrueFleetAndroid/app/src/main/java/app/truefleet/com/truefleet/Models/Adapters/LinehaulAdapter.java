package app.truefleet.com.truefleet.Models.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import app.truefleet.com.truefleet.Models.Linehaul;
import app.truefleet.com.truefleet.R;

/**
 * Created by Chris Lacy on 2/9/2015.
 */
public class LinehaulAdapter  extends ArrayAdapter<Linehaul> {

    public LinehaulAdapter(Context context, List<Linehaul> linehauls) {
        super(context, 0, linehauls);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Linehaul linehaul = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_linehaul,
                    parent, false);
        }

        TextView shipDate = (TextView) convertView.findViewById(R.id.linehaul_ship_date);
        TextView startDate = (TextView) convertView.findViewById(R.id.linehaul_pickup_start_date);
        TextView endDate = (TextView) convertView.findViewById(R.id.linehaul_pickup_end_date);
        TextView deadline = (TextView)convertView.findViewById(R.id.linehaul_delivery_deadline);
        TextView notes = (TextView)convertView.findViewById(R.id.linehaul_delivery_notes);
        TextView status = (TextView)convertView.findViewById(R.id.linehaul_status);

        shipDate.setText(linehaul.convertDateTime(linehaul.shipdate).toString());
        startDate.setText(linehaul.convertDateTime(linehaul.pickupStartDate).toString());
        endDate.setText(linehaul.convertDateTime(linehaul.pickupEndDate).toString());
        deadline.setText(linehaul.convertDateTime(linehaul.deliveryDeadline).toString());
        notes.setText(linehaul.notes);
        status.setText(linehaul.linehaulStatus.status);

        return convertView;
    }
}
