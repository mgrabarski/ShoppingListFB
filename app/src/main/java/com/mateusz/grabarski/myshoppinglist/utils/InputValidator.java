package com.mateusz.grabarski.myshoppinglist.utils;

import android.text.TextUtils;
import android.util.Patterns;

import java.util.regex.Pattern;

/**
 * Created by MGrabarski on 22.12.2017.
 */

public class InputValidator {

    public static final Pattern EMAIL_ADDRESS
            = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );

    public static final int PASSWORD_MIN_LENGTH = 6;

    public InputValidator() {
    }

    public boolean isEmailValid(String email) {
        if (email == null || TextUtils.isEmpty(email)) {
            return false;
        } else {
            return EMAIL_ADDRESS.matcher(email).matches();
        }
    }

    public boolean isPasswordLengthValid(String password) {
        return password != null && password.length() >= PASSWORD_MIN_LENGTH;
    }

    public boolean isUserNameValid(String userName) {
        return !(userName == null || userName.length() == 0);
    }

    public boolean isPasswordAndConfirmPasswordValid(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }
}
