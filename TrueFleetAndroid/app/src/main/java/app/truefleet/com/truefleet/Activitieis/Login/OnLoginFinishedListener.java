package app.truefleet.com.truefleet.Activitieis.Login;

/**
 * Created by Chris Lacy on 2/27/2015.
 */
public interface OnLoginFinishedListener {

    public void onCommunicationError();

    public void onLoginError();

    public void onSuccess();

    public void onUsernameError();

    public void onPasswordError();

    public void notConnectedToInternetError();
}
