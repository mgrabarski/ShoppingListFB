package com.mateusz.grabarski.myshoppinglist.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by MGrabarski on 22.12.2017.
 */
public class InputValidatorTest {

    private InputValidator mInputValidator;

    @Before
    public void setUp() throws Exception {
        mInputValidator = new InputValidator();
    }

    @Test
    public void testIsEmailValid() {
        assertTrue(mInputValidator.isEmailValid("asdf@adsf.com"));
        assertTrue(mInputValidator.isEmailValid("asdf@adsf.com.com"));

        assertFalse(mInputValidator.isEmailValid("asdf@adsf"));
        assertFalse(mInputValidator.isEmailValid("@adsf.com"));
        assertFalse(mInputValidator.isEmailValid(""));
        assertFalse(mInputValidator.isEmailValid(null));
    }

    @Test
    public void testIsPasswordLengthValid() {
        assertTrue(mInputValidator.isPasswordLengthValid("zaqwsx"));
        assertTrue(mInputValidator.isPasswordLengthValid("zaq12wsx"));
        assertTrue(mInputValidator.isPasswordLengthValid("qwerty"));

        assertFalse(mInputValidator.isPasswordLengthValid("zaq"));
        assertFalse(mInputValidator.isPasswordLengthValid("xsw"));
        assertFalse(mInputValidator.isPasswordLengthValid("zaqws"));
        assertFalse(mInputValidator.isPasswordLengthValid(""));
        assertFalse(mInputValidator.isPasswordLengthValid(null));
    }

    @Test
    public void testUserNameValidation() {
        assertTrue(!mInputValidator.isUserNameValid("Some name"));
        assertTrue(!mInputValidator.isUserNameValid("Mateusz"));

        assertFalse(mInputValidator.isUserNameValid(""));
        assertFalse(mInputValidator.isUserNameValid(null));
    }

    @Test
    public void testPasswordAndConfirmPasswordValid() {
        assertTrue(mInputValidator.isPasswordAndConfirmPasswordValid("asdfasdf", "asdfasdf"));
        assertTrue(mInputValidator.isPasswordAndConfirmPasswordValid("zaqwsx", "zaqwsx"));

        assertFalse(mInputValidator.isPasswordAndConfirmPasswordValid("asdf", "asd"));
        assertFalse(mInputValidator.isPasswordAndConfirmPasswordValid("asd", "asdf"));
    }

    @After
    public void tearDown() throws Exception {
        mInputValidator = null;
    }

}