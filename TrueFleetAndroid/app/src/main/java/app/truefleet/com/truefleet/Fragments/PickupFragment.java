package app.truefleet.com.truefleet.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import app.truefleet.com.truefleet.Activitieis.OnSwipeTouchListener;
import app.truefleet.com.truefleet.Models.Account;
import app.truefleet.com.truefleet.Models.ActiveOrderManager;
import app.truefleet.com.truefleet.R;
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
    @InjectView(R.id.pickup_city_state_zip)
    TextView mPickupCityStateZip;
    @InjectView(R.id.pickup_phone)
    TextView mPickupPhone;
    @InjectView(R.id.pickup_notes)
    TextView mPickupNotes;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pickup, container, false);
        activeOrderManager = ActiveOrderManager.getInstance();
        activeOrderManager.setContentUpdater(this);
        ButterKnife.inject(this, view);

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
        mPickupAddress.setText(account.mailingstreet);
        mPickupCityStateZip.setText(account.mailingcity + ", " + account.mailingstate +" " + account.mailingpostalcode);
        mPickupPhone.setText(account.phone);
        mPickupNotes.setText(account.notes);

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}


