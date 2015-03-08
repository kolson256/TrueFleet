package app.truefleet.com.truefleet.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.List;

import javax.inject.Inject;

import app.truefleet.com.truefleet.Activitieis.Events.LinehaulSelectionEvent;
import app.truefleet.com.truefleet.Models.ActiveOrderManager;
import app.truefleet.com.truefleet.Models.Adapters.FreightAdapter;
import app.truefleet.com.truefleet.Models.Freight;
import app.truefleet.com.truefleet.Models.LinehaulType;
import app.truefleet.com.truefleet.R;
import app.truefleet.com.truefleet.TrueFleetApp;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Chris Lacy on 2/8/2015.
 */
public class FreightFragment extends Fragment implements Updater {
    private final String LOG_TAG = FreightFragment.class.getSimpleName();
    private ActiveOrderManager activeOrderManager;
    @InjectView(R.id.listview_freights)
    ListView listView;
    private LinehaulType linehaulSelection;

    @Inject
    Bus bus;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_freight, container, false);
        TrueFleetApp.inject(this);
        ButterKnife.inject(this, view);

        activeOrderManager = ActiveOrderManager.getInstance();
        activeOrderManager.setContentUpdater(this);
        updateUI();

        return view;
    }

    public void updateUI() {
        List<Freight> freights = activeOrderManager.getSelectedFreights();

        if (freights.size() == 0) {
            Freight f = new Freight();
            f.description="No Freights";
            freights.add(f);
        }

        FreightAdapter adapter = new FreightAdapter(getActivity(), freights);

        listView.setAdapter(adapter);
    }
    @Override
    public void onResume() {
        super.onResume();

        bus.register(this);
    }
    @Override
    public void onPause() {
        super.onPause();
        bus.unregister(this);
    }
    @Subscribe
    public void linehaulTypeSeelction(LinehaulSelectionEvent event) {
        linehaulSelection = event.getSelectionType();
        updateUI();
    }
}
