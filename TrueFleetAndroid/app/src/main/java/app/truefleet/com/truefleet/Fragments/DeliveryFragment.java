package app.truefleet.com.truefleet.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.truefleet.com.truefleet.R;

/**
 * Created by Chris Lacy on 11/21/2014.
 */
public class DeliveryFragment extends Fragment {
    private final String LOG_TAG = DeliveryFragment.class.getSimpleName();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delivery, container, false);

        return view;
    }

}
