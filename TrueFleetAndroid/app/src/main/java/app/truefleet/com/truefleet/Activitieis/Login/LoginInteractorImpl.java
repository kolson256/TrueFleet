package app.truefleet.com.truefleet.Activitieis.Login;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import javax.inject.Inject;

import app.truefleet.com.truefleet.Models.User;
import app.truefleet.com.truefleet.Resources.ConnectionDetector;
import app.truefleet.com.truefleet.Resources.LoginManager;
import app.truefleet.com.truefleet.Tasks.ApiService;
import app.truefleet.com.truefleet.Tasks.OrderService;
import app.truefleet.com.truefleet.Tasks.Requests.LoginRequest;
import app.truefleet.com.truefleet.Tasks.RestCallback;
import app.truefleet.com.truefleet.Tasks.RestError;
import app.truefleet.com.truefleet.TrueFleetApp;
import retrofit.client.Response;

/**
 * Created by Chris Lacy on 2/27/2015.
 */
public class LoginInteractorImpl implements LoginInteractor {
    private final String LOG_TAG = LoginInteractorImpl.class.getSimpleName();

    @Inject
    ConnectionDetector cd;
    @Inject
    ApiService apiService;
    @Inject
    LoginManager loginManager;

    LoginInteractorImpl(Context context) {
        ((TrueFleetApp) context.getApplicationContext()).inject(this);
    }

    @Override
    public void login(String username, String password, OnLoginFinishedListener listener) {
        final String finalUsername = username;
        final OnLoginFinishedListener finalListener = listener;

        if (TextUtils.isEmpty(username)) {
            listener.onUsernameError();
        } else if (TextUtils.isEmpty(password)) {
            listener.onPasswordError();
        } else { //communicate w server
            //Ensure connected to internet
            boolean connectedToInternet = cd.isConnectingToInternet();
            if (!connectedToInternet) {
                listener.notConnectedToInternetError();
                return;
            }
            OrderService os = new OrderService("test", 3, 3);
            os.execute();
            apiService.login(new LoginRequest(username, password), new RestCallback<User>() {
                @Override
                public void success(User user, Response response) {

                    if (user.getApiVersion() != null) {
                        user.setUsername(finalUsername);
                        loginManager.createLoginSession(user);
                        finalListener.onSuccess();
                    } else {
                        Log.i(LOG_TAG, "Invalid login Attempt");
                        finalListener.onLoginError();
                    }
                }

                @Override
                public void failure(RestError error) {
                    finalListener.onCommunicationError();
                    Log.i(LOG_TAG, "Error: " + error.getStrMessage());
                }
            });
        }
    }
}
