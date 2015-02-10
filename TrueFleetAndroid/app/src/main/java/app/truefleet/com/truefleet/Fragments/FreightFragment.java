package app.truefleet.com.truefleet.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import app.truefleet.com.truefleet.Models.Adapters.FreightAdapter;
import app.truefleet.com.truefleet.Models.Freight;
import app.truefleet.com.truefleet.R;

/**
 * Created by Chris Lacy on 2/8/2015.
 */
public class FreightFragment extends Fragment  {

    private final String LOG_TAG = FreightFragment.class.getSimpleName();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_freight, container, false);

        ArrayList<Freight> arrayOfFreights = new ArrayList<Freight>();

        arrayOfFreights.add(Freight.load(Freight.class, 1));
        arrayOfFreights.add(Freight.load(Freight.class, 2));
        arrayOfFreights.add(Freight.load(Freight.class, 3));


        FreightAdapter adapter = new FreightAdapter(getActivity(), arrayOfFreights);

        ListView listView = (ListView) view.findViewById(R.id.listview_freights);
        listView.setAdapter(adapter);

        return view;
    }
}
