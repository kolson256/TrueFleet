package app.truefleet.com.truefleet.Activitieis;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.sql.Date;

import app.truefleet.com.truefleet.Models.Account;
import app.truefleet.com.truefleet.Models.Contact;
import app.truefleet.com.truefleet.Models.Containers;
import app.truefleet.com.truefleet.Models.Freight;
import app.truefleet.com.truefleet.Models.IMTOrder;
import app.truefleet.com.truefleet.Models.Linehaul;
import app.truefleet.com.truefleet.Models.Order;
import app.truefleet.com.truefleet.R;
import app.truefleet.com.truefleet.Resources.GcmHelper;
import app.truefleet.com.truefleet.Resources.LoginManager;

public class HomeActivity extends Activity {

    private static final String LOG_TAG = HomeActivity.class.getSimpleName();
    LoginManager loginManager;

    Context context;
    Activity activity;
    HomeFragment homeFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        activity = this;
        context = getApplicationContext();
        loginManager = new LoginManager(context);
        setTitle("Home");
        getActionBar().setIcon(R.drawable.orders);
        GcmHelper gcmHelper = new GcmHelper(getApplicationContext());
        gcmHelper.gcmSetup(this);
        //addFakeOrder();
        if (savedInstanceState == null) {

            getFragmentManager().beginTransaction()
                    .add(R.id.container, new HomeFragment())
                    .commit();
        }
    }

    protected void onResume() {
        super.onResume();

    }

    public static void showOkDialogWithText(Context context, String messageText)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(messageText);
        builder.setCancelable(true);
        builder.setPositiveButton("OK", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void logout(View view) {
        loginManager = new LoginManager(context);
         loginManager.logout();
    }
    public void orders(View view) {
        showOrders();
    }
    public void showOrders() {
        IMTOrder order = IMTOrder.getInstance();

        if (order.getOrderType() != null) {
            Intent i = new Intent(context, OrderActivitys.class);
            startActivity(i);
        }
    }
    public void addFakeOrder() {
        Account aOrder = new Account("name", "street", "city", "state", "60613",
                "United States", "pickup", "notes", "phone", "fax");

        aOrder.save();

        Account ashipper = new Account("name", "street", "city", "state", "60613",
                "United States", "shipper", "notes", "phone", "fax");

        Account aterminal = new Account("name", "street", "city", "state", "60613",
                "United States", "aterminal", "notes", "phone", "fax");

        Account areceiver = new Account("name", "street", "city", "state", "60613",
                "United States", "areceiver", "notes", "phone", "fax");

        ashipper.save();
        aterminal.save();
        areceiver.save();

        Contact cOrder = new Contact(12345, "firstname", "lastname", "suffix", "mailingStreet",
                "mailingState", "mailingCity", "60613", "United States", "303-123-1234",
                "303-123-2345", "fax", "notes", aOrder);

        cOrder.save();

        Contact cShipper = new Contact(12345, "firstname", "lastname", "suffix", "mailingStreet",
                "mailingState", "mailingCity", "60613", "United States", "303-123-1234",
                "303-123-2345", "fax", "notes", ashipper);

        cOrder.save();

        Contact cTerminal = new Contact(12345, "firstname", "lastname", "suffix", "mailingStreet",
                "mailingState", "mailingCity", "60613", "United States", "303-123-1234",
                "303-123-2345", "fax", "notes", aterminal);

        cOrder.save();

        Contact cReceiver = new Contact(12345, "firstname", "lastname", "suffix", "mailingStreet",
                "mailingState", "mailingCity", "60613", "United States", "303-123-1234",
                "303-123-2345", "fax", "notes", areceiver);

        cOrder.save();
        int orderid = 1111;
        Order o = new Order(aOrder, cOrder, orderid, "2222", "order notes", new Date(123456),
                "orderType");
        o.save();
        Linehaul lh = new Linehaul(orderid, o, 888, ashipper, aterminal, areceiver, new Date(12345),
                new Date(123456), new Date(123457), new Date(123458));

        lh.save();

        Containers c = new Containers("container description", 5, 50, 40, 30, 100, "container notes");

        c.save();

        Freight f = new Freight(c, lh, "freight description", 5, 50, "seal", "freight notes");

        f.save();
        Freight f2 = new Freight(c, lh, "freight2 description", 6, 55, "seal2", "freight2 notes");
        f2.save();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
