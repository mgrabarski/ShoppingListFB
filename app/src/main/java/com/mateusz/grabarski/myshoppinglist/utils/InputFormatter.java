package com.mateusz.grabarski.myshoppinglist.utils;

/**
 * Created by MGrabarski on 02.01.2018.
 */

public class InputFormatter {

    public InputFormatter() {
    }

    public String encodeEmail(String email) {
        return email.replace(".", ",");
    }
}
