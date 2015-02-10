package app.truefleet.com.truefleet.Tasks;

import retrofit.Callback;
import retrofit.RetrofitError;

/**
 * Created by Chris Lacy on 2/9/2015.
 */
public abstract class RestCallback<T> implements Callback<T>
{
    public abstract void failure(RestError restError);

    @Override
    public void failure(RetrofitError error)
    {
        RestError restError = (RestError) error.getBodyAs(RestError.class);

        if (restError != null)
            failure(restError);
        else
        {
            failure(new RestError(error.getMessage()));
        }
    }
}
