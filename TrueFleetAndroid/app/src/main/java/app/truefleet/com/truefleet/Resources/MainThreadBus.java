package app.truefleet.com.truefleet.Resources;

import android.os.Handler;
import android.os.Looper;

import com.squareup.otto.Bus;

/**
 * Created by Chris Lacy on 3/10/2015.
 */
//Used to be able to post bus events on main thread as you can't in async tasks
public class MainThreadBus extends Bus {
    private final Handler mainThread = new Handler(Looper.getMainLooper());

    @Override
    public void post(final Object event) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            super.post(event);
        } else {
            mainThread.post(new Runnable() {
                @Override
                public void run() {
                    post(event);
                }
            });
        }
    }
}