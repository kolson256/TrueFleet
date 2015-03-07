package app.truefleet.com.truefleet.mock;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.util.Collections;

import retrofit.client.Client;
import retrofit.client.Request;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

/**
 * Created by Chris Lacy on 2/28/2015.
 */
public class MockClient implements Client {

    private final String LOG_TAG = MockClient.class.getSimpleName();

    @Override
    public Response execute(Request request) throws IOException {
        Uri uri = Uri.parse(request.getUrl());

        Log.d(LOG_TAG, "fetching uri: " + uri.toString());

        String response = "";

        if (uri.getPath().equals("/Login")) {
        response  = "{\"apiVersion\":\"test\",\"tenantId\":\"ade7e24a-ecc4-48f7-ad1c-943b9da2385f\",\"authenticationToken\":\"1\"}";
        } else if (uri.getPath().equals("/GcmRegistration")) {
        response = "Gcm registration json response";
        } else if (uri.getPath().startsWith("/0.1/order/")) {
            response = "{\"id\": 3,\"orderid\": \"12345\",\"externalid\": \"54321\",\"notes\": \"order notes\",\"receiptdate\": 976987273000}";
        } else if (uri.getPath().startsWith("/0.1/account/")) {
            response = "{\"id\":1234,\"name\":\"Account 2\",\"mailingstreet\":\"233 South Wacker Drive\",\"mailingcity\":\"Chicago\",\"mailingstate\":\"IL\",\"mailingpostalcode\":\"60606\",\"mailingcountry\":\"USA\",\"types\":\"Broker\",\"notes\":\"Account notes.\",\"phone\":\"312-555-1212\",\"fax\":\"312-555=2121\"}";
        } else if (uri.getPath().startsWith("/0.1/container/")) {
            response = "{\"id\":0,\"description\":\"UMAX432158\",\"volume\":2000,\"length\":55,\"width\":20,\"height\":55,\"weight\":300,\"notes\":\"Container 2\"}";
        } else if (uri.getPath().startsWith("/0.1/route/")) {
            response = "{\"id\":0,\"notes\":\"Route 2\"}";
        } else if (uri.getPath().startsWith("/0.1/linehaul/")) {
            response = "{\"id\":0,\"notes\":\"Linehaul 2\",\"shipdate\":1424412000000,\"pickupstartdate\":1424694600000,\"pickupenddate\":1424705400000,\"deliverydeadline\":1424986200000}";

        } else if (uri.getPath().startsWith("/0.1/freight/")) {
            response = "{\"id\":0,\"description\":\"This is 2 Test\",\"quantity\":3,\"weight\":20000,\"seal\":\"Seal ID 2\",\"notes\":\"Freight Test 2\"}";
        } else if (uri.getPath().startsWith("/0.1/contact/")) {
           response = "{\"id\":0,\"firstname\":\"First\",\"lastname\":\"User One\",\"suffix\":null,\"mailingstreet\":\"233 South Wacker Drive\",\"mailingcity\":\"Chicago\",\"mailingstate\":\"IL\",\"mailingpostalcode\":\"60606\",\"mailingcountry\":null,\"phone\":null,\"mobilephone\":null,\"fax\":null,\"notes\":null,\"id\":0}";

        }
        else if (uri.getPath().startsWith("/0.1/linehauls/")) {
            response = "[{\"id\":0,\"notes\":\"Linehaul 1\",\"shipdate\":1424412000000,\"pickupstartdate\":1424694600000,\"pickupenddate\":1424705400000,\"deliverydeadline\":1424986200000}," +
                    "{\"id\":1,\"notes\":\"Linehaul 2\",\"shipdate\":1424412000000,\"pickupstartdate\":1424694600000,\"pickupenddate\":1424705400000,\"deliverydeadline\":1424986200000}]";
        } else if(uri.getPath().startsWith("/0.1/freights/")) {
            response = "[{\"id\":0,\"description\":\"This is 1 Test\",\"quantity\":2,\"weight\":10000,\"seal\":\"Seal ID 1\",\"notes\":\"Freight Test 1\"}," +
                    "{\"id\":1,\"description\":\"This is 2 Test\",\"quantity\":3,\"weight\":20000,\"seal\":\"Seal ID 2\",\"notes\":\"Freight Test 2\"}]";
        } else if (uri.getPath().startsWith("/0.1/contacts/")) {
            response = "[{\"id\":0,\"firstname\":\"First\",\"lastname\":\"User One\",\"suffix\":null,\"mailingstreet\":\"12345 S. Chicago ave.\",\"mailingcity\":\"Chicago\",,\"mailingstate\":\"IL\",\"mailingpostalcode\":60613,\"mailingcountry\":null,\"phone\":null,\"mobilephone\":null,\"fax\":null,\"notes\":null,\"id\":0}," +
                    "{\"id\":1,\"firstname\":\"First2\",\"lastname\":\"User two\",\"suffix\":null,\"mailingstreet\":null,\"mailingcity\":null,\"mailingstate\":null,\"mailingpostalcode\":null,\"mailingcountry\":null,\"phone\":null,\"mobilephone\":null,\"fax\":null,\"notes\":null,\"id\":0}";
        }
        else {
        response = "Invalid path json response";
        }

        return new Response(request.getUrl(), 200, "OK", Collections.EMPTY_LIST,
        new TypedByteArray("application/json", response.getBytes()));
        }
        }
