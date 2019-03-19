package pl.robert.api.user.domain;

interface UserValidatorConstants {

    int COL_LENGTH_MIN_LOGIN = 2;
    int COL_LENGTH_MAX_LOGIN = 20;
    int COL_LENGTH_MIN_PASSWORD = 5;
    int COL_LENGTH_MAX_PASSWORD = 20;

    String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[" +
             "\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0" +
             "c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z" +
             "0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01" +
             "]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-" +
             "\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";


    /* Fields */

    String F_LOGIN = "login";
    String F_PASSWORD = "password";
    String F_CONFIRMED_PASSWORD = "confirmedPassword";
    String F_EMAIL = "email";


    /* Error codes */

    String C_EMPTY = "empty";
    String C_MIN_LENGTH = "minLength";
    String C_MAX_LENGTH = "maxLength";
    String C_EXIST = "exist";
    String C_NOT_MATCH = "notMatch";
    String C_WRONG_FORMAT = "wrongFormat";


    /* Default messages */

    String M_LOGIN_EMPTY = "Login cannot be empty";
    String M_LOGIN_MIN_LENGTH = "Login cannot have less than 2 characters";
    String M_LOGIN_MAX_LENGTH = "Login cannot have more than 20 characters";
    String M_LOGIN_EXISTS = "Login already exists";
    String M_PASSWORD_EMPTY = "Password cannot be empty";
    String M_PASSWORD_MIN_LENGTH = "Password cannot have less than 5 characters";
    String M_PASSWORD_MAX_LENGTH = "Password cannot have more than 20 characters";
    String M_CONFIRMED_PASSWORD_NOT_MATCH = "Confirmed password must match with password";
    String M_EMAIL_EMPTY = "Email cannot be empty";
    String M_EMAIL_EXISTS = "Email already exists";
    String M_EMAIL_WRONG_FORMAT = "Please enter valid email";
}
