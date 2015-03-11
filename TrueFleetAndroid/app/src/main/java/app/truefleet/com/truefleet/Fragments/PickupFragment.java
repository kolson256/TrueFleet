package app.truefleet.com.truefleet.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import app.truefleet.com.truefleet.Activitieis.Events.LinehaulSelectionEvent;
import app.truefleet.com.truefleet.Activitieis.Events.NoLinehaulsEvent;
import app.truefleet.com.truefleet.Activitieis.Events.YesLinehaulsEvent;
import app.truefleet.com.truefleet.Activitieis.OnSwipeTouchListener;
import app.truefleet.com.truefleet.Models.Account;
import app.truefleet.com.truefleet.Models.ActiveOrderManager;
import app.truefleet.com.truefleet.Models.LinehaulType;
import app.truefleet.com.truefleet.R;
import app.truefleet.com.truefleet.TrueFleetApp;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Chris Lacy on 11/21/2014.
 */
public class PickupFragment extends Fragment implements Updater {
    private final String LOG_TAG = PickupFragment.class.getSimpleName();
    private ActiveOrderManager activeOrderManager;

    @InjectView(R.id.pickup_name)
    TextView mPickupName;
    @InjectView(R.id.pickup_address)
    TextView mPickupAddress;

    @InjectView(R.id.pickup_phone)
    TextView mPickupPhone;
    @InjectView(R.id.pickup_notes)
    TextView mPickupNotes;
    private LinehaulType linehaulSelection;
    View view;
    @Inject
    Bus bus;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_pickup, container, false);
        activeOrderManager = ActiveOrderManager.getInstance();
        activeOrderManager.setContentUpdater(this);
        ButterKnife.inject(this, view);
        TrueFleetApp.inject(this);
        updateUI();

        container.setOnTouchListener(new OnSwipeTouchListener(getActivity().getApplicationContext()) {
            @Override
            public void onSwipeRight() {
                Log.i(LOG_TAG, "SWIPE RIGHT");
            }

            @Override
            public void onSwipeLeft() {
                Log.i(LOG_TAG, "SWIPE LEFT");
            }
        });

        return view;
    }

    public void updateUI() {
        Account account = activeOrderManager.getActivePickupAccount();

        if (account == null) {
            Log.i(LOG_TAG, "Account was null");
            emptyPickup();
            return;
        }
        mPickupName.setText(account.name);
        mPickupAddress.setText(account.mailingstreet + "\n" +
                        account.mailingcity + ", " + account.mailingstate + " " + account.mailingpostalcode);
        mPickupPhone.setText(account.phone);
        mPickupNotes.setText(account.notes);

    }


    private void emptyPickup() {
        mPickupName.setText("No pickup set");
        mPickupAddress.setText("");
        mPickupPhone.setText("");
        mPickupNotes.setText("");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
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

    @Subscribe
    public void noLinehauls(NoLinehaulsEvent event) {
        clearView();
    }
    @Subscribe
    public void yesLinehauls(YesLinehaulsEvent event) {
        setVisible();
    }
    private void clearView() {

        view.setVisibility(View.INVISIBLE);
    }
    private void setVisible() {
        view.setVisibility(View.VISIBLE);
    }

}


