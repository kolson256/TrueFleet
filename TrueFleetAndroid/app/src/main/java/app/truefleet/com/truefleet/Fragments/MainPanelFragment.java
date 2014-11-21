package app.truefleet.com.truefleet.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.truefleet.com.truefleet.R;

/**
 * Created by Chris Lacy on 11/21/2014.
 */
public class MainPanelFragment extends Fragment {
    private final String LOG_TAG = MainPanelFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_panel, container, false);

        return view;
    }
    public void updateOrderView(int position) {
        Log.i(LOG_TAG, "Main panel fragment received update order from item selected");
    }
}
