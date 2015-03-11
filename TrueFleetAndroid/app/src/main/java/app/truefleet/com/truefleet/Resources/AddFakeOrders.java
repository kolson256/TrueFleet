package app.truefleet.com.truefleet.Resources;

import app.truefleet.com.truefleet.Models.Account;
import app.truefleet.com.truefleet.Models.Contact;
import app.truefleet.com.truefleet.Models.Container;
import app.truefleet.com.truefleet.Models.Freight;
import app.truefleet.com.truefleet.Models.Linehaul;
import app.truefleet.com.truefleet.Models.LinehaulStatus;
import app.truefleet.com.truefleet.Models.Order;

/**
 * Created by Chris Lacy on 3/8/2015.
 */
public class AddFakeOrders {

    public static void addFakeOrders() {
        Account aOrder = new Account(1, "name", "street", "city", "state", "60613",
                "United States", "pickup", "notes", "111-123-6545", "fax");

        aOrder.save();

        Account ashipper = new Account(2, "shipper", "231 S Grand Ave.", "Chicago", "IL", "60613",
                "United States", "shipper", "notes", "303-333-1234", "fax");

        Account aterminal = new Account(3, "terminal", "233 South Wacker Drive", "Chicago", "IL", "60606",
                "United States", "aterminal", "notes terminal", "222-222-2054", "fax");

        Account areceiver = new Account(4, "receiver", "123 West Utopia", "Chicago", "IL", "60613",
                "United States", "areceiver", "notes", "222-222-2054", "fax");

        ashipper.save();
        aterminal.save();
        areceiver.save();

        Contact cOrder = new Contact(1, 1, "firstname", "lastname", "suffix", "mailingStreet",
                "mailingState", "mailingCity", "60613", "United States", "303-123-1234",
                "303-123-2345", "fax", "notes", aOrder);

        cOrder.save();

        Contact cShipper = new Contact(2, 2, "firstname", "lastname", "suffix", "mailingStreet",
                "mailingState", "mailingCity", "60613", "United States", "303-123-1234",
                "303-123-2345", "fax", "notes", ashipper);

        cShipper.save();

        Contact cTerminal = new Contact(3,3 , "firstname", "lastname", "suffix", "mailingStreet",
                "mailingState", "mailingCity", "60613", "United States", "303-123-1234",
                "303-123-2345", "fax", "notes", aterminal);

        cTerminal.save();

        Contact cReceiver = new Contact(4,4, "firstname", "lastname", "suffix", "mailingStreet",
                "mailingState", "mailingCity", "60613", "United States", "303-123-1234",
                "303-123-2345", "fax", "notes", areceiver);

        LinehaulStatus lhStatus = new LinehaulStatus();
        lhStatus.id = 1;
        lhStatus.status = "Unassigned";
        lhStatus.save();

        LinehaulStatus lhStatus2 = new LinehaulStatus();
        lhStatus2.id = 2;
        lhStatus2.status = "Rejected";
        lhStatus2.save();

        LinehaulStatus lhStatus3 = new LinehaulStatus();
        lhStatus3.id = 3;
        lhStatus3.status = "Completed";
        lhStatus3.save();


        LinehaulStatus lhStatus4 = new LinehaulStatus();
        lhStatus4.id = 4;
        lhStatus4.status = "Picked up";
        lhStatus4.futurestatus1 = lhStatus3;
        lhStatus4.save();


        LinehaulStatus lhStatus5 = new LinehaulStatus();
        lhStatus5.id = 5;
        lhStatus5.status = "Accept";
          lhStatus5.futurestatus1 = lhStatus4;
        lhStatus5.save();

        LinehaulStatus lhStatus6 = new LinehaulStatus();
        lhStatus6.id = 6;
        lhStatus6.status = "Assigned";
        lhStatus6.futurestatus1 = lhStatus5;
        lhStatus6.futurestatus2 = lhStatus2;
        lhStatus6.save();



        cReceiver.save();
        addOrder(1, aOrder, cOrder, ashipper, aterminal, areceiver, 1, lhStatus6);
        addOrder(4, aOrder, cOrder, ashipper, aterminal, areceiver, 2, lhStatus6);
    }
    private static void addOrder(int linehaulStart, Account aOrder, Contact cOrder, Account ashipper, Account aterminal, Account areceiver, int orderid,
                          LinehaulStatus lhStatus) {

        Order o = new Order(orderid, aOrder, cOrder, orderid, "2222", "order notes", 976987273000L,
                "orderType", "test");
        o.save();
        Linehaul lh = new Linehaul(linehaulStart, orderid, o, 888, ashipper, aterminal, areceiver,
                "linehaul notes", 976987273001L,
                976987273001L, 976987273001L, 976987273001L, lhStatus);

        lh.save();

        Linehaul lh2 = new Linehaul(linehaulStart+1, orderid, o, 888, ashipper, aterminal, areceiver,
                "linehaul notes2", 976987273001L,
                976987273001L, 976987273001L, 976987273001L, lhStatus);

        lh2.save();

        Linehaul lh3 = new Linehaul(linehaulStart+2, orderid, o, 888, ashipper, aterminal, areceiver,
                "linehaul notes3", 976987273001L,
                976987273001L, 976987273001L, 976987273001L, lhStatus);

        lh3.save();

        Container c = new Container(linehaulStart, "container description", 5, 50, 40, 30, 100, "container notes");

        c.save();

        Freight f = new Freight(linehaulStart+1, c, lh, "freight description", 5, 50, "seal", "freight notes");

        f.save();
        Freight f2 = new Freight(linehaulStart+2, c, lh, "freight2 description", 6, 55, "seal2", "freight2 notes");
        f2.save();

        Freight f3 = new Freight(linehaulStart+3, c, lh, "freight3 description", 6, 55, "seal3", "freight3 notes");
        f3.save();
    }
}
