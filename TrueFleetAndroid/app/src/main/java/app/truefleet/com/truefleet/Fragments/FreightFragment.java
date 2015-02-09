package app.truefleet.com.truefleet.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.truefleet.com.truefleet.R;

/**
 * Created by Chris Lacy on 2/8/2015.
 */
public class FreightFragment extends Fragment  {

    private final String LOG_TAG = FreightFragment.class.getSimpleName();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_freight, container, false);

        return view;
    }
}
