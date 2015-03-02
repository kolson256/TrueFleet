package app.truefleet.com.truefleet.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import app.truefleet.com.truefleet.Models.ActiveOrderManager;
import app.truefleet.com.truefleet.Models.Containers;
import app.truefleet.com.truefleet.R;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Chris Lacy on 2/8/2015.
 */
public class ContainerFragment extends Fragment implements Updater {

    private final String LOG_TAG = ContainerFragment.class.getSimpleName();
    private ActiveOrderManager activeOrderManager;

    @InjectView(R.id.container_description)
    TextView mContainerDescription;
    @InjectView(R.id.container_volume)
    TextView mContainerVolume;
    @InjectView(R.id.container_length)
    TextView mContainerLength;
    @InjectView(R.id.container_width)
    TextView mContainerWidth;
    @InjectView(R.id.container_height)
    TextView mContainerHeight;
    @InjectView(R.id.container_weight)
    TextView mContainerWeight;
    @InjectView(R.id.container_notes)
    TextView mContainerNotes;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_container, container, false);

        activeOrderManager = ActiveOrderManager.getInstance();
        activeOrderManager.setContentUpdater(this);
        ButterKnife.inject(this, view);

        updateUI();
        return view;
    }

    public void updateUI() {
        Containers container = activeOrderManager.getActiveContainer();

        if (container == null) {
            Log.i(LOG_TAG, "Container was null");
            setNullContainer();
            return;
        }
        mContainerDescription.setText(setValue(container.description, "No container"));
        mContainerVolume.setText(setValue(container.volume, ""));
        mContainerLength.setText(setValue(container.length, ""));
        mContainerWidth.setText(setValue(container.width, ""));
        mContainerHeight.setText(setValue(container.height, ""));
        mContainerWeight.setText(setValue(container.weight, ""));
        mContainerNotes.setText(setValue(container.notes, ""));
    }

    public void setNullContainer() {
        mContainerDescription.setText("No container");
        mContainerVolume.setText("");
        mContainerLength.setText("");
        mContainerWidth.setText("");
        mContainerHeight.setText("");
        mContainerWeight.setText("");
        mContainerNotes.setText("");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    //Returns value to set if not null, else returns default value
    public String setValue(Object valueToSet, String defaultValue) {
        if (valueToSet!= null) {
            return String.valueOf(valueToSet);
        }
        return defaultValue;
    }
}
