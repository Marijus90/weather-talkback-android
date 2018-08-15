package accessibility.forecast.marijus.weathertalkback2.base;

import android.app.Application;
import android.content.Context;

public class App extends Application {
    //TODO: Remove if not used to set caching
    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
