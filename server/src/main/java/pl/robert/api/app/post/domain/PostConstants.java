package pl.robert.api.app.post.domain;

public interface PostConstants {

    int COL_LENGTH_MAX_TITLE = 50;
    int COL_LENGTH_MIN_TITLE = 5;
    int COL_LENGTH_MIN_DESCRIPTION = 10;
    int COL_LENGTH_MAX_DESCRIPTION = 255;
    int COL_LENGTH_DATE = 10;


    /* Fields */

    /* Error codes */

    /* Default messages */

    String M_TITLE_EMPTY = "Title cannot be empty";
    String M_TITLE_LENGTH = "Title should have between 5 and 50 characters";
    String M_DESCRIPTION_EMPTY = "Description cannot be empty";
    String M_DESCRIPTION_LENGTH = "Description should have between 10 and 255 characters";
}
