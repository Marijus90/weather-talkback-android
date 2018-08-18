package accessibility.forecast.marijus.weathertalkback2.helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import accessibility.forecast.marijus.weathertalkback2.base.App;

/**
 * This provides methods to help get the state of the device - location, network state etc.
 */
public class DeviceStateUtils {

    public static void getDeviceLocation() {
        //TODO: Implement this
        return;
    }

    public static boolean isDeviceOnline() {
        ConnectivityManager connManager = (ConnectivityManager) App.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connManager != null) {
            NetworkInfo networkInfo = connManager.getActiveNetworkInfo();

            return networkInfo != null && networkInfo.isConnectedOrConnecting();
        }
        return false;
    }

}
