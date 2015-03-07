package app.truefleet.com.truefleet.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import app.truefleet.com.truefleet.Models.Account;
import app.truefleet.com.truefleet.Models.ActiveOrderManager;
import app.truefleet.com.truefleet.R;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Chris Lacy on 11/21/2014.
 */
public class DeliveryFragment extends Fragment implements Updater {
    private final String LOG_TAG = DeliveryFragment.class.getSimpleName();
    ActiveOrderManager activeOrderManager;

    @InjectView(R.id.delivery_name)
    TextView mDeliveryName;
    @InjectView(R.id.delivery_address)
    TextView mDeliveryAddress;

    @InjectView(R.id.delivery_phone)
    TextView mDeliveryPhone;
    @InjectView(R.id.delivery_notes)
    TextView mDeliveryNotes;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delivery, container, false);

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
}

