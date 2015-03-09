package app.truefleet.com.truefleet.Tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.Log;

import com.activeandroid.Model;

import java.util.List;

import javax.inject.Inject;

import app.truefleet.com.truefleet.Models.Linehaul;
import app.truefleet.com.truefleet.Models.Order;
import app.truefleet.com.truefleet.Tasks.Requests.LinehaulsRequest;
import app.truefleet.com.truefleet.TrueFleetApp;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Chris Lacy on 3/5/2015.
 */
public class OrderService extends AsyncTask<String, Void, String[]> {
    private final int orderid;
    private final int routeid;
    private final String assignedUser;

    private final String LOG_TAG = OrderService.class.getSimpleName();

    @Inject ApiService apiService;
    @Inject
    Context context;

    public OrderService(String assignedUser, int orderid, int routeid)  {
        this.assignedUser = assignedUser;
        this.orderid = orderid;
        this.routeid = routeid;
        TrueFleetApp.inject(this);

    }
    @Override
    protected String[] doInBackground(String... params) {
       Log.i(LOG_TAG, "Getting orders for orderid: " + orderid + " routeid: " + routeid + " user: " + assignedUser);

       Order order = getOrder(orderid);

        if (order !=null) {
            order.assignedUser = assignedUser;
            order.save();
            getLinehauls(order);

        }
        //Send notice of new order in DB
     //   Intent data = new Intent("fragmentupdater");
      //  context.sendBroadcast(data);

        return null;
    }
    private void getLinehauls(Order order) {
        final Order o = order;
        try {
            apiService.getLinehauls(new LinehaulsRequest(routeid, orderid), new RestCallback<List<Linehaul>>() {
                @Override
                public void success(List<Linehaul> linehauls, Response response) {
                    LinehaulsService linehaulsService = new LinehaulsService(routeid, orderid, o, linehauls);
                    linehaulsService.execute();
                }

                @Override
                public void failure(RestError error) {
                    Log.i(LOG_TAG, "Error: " + error.getStrMessage());
                }
            });
        } catch (Exception e) {}
    }


    private void trySave(Model model) {
        if (model == null)
            return;

        model.save();
    }



    @Nullable
    private Order getOrder(int orderid) {
        try {
            return apiService.getOrder(orderid);
        } catch (RetrofitError error) {
        Log.e(LOG_TAG, error.toString());
        return null;
    }
    }


}
