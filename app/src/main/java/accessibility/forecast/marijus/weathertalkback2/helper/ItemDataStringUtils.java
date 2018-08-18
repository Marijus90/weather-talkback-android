package accessibility.forecast.marijus.weathertalkback2.helper;

/**
 * This provides methods to help format the Double values of API response such as temperature in to String.
 */
public class ItemDataStringUtils {

    private static String temperatureSymbol = (String.valueOf((char) 0x00B0));
    private static String[] windDirections = new String[]{"Northerly", "North Easterly", "Easterly", "South Easterly",
            "Southerly", "South Westerly", "Westerly", "North Westerly"};

    public static String getFormattedAsTemperature(double value) {
        String rounded = getRoundedValueNoDecimals(value);
        return rounded.concat(temperatureSymbol).concat("C");
    }

    public static String getFormattedAsWindSpeed(double value) {
        String rounded = getRoundedValueNoDecimals(value);
        return rounded.concat("mph");
    }

    private static String getRoundedValueNoDecimals(double value) {
        String roundedValue = String.valueOf(Math.round(value));
        int separator = roundedValue.indexOf(".");

        if (separator != -1) {
            return roundedValue.substring(0, separator);
        }
        return roundedValue;
    }

    public static String getWindDirectionDescription(double value) {
        return getWindDirectionAsString(value);
    }

    private static String getWindDirectionAsString(double value) {
        Double degrees = value + (360 / 16);

        if (degrees < 0) {
            degrees = 360 - Math.abs(degrees) % 360;
        } else {
            degrees = degrees % 360;
        }

        int index = (degrees.intValue() / 45);
        return windDirections[index];
    }

    public static String getFormattedIconName(String text) {
        return text.replaceAll("-", "_");
    }

}
