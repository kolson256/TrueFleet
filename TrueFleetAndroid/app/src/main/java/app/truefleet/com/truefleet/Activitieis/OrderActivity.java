package app.truefleet.com.truefleet.Activitieis;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import app.truefleet.com.truefleet.Fragments.MainPanelFragment;
import app.truefleet.com.truefleet.Fragments.SidePanelFragment;
import app.truefleet.com.truefleet.R;

public class OrderActivity extends FragmentActivity implements SidePanelFragment.OnColumnSelectedListener {

    private final String LOG_TAG = OrderActivity.class.getSimpleName();

    public void onColumnSelected(int position) {
        System.out.println("Order activity receieved item selected");
        Log.i(LOG_TAG, "Order activity receieved item selected");
        MainPanelFragment mainPanelFragment = (MainPanelFragment)getSupportFragmentManager().findFragmentById(R.id.main_panel);

        if (mainPanelFragment!= null) {
            mainPanelFragment.updateOrderView(position);
        }
        else {
            System.out.println("MAIN PANEL NULL" );
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);


        int orientation = getResources().getConfiguration().orientation;

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            hideSidePanel();
        }
    }

    @Override
    public View onCreateView(String name, @NonNull Context context, @NonNull AttributeSet attrs) {

        SidePanelFragment sidePanelFragment = (SidePanelFragment)
                getSupportFragmentManager().findFragmentById(R.id.side_panel);
        //lvColumn.setOnItemClickListener();
        return super.onCreateView(name, context, attrs);

    }

    private void hideSidePanel() {
        View sidePane = findViewById(R.id.side_panel);
        if (sidePane.getVisibility() == View.VISIBLE) {
            sidePane.setVisibility(View.GONE);
        }
    }

}
