package app.truefleet.com.truefleet.Models.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import app.truefleet.com.truefleet.Models.PanelItem;
import app.truefleet.com.truefleet.R;

/**
 * Created by Chris Lacy on 3/2/2015.
 */
public class PanelAdapter extends ArrayAdapter<PanelItem> {

    public PanelAdapter(Context context, List<PanelItem> panelItems) {
        super(context, 0, panelItems);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        PanelItem panelItem = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listlayout,
                    parent, false);
        }
        ImageView imgView = (ImageView) convertView.findViewById(R.id.panel_icon);
        TextView titleView = (TextView)  convertView.findViewById(R.id.panel_title);
        TextView counterView = (TextView)convertView.findViewById(R.id.panel_counter);

        if(panelItem.isVisible()) {
            counterView.setVisibility(View.VISIBLE);
        }
        else {
            counterView.setVisibility(View.INVISIBLE);
        }

        imgView.setImageResource(panelItem.getIcon());
        titleView.setText(panelItem.getTitle());
        counterView.setText(panelItem.getCounter());


        return convertView;

    }
}
