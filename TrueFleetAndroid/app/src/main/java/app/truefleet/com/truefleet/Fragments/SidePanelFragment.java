package app.truefleet.com.truefleet.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

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
        ArrayList<String> columns = new ArrayList<>();
        columns.add("Order details");
        columns.add("Pickup");
        columns.add("Delivery");

        ArrayAdapter<String> columnAdapter = new ArrayAdapter<String>
                (getActivity(), R.layout.side_panel_column, R.id.side_panel_column_textview, columns);

        ListView lvColumn = (ListView) view.findViewById(R.id.listview_columns);
        lvColumn.setAdapter(columnAdapter);

        lvColumn.setOnItemClickListener(new AdapterView.OnItemClickListener()  {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
               view.setSelected(true);
                Log.i(LOG_TAG, "Listview index: " + position + " clicked");
                mCallback.onColumnSelected(position);
            }
        });

        lvColumn.setItemChecked(0, true);
        lvColumn.setSelection(0);


        return view;
    }

}
