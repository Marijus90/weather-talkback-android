package accessibility.forecast.marijus.weathertalkback2.helper;

import org.junit.Test;

import accessibility.forecast.marijus.weathertalkback2.helper.utils.ItemDataStringUtils;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class StringUtilsTest {

    @Test
    public void shouldRoundDoubleToTemperatureCorrectly() {
        double value = 200.67;
        String result = ItemDataStringUtils.getFormattedAsTemperature(value);

        assertThat(result, containsString("201"));
    }

    @Test
    public void shouldRoundEvenDoubleToTemperatureCorrectly() {
        double value = 199;
        String result = ItemDataStringUtils.getFormattedAsTemperature(value);
        assertFalse(result.contains("."));
    }

    @Test
    public void windSpeedStringShouldContainMPH() {
        double value = 200.01;
        String result = ItemDataStringUtils.getFormattedAsWindSpeed(value);
        assertTrue(result.contains("mph"));
    }

    @Test
    public void windDirectionShouldBeCorrect() {
        double northerly = 0.00;
        double northerlyTwo = 360.99;
        double southerly = 180.00;
        double southEasterly = 120.46;
        double northWesterly = 300.1;

        assertEquals("Northerly", ItemDataStringUtils.getWindDirectionDescription(northerly));
        assertEquals("Northerly", ItemDataStringUtils.getWindDirectionDescription(northerlyTwo));
        assertEquals("Southerly", ItemDataStringUtils.getWindDirectionDescription(southerly));
        assertEquals("South Easterly", ItemDataStringUtils.getWindDirectionDescription(southEasterly));
        assertEquals("North Westerly", ItemDataStringUtils.getWindDirectionDescription(northWesterly));
    }

}
