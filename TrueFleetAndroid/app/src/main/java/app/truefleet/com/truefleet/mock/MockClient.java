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
  response  = "Login json response";
        } else if (uri.getPath().equals("/GcmRegistration")) {
        response = "Gcm registration json response";
        } else {
        response = "Invalid path json response";
        }

        return new Response(request.getUrl(), 200, "OK", Collections.EMPTY_LIST,
        new TypedByteArray("application/json", response.getBytes()));
        }
        }
