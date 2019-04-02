package pl.robert.api.app.user.domain;

public interface UserConstants {

    int COL_LENGTH_MIN_USERNAME = 2;
    int COL_LENGTH_MAX_USERNAME = 20;
    int COL_LENGTH_MIN_PASSWORD = 5;
    int COL_LENGTH_MAX_PASSWORD = 20;
    int COL_LENGTH_ENCODED_PASSWORD = 60;
    int COL_LENGTH_ROLE = 11;
    int COL_LENGTH_CONFIRMATION_TOKEN = 36;
    int COL_LENGTH_DATE_IN_SECONDS = 11;
    int COL_LENGTH_RANK = 10;


    /* Fields */

    String F_USERNAME = "username";
    String F_EMAIL = "email";
    String F_CONFIRMED_PASSWORD = "confirmedPassword";
    String F_CONFIRMED_EMAIL = "confirmedEmail";


    /* Error codes */

    String C_EXISTS = "exists";
    String C_NOT_MATCH = "notMatch";
    String C_NOT_EXISTS = "notExists";
    String C_NOT_ENABLED= "notEnabled";
    String C_SENT ="sent";


    /* Default messages */

    String M_USERNAME_EMPTY = "Username cannot be empty";
    String M_USERNAME_LENGTH = "Username should have between 2 and 20 characters";
    String M_USERNAME_EXISTS = "Username already exists";
    String M_EMAIL_EMPTY = "Email cannot be empty";
    String M_EMAIL_WRONG_FORMAT = "Please enter valid email";
    String M_EMAIL_EXISTS = "Email already exists";
    String M_EMAIL_NOT_EXISTS = "Email do not exists";
    String M_CONFIRMED_EMAIL_EMPTY = "Confirmed email cannot be empty";
    String M_CONFIRMED_EMAIL_NOT_MATCH = "Confirmed email must match with email";
    String M_PASSWORD_EMPTY = "Password cannot be empty";
    String M_PASSWORD_LENGTH = "Password should have between 5 and 20 characters";
    String M_CONFIRMED_PASSWORD_EMPTY = "Confirmed password cannot be empty";
    String M_CONFIRMED_PASSWORD_NOT_MATCH = "Confirmed password must match with password";
    String M_ACCOUNT_NOT_ENABLED = "Account is not confirmed after registration";
    String M_TOKEN_SENT = "Token has been already sent to the given email";
}
