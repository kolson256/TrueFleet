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
public class DeliveryFragment extends Fragment implements Updater {
    private final String LOG_TAG = DeliveryFragment.class.getSimpleName();
    ActiveOrderManager activeOrderManager;
    private LinehaulType linehaulSelection;

    @InjectView(R.id.delivery_name)
    TextView mDeliveryName;
    @InjectView(R.id.delivery_address)
    TextView mDeliveryAddress;

    @InjectView(R.id.delivery_phone)
    TextView mDeliveryPhone;
    @InjectView(R.id.delivery_notes)
    TextView mDeliveryNotes;
    @Inject
    Bus bus;
    View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_delivery, container, false);
        TrueFleetApp.inject(this);
        activeOrderManager = ActiveOrderManager.getInstance();
        activeOrderManager.setContentUpdater(this);
        ButterKnife.inject(this, view);

        updateUI();

        return view;
    }

    public void updateUI() {
        Account account = activeOrderManager.getActiveDeliveryAccount();

        if (account == null) {
            Log.i(LOG_TAG, "Account was null");
            emptyDelivery();
            return;
        }
        mDeliveryName.setText(account.name);
        mDeliveryAddress.setText(account.mailingstreet + "\n" +
                account.mailingcity + ", " + account.mailingstate + " " + account.mailingpostalcode);
        mDeliveryPhone.setText(account.phone);
        mDeliveryNotes.setText(account.notes);
    }

    private void emptyDelivery() {
        mDeliveryName.setText("No Delivery");
        mDeliveryAddress.setText("");
        mDeliveryPhone.setText("");
        mDeliveryNotes.setText("");
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
        view.setVisibility(View.INVISIBLE);
    }
    @Subscribe
    public void yesLinehauls(YesLinehaulsEvent event) {
        view.setVisibility(View.VISIBLE);
    }
}

