package pl.robert.api.user.domain;

public interface UserConstants {

    int COL_LENGTH_MIN_LOGIN = 2;
    int COL_LENGTH_MAX_LOGIN = 20;
    int COL_LENGTH_MIN_PASSWORD = 5;
    int COL_LENGTH_MAX_PASSWORD = 20;
    int COL_LENGTH_CONFIRMATION_TOKEN = 36;
    int COL_LENGTH_DATE_IN_SECONDS = 11;


    /* Fields */

    String F_LOGIN = "login";
    String F_EMAIL = "email";
    String F_CONFIRMED_PASSWORD = "confirmedPassword";


    /* Error codes */

    String C_EXIST = "exist";
    String C_NOT_MATCH = "notMatch";


    /* Default messages */


    String M_LOGIN_EMPTY = "Login cannot be empty";
    String M_LOGIN_LENGTH = "Login should have between 2 and 20 characters";
    String M_LOGIN_EXISTS = "Login already exists";
    String M_EMAIL_EMPTY = "Email cannot be empty";
    String M_EMAIL_WRONG_FORMAT = "Please enter valid email";
    String M_EMAIL_EXISTS = "Email already exists";
    String M_PASSWORD_EMPTY = "Password cannot be empty";
    String M_PASSWORD_LENGTH = "Password should have between 5 and 20 characters";
    String M_CONFIRMED_PASSWORD_EMPTY = "Confirmed password cannot be empty";
    String M_CONFIRMED_PASSWORD_NOT_MATCH = "Confirmed password must match with password";
}
