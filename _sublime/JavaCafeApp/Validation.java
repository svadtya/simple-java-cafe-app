/**
 * This class is used to hold methods for input validation purposes
 * and printing error message
 * @author Siva Aditya
 */

import java.util.List;

public class Validation {

    public static boolean string(String value) {
        if (value.isBlank()) {
            errMsg("Value can't be empty");
            return false;
        }
        return true;
    }

    public static boolean integer(String number) {
        try {
            int value = Integer.parseInt(number);
            return true;
        } catch (NumberFormatException nfe) {
            errMsg("Invalid value");
            return false;
        }
    }

    public static boolean integer(String number, int lowerLimit) {
        if (integer(number)) {
            int value = Integer.parseInt(number);
            if (value >= lowerLimit)
                return true;
            else {
                errMsg("Invalid value");
                return false;
            }
        }
        return false;
    }

    public static boolean decimal(String number) {
        try {
            double value = Double.parseDouble(number);
            return true;
        } catch (NumberFormatException nfe) {
            errMsg("Invalid value");
            return false;
        }
    }

    public static boolean decimal(String number, double lowerLimit) {
        if (decimal(number)) {
            double value = Double.parseDouble(number);
            if (value >= lowerLimit)
                return true;
            else {
                errMsg("Invalid value");
                return false;
            }
        }
        return false;
    }


    public static boolean username(String username) {
        if (username.length() < 8 || username.length() > 15){
            errMsg("Username must be between 8-15 characters");
            return false;
        }

        for(int i=0; i<username.length(); i++) {
            if (username.charAt(i) == ' '){
                errMsg("<Space> is not allowed in username");
                return false;
            }
        }

        return true;
    }

    public static boolean password(String pass) {
        if (pass.length() != 8) {
            errMsg("Password must be in 8 characters");
            return false;
        }

        for(int i=0; i<pass.length(); i++) {
            if (pass.charAt(i) == ' ') {
                errMsg("<Space> is not allowed in password");
                return false;
            }
        }

        return true;
    }

    public static boolean name(String name) {
        if (name.isBlank()) {
            errMsg("Please input your full name");
            return false;
        }

        for (int i=0; i< name.length(); i++) {
            if (Character.isDigit(name.charAt(i))) {
                errMsg("Name can't contain numeric character");
                return false;
            }
        }

        return true;
    }

    public static boolean choice(char choice, List<Character> choiceList) {
        if (choiceList.contains(choice))
            return true;

        errMsg("Invalid choice");
        return false;
    }


    /**
     * A method to print error message
     */
    public static void errMsg(String errText) {
        System.out.println("-- ERROR: "+errText+".\n");
    }
}
