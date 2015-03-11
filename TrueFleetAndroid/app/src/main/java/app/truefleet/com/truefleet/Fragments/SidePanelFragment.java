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

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import javax.inject.Inject;

import app.truefleet.com.truefleet.Activitieis.Events.FreightCountChangedEvent;
import app.truefleet.com.truefleet.Activitieis.Events.LinehaulSelectionEvent;
import app.truefleet.com.truefleet.Models.ActiveOrderManager;
import app.truefleet.com.truefleet.Models.Adapters.PanelAdapter;
import app.truefleet.com.truefleet.Models.LinehaulType;
import app.truefleet.com.truefleet.Models.PanelItem;
import app.truefleet.com.truefleet.R;
import app.truefleet.com.truefleet.TrueFleetApp;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Chris Lacy on 11/21/2014.
 */
public class SidePanelFragment extends Fragment {
    private final String LOG_TAG = SidePanelFragment.class.getSimpleName();

    @InjectView(R.id.listview_columns)
    ListView listView;
    OnColumnSelectedListener mCallback;
    ArrayList<PanelItem> panelItems;
    PanelAdapter adapter;
    ActiveOrderManager aom;
    private LinehaulType linehaulSelection;
    private boolean clickable;

    @Inject
    Bus bus;
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
        panelItems = new ArrayList<>();
        clickable = true;

        ButterKnife.inject(this, view);
        TrueFleetApp.inject(this);
        aom = ActiveOrderManager.getInstance();

        int count = aom.getSelectedFreights().size();


        panelItems.add(new PanelItem(R.drawable.ic_pickup, "Pickup", "", false));
        panelItems.add(new PanelItem(R.drawable.ic_delivery, "Delivery",  "", false));
        panelItems.add(new PanelItem(R.drawable.ic_freights, "Freights" , String.valueOf(count), true));
        panelItems.add(new PanelItem(R.drawable.ic_container, "Container" , "", false));

        ArrayList<String> columns = new ArrayList<>();

        columns.add("Pickup");
        columns.add("Delivery");
        columns.add("Freights");
        columns.add("Container");
        adapter = new PanelAdapter(getActivity(), panelItems);
        adapter.setNotifyOnChange(true);


        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (clickable) {
                    view.setSelected(true);
                    Log.i(LOG_TAG, "Listview index: " + position + " clicked");
                    mCallback.onColumnSelected(position);
                }
            }
        });

        listView.setItemChecked(0, true);
        listView.setSelection(0);


        return view;
    }
    private void  updateUI() {
        int size = aom.getSelectedFreights().size();
        panelItems.get(2).setCounter(String.valueOf(aom.getSelectedFreights().size()));
        adapter.notifyDataSetChanged();
    }
    @Override
    public void onResume() {
        updateUI();
        super.onResume();

        bus.register(this);
    }
    @Override
    public void onPause() {
        super.onPause();
        bus.unregister(this);
    }

    @Subscribe
    public void freightItemCountChanged(FreightCountChangedEvent event) {
        Log.i(LOG_TAG, "Received freight item count changed event");
        panelItems.get(2).setCounter(String.valueOf(event.getCount()));
        adapter.notifyDataSetChanged();
    }
    @Subscribe
    public void linehaulTypeSeelction(LinehaulSelectionEvent event) {
        linehaulSelection = event.getSelectionType();
        updateUI();
    }

}
