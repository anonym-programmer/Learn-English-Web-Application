package pl.robert.api.app.shared;

public interface Constants {

    /* error codes */

    String C_EXISTS = "exists";
    String C_MATCH = "match";
    String C_NOT_MATCH = "notMatch";
    String C_NOT_EXISTS = "notExists";
    String C_NOT_ENABLED= "notEnabled";
    String C_SENT ="sent";


    /* user */

    int COL_LENGTH_MIN_USERNAME = 2;
    int COL_LENGTH_MAX_USERNAME = 20;
    int COL_LENGTH_MIN_PASSWORD = 5;
    int COL_LENGTH_MAX_PASSWORD = 20;
    int COL_LENGTH_ENCODED_PASSWORD = 60;
    int COL_LENGTH_ROLE = 11;
    int COL_LENGTH_CONFIRMATION_TOKEN = 36;
    int COL_LENGTH_DATE_IN_SECONDS = 11;
    int COL_LENGTH_RANK = 10;

    String ROLE_USER = "ROLE_USER";
    String USER = "User";
    String USER_ADMIN = "User, Admin";

    String F_USERNAME = "username";
    String F_EMAIL = "email";
    String F_CONFIRMED_PASSWORD = "confirmedPassword";
    String F_CONFIRMED_EMAIL = "confirmedEmail";

    String M_USERNAME_EMPTY = "Username cannot be empty";
    String M_USERNAME_LENGTH = "Username should have between 2 and 20 characters";
    String M_USERNAME_EXISTS = "Username already exists";
    String M_USERNAME_NOT_EXISTS = "Username do not exists";
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


    /* post */

    int COL_LENGTH_MAX_TITLE = 50;
    int COL_LENGTH_MIN_TITLE = 5;
    int COL_LENGTH_MIN_DESCRIPTION = 10;
    int COL_LENGTH_MAX_DESCRIPTION = 255;
    int COL_LENGTH_DATE = 10;

    String F_POST_ID = "postId";

    String M_TITLE_EMPTY = "Title cannot be empty";
    String M_TITLE_LENGTH = "Title should have between 5 and 50 characters";
    String M_DESCRIPTION_EMPTY = "Description cannot be empty";
    String M_DESCRIPTION_LENGTH = "Description should have between 10 and 255 characters";
    String M_POST_ID_EMPTY = "Post id cannot be empty";
    String M_POST_NOT_EXISTS = "Post id do not exists";


    /* vote */

    int COL_LENGTH_MIN_TYPE = 2;
    int COL_LENGTH_MAX_TYPE = 3;

    String TYPE_YES = "YES";
    String TYPE_NO = "NO";

    String F_TYPE = "type";

    String M_TYPE_EMPTY = "Post type cannot be empty";
    String M_TYPE_NOT_MATCH = "Vote type do not match";
    String M_TYPE_EXISTS = "You already voted this post";


    /* comment */

    int COL_LENGTH_MIN_TEXT = 10;
    int COL_LENGTH_MAX_TEXT = 255;

    String M_TEXT_EMPTY = "Text cannot be empty";
    String M_COMMENT_LENGTH = "Text of a comment should have between 10 and 255 characters";


    /* challenge */

    int COL_LENGTH_GAINED_XP = 2;
    int COL_LENGTH_CHALLENGE_RESULT = 4;
    int COL_LENGTH_QUESTION = 150;
    int COL_LENGTH_ANSWERS = 150;
    int COL_LENGTH_MY_ANSWERS = 9;
    int COL_LENGTH_CORRECT_ANSWER_FULL_FORM = 30;
    int COL_LENGTH_CORRECT_ANSWER_SHORT_FORM = 1;
    int COL_LENGTH_CHALLENGE_STATUS = 9;

    String F_ATTACKER_USERNAME = "attackerUsername";
    String F_DEFENDER_USERNAME = "defenderUsername";
    String F_QUESTION_IDS = "questionsIds";

    String M_DEFENDER_USERNAME_EMPTY = "Defender username can not be empty";
    String M_ATTACKER_USERNAME_NOT_EXISTS = "Attacker username do not exist";
    String M_DEFENDER_USERNAME_NOT_EXISTS = "Defender username do not exist";
    String M_ATTACKER_EQUALS_DEFENDER_USERNAME = "You cannot challenge yourself";
    String M_CHALLENGE_ID_EMPTY = "Challenge id can not be empty";

    String F_CHALLENGE_RESULT_WIN = "WIN";
    String F_CHALLENGE_RESULT_LOSE = "LOSE";
    String F_CHALLENGE_RESULT_DRAW = "DRAW";

    String M_CHALLENGE_RESULT_WIN = "YOU WON";
    String M_CHALLENGE_RESULT_LOSE = "YOU LOST";
    String M_CHALLENGE_RESULT_DRAW = "YOU DREW";
    String M_CHALLENGE_RESULT_NONE = "NONE";
}
