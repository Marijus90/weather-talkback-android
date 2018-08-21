package accessibility.forecast.marijus.weathertalkback2.helper;

import org.junit.Test;

import accessibility.forecast.marijus.weathertalkback2.helper.utils.DeviceStateUtils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DeviceStateTest {

    @Test
    public void dateIsOlderThanOneDayAgo() {
        String oldDate = "18/8/2010 07:32:20";

        boolean result = DeviceStateUtils.isOlderThanOneDay(oldDate);
        assertTrue(result);
    }

    @Test
    public void todayDateIsNotOlderThanOneDayAgo() {
        String todayDate = DeviceStateUtils.getCurrentDateAsString();

        boolean result = DeviceStateUtils.isOlderThanOneDay(todayDate);
        assertFalse(result);
    }

}
