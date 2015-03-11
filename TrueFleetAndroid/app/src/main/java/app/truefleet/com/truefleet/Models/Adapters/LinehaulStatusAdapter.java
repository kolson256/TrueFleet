package app.truefleet.com.truefleet.Models.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import app.truefleet.com.truefleet.Models.LinehaulStatus;
import app.truefleet.com.truefleet.R;

/**
 * Created by Chris Lacy on 3/10/2015.
 */
public class LinehaulStatusAdapter  extends ArrayAdapter<LinehaulStatus>  {

    public LinehaulStatusAdapter(Context context, List<LinehaulStatus> linehaulStatuses) {
        super(context, 0, linehaulStatuses);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LinehaulStatus linehaulStatus = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.freight_item,
                    parent, false);
        }
        return null;
    }
}
