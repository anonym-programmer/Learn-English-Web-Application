package pl.robert.api.app.vote.domain;

public interface VoteConstants {

    int COL_LENGTH_MIN_TYPE = 2;
    int COL_LENGTH_MAX_TYPE = 3;


    /* Fields */

    String F_POST_ID = "postId";
    String F_TYPE = "type";


    /* Default messages */

    String M_POST_ID_EMPTY = "Post id cannot be empty";
    String M_POST_NOT_EXISTS = "Post id do not exists";
    String M_TYPE_EMPTY = "Post type cannot be empty";
    String M_TYPE_NOT_MATCH = "Vote type do not match";
}
