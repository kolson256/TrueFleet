package app.truefleet.com.truefleet.Activitieis.Login;

/**
 * Created by Chris Lacy on 2/27/2015.
 */
public interface LoginInteractor {

    public void login(String username, String password, OnLoginFinishedListener listener);
}
