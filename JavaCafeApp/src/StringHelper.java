/**
 * This class is used to hold methods for string manipulation
 * @author Siva Aditya
 */

public class StringHelper {

    /**
     * A method to make a string to be in a certain length
     */
    public static String limit(String value, int length) {
        StringBuilder limitedVal = new StringBuilder(value);

        /* If the string is too long, cut the string
         * and add ... at the end of the string
         */
        if (limitedVal.length() > length) {
            limitedVal.setLength(length);
            limitedVal.append("...");
        }

        /* If the string is too short, keep adding space
         * at the end of the string until the length
         * matches the specified length in parameter
         */
        else if (limitedVal.length() < length) {
            int oriLength = limitedVal.length();
            for (int i=oriLength; i<(length+3); i++) {
                limitedVal.append(" ");
            }
        }

        else {
            limitedVal.append(" ".repeat(3));
        }

        return limitedVal.toString();
    }
}
