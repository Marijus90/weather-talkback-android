package accessibility.forecast.marijus.weathertalkback2.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * This provides methods to help get the state of the device - location, network state, time.
 */
public class DeviceStateUtils {
    private static final int PARSING_ERROR_VALUE_TO_FAIL_FUNCTION = 2;

    private static final long SECONDS_IN_MILLI = 1000;
    private static final long MINUTES_IN_MILLI = SECONDS_IN_MILLI * 60;
    private static final long HOURS_IN_MILLI = MINUTES_IN_MILLI * 60;
    private static final long DAYS_IN_MILLI = HOURS_IN_MILLI * 24;

    public static void getDeviceLocation() {
        //TODO: Implement this
        // https://developer.android.com/training/location/retrieve-current
        return;
    }

    public static String getCurrentTimeAsString() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.UK);
        return timeFormat.format(calendar.getTime());
    }

    public static String getCurrentDateAsString() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat =
                new SimpleDateFormat("dd/M/yyyy hh:mm:ss", Locale.UK);

        return dateFormat.format(calendar.getTime());
    }

    public static boolean isOlderThanOneDay(String dateCreated) {
        return getNumberOfElapsedDays(dateCreated) >= 1;
    }

    private static long getNumberOfElapsedDays(String dateCreated) {
        SimpleDateFormat dateFormat =
                new SimpleDateFormat("dd/M/yyyy hh:mm:ss", Locale.UK);
        Date startDate;
        Date endDate;
        try {
            startDate = dateFormat.parse(dateCreated);
            endDate = dateFormat.parse(getCurrentDateAsString());
        } catch (ParseException e) {
            return PARSING_ERROR_VALUE_TO_FAIL_FUNCTION;
        }

        long differenceInMillis = endDate.getTime() - startDate.getTime();
        return (differenceInMillis / DAYS_IN_MILLI);
    }

}
