package ua.coral.ugcc.common.utils;

public final class StringUtils {

    private StringUtils() {
        super();
    }

    public static String abbreviate(final String str, final int maxWidth) {
        if (str == null || str.isEmpty()) {
            return null;
        } else if (str.length() <= maxWidth) {
            return str + "...";
        }
        return abbreviate(str, 0, maxWidth, 0);
    }

    private static String abbreviate(final String str, final int index, final int maxWidth, int counter) {
        final int foundIndex = getFoundIndex(str, index);
        counter++;
        if (counter < maxWidth) {
            return abbreviate(str, foundIndex, maxWidth, counter);
        }
        return str.substring(0, foundIndex) + "...";
    }

    private static int getFoundIndex(final String str, final int index) {
        final int foundIndex = str.indexOf(" ", index);
        if (foundIndex == -1) {
            return index;
        } else if ((foundIndex + 1) < str.length() && str.charAt(foundIndex + 1) == ' ') {
            return getFoundIndex(str, foundIndex + 1);
        }
        return foundIndex + 1;
    }
}
