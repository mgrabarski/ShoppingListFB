package com.mateusz.grabarski.myshoppinglist.utils;

import android.text.TextUtils;
import android.util.Patterns;

/**
 * Created by MGrabarski on 22.12.2017.
 */

public class InputValidator {

    public static final int PASSWORD_MIN_LENGTH = 6;

    public InputValidator() {
    }

    public boolean isEmailValid(String email) {
        if (TextUtils.isEmpty(email)) {
            return false;
        } else {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }

    public boolean isPasswordLengthValid(String password) {
        return password.length() >= PASSWORD_MIN_LENGTH;
    }

    public boolean isUserNameValid(String userName) {
        return TextUtils.isEmpty(userName);
    }

    public boolean isPasswordAndConfirmPasswordValid(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }
}
