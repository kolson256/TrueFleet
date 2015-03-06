package app.truefleet.com.truefleet.Tasks;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.mime.TypedByteArray;

/**
 * Created by Chris Lacy on 2/9/2015.
 */
/* Our own restcall back in order to be able to access the error messages through retrofit */
public abstract class RestCallback<T> implements Callback<T>
{
    private final String LOG_TAG = RestCallback.class.getSimpleName();
    public abstract void failure(RestError restError);

    @Override
    public void failure(RetrofitError error)
    {

        final GsonBuilder gsonBuilder = new GsonBuilder();

        gsonBuilder.registerTypeAdapter(RestError.class, new RestErrorTypeAdapter());

        final Gson gson = gsonBuilder.create();
        RestError restError=null;

        try {
            String json = new String(((TypedByteArray) error.getResponse().getBody()).getBytes());
            Log.d(LOG_TAG, json);
            restError = gson.fromJson(json, RestError.class);
        } catch (Exception e) {
            Log.d(LOG_TAG, "Unexpected failure result from server: " + e.toString());
        }
       // RestError restError = (RestError) error.getBodyAs(RestError.class);

        if (restError != null)
            failure(restError);
        else
        {
            failure(new RestError(error.getMessage()));
        }
    }
}
