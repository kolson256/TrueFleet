package app.truefleet.com.truefleet;

import android.app.Application;
import android.content.Context;

import java.util.Arrays;
import java.util.List;

import dagger.ObjectGraph;
//import dagger.internal.Modules;


/**
 * Created by Chris Lacy on 2/27/2015.
 */
public class TrueFleetApp extends Application {
    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
   

        buildObjectGraphAndInject();
    }
    public void buildObjectGraphAndInject() {
        //objectGraph = ObjectGraph.create(Modules.list(this));
        //objectGraph.inject(this);
        Object[] modules = getModules().toArray();
        objectGraph = ObjectGraph.create(modules);
        objectGraph.inject(this);
    }

    public void inject(Object o) {
        objectGraph.inject(o);
    }
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new TrueFleetModule(this));
    }
    public static TrueFleetApp get(Context context) {
        return (TrueFleetApp) context.getApplicationContext();
    }
    public ObjectGraph getObjectGraph() {
        return this.objectGraph;
    }


}
