package app.truefleet.com.truefleet.Tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.activeandroid.Model;

import javax.inject.Inject;

import app.truefleet.com.truefleet.Models.ActiveOrderManager;
import app.truefleet.com.truefleet.Models.Linehaul;
import app.truefleet.com.truefleet.Models.LinehaulStatus;
import app.truefleet.com.truefleet.Resources.MainThreadBus;
import app.truefleet.com.truefleet.TrueFleetApp;

/**
 * Created by Chris Lacy on 3/10/2015.
 */
public class PostStatus extends AsyncTask<String, Void, String[]> {
    private final String LOG_TAG = PostStatus.class.getSimpleName();
    @Inject
    ApiService apiService;

    private LinehaulStatus updateLinehaulStatus;
    private Linehaul linehaul;

    public PostStatus(Linehaul linehaul, LinehaulStatus updateLinehaulStatus)  {
        TrueFleetApp.inject(this);
        this.updateLinehaulStatus = updateLinehaulStatus;
        this.linehaul = linehaul;
    }


    @Override
    protected String[] doInBackground(String... params) {
        //todo: upd status when imp on server

        if (updateLinehaulStatus.status.equalsIgnoreCase("ACCEPT"))
        {
            Log.e(LOG_TAG, "Updating status to accept");
            linehaul.linehaulStatus = Model.load(LinehaulStatus.class, 5);
            linehaul.save();
            MainThreadBus bus = new MainThreadBus();
            //bus.post(new LinehaulStatusUpdateEvent(linehaul));


        } else if(updateLinehaulStatus.status.equalsIgnoreCase("Picked up")) {
            linehaul.linehaulStatus = Model.load(LinehaulStatus.class, 4);
            linehaul.save();
        }
     else if(updateLinehaulStatus.status.equalsIgnoreCase("Completed")) {
        linehaul.linehaulStatus = Model.load(LinehaulStatus.class, 3);
        linehaul.save();
    }
        else if(updateLinehaulStatus.status.equalsIgnoreCase("Rejected")) {
            linehaul.linehaulStatus = Model.load(LinehaulStatus.class, 2);
            linehaul.save();
        }
        return null;
    }
    protected void onPostExecute(String[] result) {
        ActiveOrderManager aom = ActiveOrderManager.getInstance();
        aom.linehaulStatusChanged(linehaul.linehaulStatus);
    }
}
