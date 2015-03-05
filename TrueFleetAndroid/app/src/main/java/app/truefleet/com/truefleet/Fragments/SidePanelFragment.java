package app.truefleet.com.truefleet.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import app.truefleet.com.truefleet.Models.Adapters.PanelAdapter;
import app.truefleet.com.truefleet.Models.PanelItem;
import app.truefleet.com.truefleet.R;

/**
 * Created by Chris Lacy on 11/21/2014.
 */
public class SidePanelFragment extends Fragment {
    private final String LOG_TAG = SidePanelFragment.class.getSimpleName();

    OnColumnSelectedListener mCallback;

    public interface OnColumnSelectedListener {
        public void onColumnSelected(int position);
    }

    @Override
    public void onAttach(Activity activity) {

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnColumnSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnColumnSelectedListener");
        }
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_side_panel, container, false);
        ArrayList<PanelItem> panelItems = new ArrayList<>();

        ListView listView = (ListView) view.findViewById(R.id.listview_columns);
        panelItems.add(new PanelItem(R.drawable.ic_pickup, "Pickup", ""));
        panelItems.add(new PanelItem(R.drawable.ic_delivery, "Delivery", ""));
        panelItems.add(new PanelItem(R.drawable.ic_freights, "Freights", ""));
        panelItems.add(new PanelItem(R.drawable.ic_container, "Container", ""));

        ArrayList<String> columns = new ArrayList<>();

        columns.add("Pickup");
        columns.add("Delivery");
        columns.add("Freights");
        columns.add("Container");
        PanelAdapter adapter = new PanelAdapter(getActivity(), panelItems);


        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                view.setSelected(true);
                Log.i(LOG_TAG, "Listview index: " + position + " clicked");
                mCallback.onColumnSelected(position);
            }
        });

        listView.setItemChecked(0, true);
        listView.setSelection(0);


        return view;
    }

}
