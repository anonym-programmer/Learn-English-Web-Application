export class Constants {

  public static readonly LOGIN = 'Successfully logged in!';
  public static readonly LOGOUT = 'Successfully logged out!';

  public static readonly INVALID_FIELD = 'Correct invalid field.';
  public static readonly INVALID_FIELDS = 'Correct invalid fields.';
  public static readonly SOMETHING_WRONG = 'Something went wrong.';
  public static readonly WRONG_CREDENTIALS = 'Username or password are invalid.';

  public static readonly POST_DOESNT_EXIST = 'Post doesn\'t exist.';
  public static readonly USER_DOESNT_EXIST = 'User doesn\'t exist.';
  public static readonly TOKEN_CONFIRMATION_FAILURE_MSG = 'Token doesn\'t exist.';

  public static readonly DELETE_USER = 'Successfully deleted current user!';
  public static readonly ADDED_COMMENT = 'Successfully added new comment!';
  public static readonly ADDED_POST = 'Successfully added new post!';
  public static readonly VOTED_POST = 'Successfully voted post!';
  public static readonly CHANGED_PASSWORD = 'You have successfully changed your password!';
  public static readonly CHANGED_EMAIL = 'Successfully changed email!';
  public static readonly TOKEN_CONFIRMATION_SUCCESS_MSG = 'You have successfully confirmed your account!';

  public static readonly FAILURE_TITLE = 'Oops...';
  public static readonly FORGOT_CREDENTIALS_TITLE = 'Forgot credential';
  public static readonly ACCOUNT_CONFIRMATION_TITLE = 'Account confirmation';
  public static readonly TOKEN_CONFIRMATION_SUCCESS_TITLE = 'Good job!';

  public static readonly FORGOT_CREDENTIALS_MSG = 'Reset token has been sent to your email.' + '<br>' +
    'U\'ve got 15 minutes from now, after that token will expire' + '<br>' +
    'and u will be not able to reset your password!';
  public static readonly ACCOUNT_CONFIRMATION_MSG = 'Confirmation token has been sent to your email.' + '<br>' +
    'U\'ve got 15 minutes from now, after that token will expire' + '<br>' +
    'and u will be not able to log into application!';

  public static readonly DECLINED_CHALLENGE = 'Declined challenge!';
}
