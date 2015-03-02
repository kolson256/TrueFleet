import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import java.util.List;

import javax.inject.Inject;

import app.truefleet.com.truefleet.Activitieis.Login.LoginActivity;
import app.truefleet.com.truefleet.Models.User;
import app.truefleet.com.truefleet.Tasks.ApiService;
import app.truefleet.com.truefleet.Tasks.RestCallback;

/**
 * Created by Chris Lacy on 2/28/2015.
 */
@Config(emulateSdk = 16, manifest=Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class TestApi {
    @Inject ApiService mockApi;

    private LoginActivity loginActivity;

    @Captor
    private ArgumentCaptor<RestCallback<List<User>>> cb;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        ActivityController<LoginActivity> controller = Robolectric.buildActivity(LoginActivity.class);

        loginActivity = controller.get();



    }

}
