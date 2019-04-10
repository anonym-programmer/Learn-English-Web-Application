package pl.robert.api.app.post.query;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostQuery {

    String id;
    String title;
    String description;
    String date;
    String upVote;
    String downVote;
    String username;
}
