package pl.robert.api.user.domain;

interface UserValidatorConstants {

    int COL_LENGTH_MIN_LOGIN = 2;
    int COL_LENGTH_MAX_LOGIN = 20;
    int COL_LENGTH_MIN_PASSWORD = 5;
    int COL_LENGTH_MAX_PASSWORD = 20;


    /* Fields */

    String F_LOGIN = "login";
    String F_PASSWORD = "password";
    String F_CONFIRMED_PASSWORD = "confirmedPassword";


    /* Error codes */

    String C_EMPTY = "empty";
    String C_MIN_LENGTH = "minLength";
    String C_MAX_LENGTH = "maxLength";
    String C_EXIST = "exist";
    String C_NOT_MATCH = "notMatch";


    /* Default messages */

    String M_LOGIN_EMPTY = "Login cannot be empty";
    String M_LOGIN_MIN_LENGTH = "Login cannot have less than 2 characters";
    String M_LOGIN_MAX_LENGTH = "Login cannot have more than 20 characters";
    String M_LOGIN_EXISTS = "Login already exists";
    String M_PASSWORD_EMPTY = "Password cannot be empty";
    String M_PASSWORD_MIN_LENGTH = "Password cannot have less than 5 characters";
    String M_PASSWORD_MAX_LENGTH = "Password cannot have more than 20 characters";
    String M_CONFIRMED_PASSWORD_NOT_MATCH = "Confirmed password must match with password";
}
