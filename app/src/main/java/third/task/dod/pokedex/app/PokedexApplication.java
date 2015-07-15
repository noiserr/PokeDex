package third.task.dod.pokedex.app;

import android.app.Application;

import com.activeandroid.ActiveAndroid;
import com.facebook.stetho.Stetho;

/**
 * Created by noiser on 15.07.15.
 */
public class PokedexApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        initDatabase();
        initStetho();
    }

    private void initDatabase() {
        ActiveAndroid.initialize(this);
    }

    private void initStetho() {
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build());
    }
}
