package app.truefleet.com.truefleet.Models.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import app.truefleet.com.truefleet.Models.Freight;
import app.truefleet.com.truefleet.R;

/**
 * Created by Chris Lacy on 2/9/2015.
 */
public class FreightAdapter extends ArrayAdapter<Freight> {

    public FreightAdapter(Context context, ArrayList<Freight> freights) {
        super(context, 0, freights);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Freight freight = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.freight_item,
                    parent, false);
        }

        TextView description = (TextView) convertView.findViewById(R.id.freight_description);
        TextView quantity = (TextView) convertView.findViewById(R.id.freight_quantity);
        TextView weight = (TextView) convertView.findViewById(R.id.freight_weight);
        TextView notes = (TextView)convertView.findViewById(R.id.freight_notes);
        TextView seal = (TextView)convertView.findViewById(R.id.freight_seal);

        description.setText(freight.description);
        quantity.setText(String.valueOf(freight.quantity));
        weight.setText(String.valueOf(freight.weight));
        notes.setText(freight.notes);
        seal.setText(freight.seal);

        return convertView;
    }
}
