package app.truefleet.com.truefleet.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import app.truefleet.com.truefleet.Models.ActiveOrderManager;
import app.truefleet.com.truefleet.Models.Adapters.FreightAdapter;
import app.truefleet.com.truefleet.Models.Freight;
import app.truefleet.com.truefleet.R;

/**
 * Created by Chris Lacy on 2/8/2015.
 */
public class FreightFragment extends Fragment  {

    private final String LOG_TAG = FreightFragment.class.getSimpleName();
    private ActiveOrderManager activeOrderManager;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_freight, container, false);

        activeOrderManager = ActiveOrderManager.getInstance();
        updateUI(view);

        return view;
    }

    public void updateUI(View view) {
        List<Freight> freights = activeOrderManager.getActiveFreights();

        if (freights.size() == 0) {
            Freight f = new Freight();
            f.description="No Freights";
            freights.add(f);
        }

        FreightAdapter adapter = new FreightAdapter(getActivity(), freights);

        ListView listView = (ListView) view.findViewById(R.id.listview_freights);
        listView.setAdapter(adapter);
    }
}
