package pl.robert.api.user.domain;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
class UserValidator {

    final static int COL_LENGTH_MAX_LOGIN = 20;
    final static int COL_LENGTH_MAX_PASSWORD = 20;
}
