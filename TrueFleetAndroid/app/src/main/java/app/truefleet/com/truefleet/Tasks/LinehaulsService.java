package app.truefleet.com.truefleet.Tasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.Log;

import com.activeandroid.Model;

import java.util.List;

import javax.inject.Inject;

import app.truefleet.com.truefleet.Models.Account;
import app.truefleet.com.truefleet.Models.Contact;
import app.truefleet.com.truefleet.Models.Container;
import app.truefleet.com.truefleet.Models.Freight;
import app.truefleet.com.truefleet.Models.Linehaul;
import app.truefleet.com.truefleet.Models.Order;
import app.truefleet.com.truefleet.TrueFleetApp;
import retrofit.RetrofitError;

/**
 * Created by Chris Lacy on 3/9/2015.
 */
public class LinehaulsService extends AsyncTask<String, Void, String[]>  {
    @Inject ApiService apiService;
    private final int orderid;
    private final int routeid;
    private final Order order;
    private final List<Linehaul> linehauls;
    private final String LOG_TAG = LinehaulsService.class.getSimpleName();
    @Inject
    Context context;

    public LinehaulsService(int routeid, int orderid, Order order, List<Linehaul> linehauls) {
        TrueFleetApp.inject(this);
        this.routeid = routeid;
        this.orderid = orderid;
        this.order = order;
        this.linehauls = linehauls;
    }
    @Override
    protected String[] doInBackground(String... params) {
        Log.i(LOG_TAG, "Getting linehauls for orderid: " + orderid + " routeid: " + routeid);

        linehaulsHandler(linehauls);
        Intent data = new Intent("fragmentupdater");
          context.sendBroadcast(data);
        return null;
    }
    private void linehaulsHandler(List<Linehaul> linehauls) {

        if (linehauls == null) {
            return;
        }
        for(Linehaul l : linehauls) {
            Account shipper = getAccount(l.shipperid);
            Account terminal = getAccount(l.terminalid);
            Account receiver = getAccount(l.receiverid);

            trySave(shipper);
            trySave(terminal);
            trySave(receiver);

            l.shipper = shipper;
            l.terminal = terminal;
            l.receiver = receiver;

            getContactHandler(terminal);
            getContactHandler(shipper);
            getContactHandler(receiver);
            l.order = order;
            trySave(l);
            freightHandler(l);
        }
    }
    private void freightHandler(Linehaul linehaul) {
        List<Freight> freights = getFreights(linehaul.id);

        if (freights == null) {
            return;
        }
        try {
            for (Freight freight : freights) {
                Container c = getContainer(freight.containerid);
                trySave(c);
                freight.container = c;
                freight.linehaul = linehaul;
                trySave(freight);
            } } catch (NullPointerException npe) {
            Log.e(LOG_TAG, "WebServer returned null container");
        }
    }
    private void trySave(Model model) {
        if (model == null)
            return;

        model.save();
    }
    @Nullable
    private Account getAccount(int accountid) {
        try {
            return apiService.getAccount(accountid);
        } catch (RetrofitError error) {
            Log.e(LOG_TAG, error.toString());
            return null;
        }
    }

    @Nullable
    private Contact getContact(int contactid) {
        try {
            return apiService.getContact(contactid);
        } catch (RetrofitError error) {
            Log.e(LOG_TAG, error.toString());
            return null;
        }
    }
    @Nullable
    private List<Freight> getFreights(int linehaulid) {
        try {
            return apiService.getFreights(linehaulid);
        } catch (RetrofitError error) {
            Log.e(LOG_TAG, error.toString());
        }
        return null;
    }
    @Nullable
    private Container getContainer(int containerid) {
        try {
            return apiService.getContainer(containerid);
        } catch (RetrofitError error) {
            Log.e(LOG_TAG, error.toString());
        }
        return null;
    }
    private void getContactHandler(Account account) {
        try {
            Contact c = getContact(account.id);
            c.account = account;
            trySave(c);
        } catch (NullPointerException npe) {
            Log.e(LOG_TAG, "Null account");
        }
    }

}
