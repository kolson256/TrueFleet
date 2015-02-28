package app.truefleet.com.truefleet.Activitieis.Login;

import android.content.Context;

/**
 * Created by Chris Lacy on 2/27/2015.
 */
public class LoginPresenterImpl implements LoginPresenter, OnLoginFinishedListener {

    private LoginView loginView;
    private Context context;
    private LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginView loginView, Context context) {
        this.context = context;
        this.loginView = loginView;

        this.loginInteractor = new LoginInteractorImpl(context);
    }

    @Override
    public void validateCredentials(String username, String password) {
        loginInteractor.login(username, password, this);
    }

    @Override
    public void onCommunicationError() {
        loginView.setCommunicationError();
    }

    @Override
    public void onLoginError() {
        loginView.setLoginError();
    }

    @Override
    public void onSuccess() {
        loginView.navigateToHome();
    }

    @Override
    public void onUsernameError() {
        loginView.usernameError();
    }

    @Override
    public void onPasswordError() {
        loginView.passwordError();
    }

    @Override
    public void notConnectedToInternetError() {
        loginView.notConnectedToOInternet();
    }
}
