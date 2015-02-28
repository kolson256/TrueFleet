package app.truefleet.com.truefleet.Activitieis.Login;

/**
 * Created by Chris Lacy on 2/27/2015.
 */
public interface LoginView {

    public void setLoginError();

    public void setCommunicationError();

    public void navigateToHome();

    public void usernameError();

    public void passwordError();

    public void notConnectedToOInternet();
}
