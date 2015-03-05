package app.truefleet.com.truefleet.tests;

import android.test.AndroidTestCase;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Test;

import app.truefleet.com.truefleet.Tasks.RestError;
import app.truefleet.com.truefleet.Tasks.RestErrorTypeAdapter;

/**
 * Created by Chris Lacy on 3/4/2015.
 */
public class RestTypeAdapterTest  extends AndroidTestCase  {
    final String message = "message";
    final String error = "errorMessage";
    String messageType = "{\"message\": " + message + " }";
    String errorMessageType = "{\"errorMessage\": " + error + " }";

    final GsonBuilder gsonBuilder = new GsonBuilder();
    Gson gson;

    @Override
    protected void setUp() throws Exception {
       gsonBuilder.registerTypeAdapter(RestError.class, new RestErrorTypeAdapter());
         gson = gsonBuilder.create();
    }

    @Test
    public void testMessageType() {
        RestError restErrorMessage = gson.fromJson(messageType, RestError.class);
        assertEquals(message, restErrorMessage.getStrMessage());
    }
    @Test
    public void testErrorMessageType() {
        RestError restErrorMessage = gson.fromJson(errorMessageType, RestError.class);
        assertEquals(error, restErrorMessage.getStrMessage());
    }

}
