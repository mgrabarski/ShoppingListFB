package com.mateusz.grabarski.myshoppinglist.views.activities.login.contract;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

/**
 * Created by MGrabarski on 04.01.2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterImplTest {

    private static final String CORRECT_EMAIL_ADDRESS = "mateusz.grabarski@gmail.com";
    private static final String INCORRECT_EMAIL_ADDRESS = "mateusz.grabarski";
    private static final String CORRECT_NAME = "Mateusz";
    private static final String CORRECT_PASSWORD = "zaqwsx";
    private static final String INCORRECT_PASSWORD = "xswqaz";

    @Mock
    private LoginContract.View mView;

    private LoginContract.Presenter mPresenter;

    @Before
    public void setUp() throws Exception {
        mPresenter = new LoginPresenterImpl(mView);
    }

    @Test
    public void showErrorMessageWhenEmailIsNotValid() throws Exception {
        mPresenter.loginByEmail(INCORRECT_EMAIL_ADDRESS, "");
        verify(mView).displayEmailError();
    }

    @Test
    public void showErrorMessageWhenEmailIsEmpty() throws Exception {
        mPresenter.loginByEmail("", "");
        verify(mView).displayEmailError();
    }

    @Test
    public void showErrorMessageWhenEmailIsNull() throws Exception {
        mPresenter.loginByEmail(null, null);
        verify(mView).displayEmailError();
    }

    @Test
    public void showErrorMessageWhenPasswordIsToShort() throws Exception {
        mPresenter.loginByEmail(CORRECT_EMAIL_ADDRESS, "zaq");
        verify(mView).displayPasswordError();
    }

    @Test
    public void showErrorMessageWhenPasswordIsEmpty() throws Exception {
        mPresenter.loginByEmail(CORRECT_EMAIL_ADDRESS, "");
        verify(mView).displayPasswordError();
    }

    @Test
    public void showErrorMessageWhenPasswordIsNull() throws Exception {
        mPresenter.loginByEmail(CORRECT_EMAIL_ADDRESS, null);
        verify(mView).displayPasswordError();
    }

    @Test
    public void showProgressIndicatorWhenLoginAndPasswordAreOk() throws Exception {
        mPresenter.loginByEmail(CORRECT_EMAIL_ADDRESS, CORRECT_PASSWORD);
        verify(mView).showProgressDialog();
    }

    @Test
    public void showSignUpErrorMessageWhenEmailIsNotValid() throws Exception {
        mPresenter.signUp(CORRECT_NAME, INCORRECT_EMAIL_ADDRESS, "", "");
        verify(mView).displayEmailError();
    }

    @Test
    public void showSignUpErrorMessageWhenEmailIsEmpty() throws Exception {
        mPresenter.signUp(CORRECT_NAME,"", "", "");
        verify(mView).displayEmailError();
    }

    @Test
    public void showSignUpErrorMessageWhenEmailIsNull() throws Exception {
        mPresenter.signUp(CORRECT_NAME,null, "", "");
        verify(mView).displayEmailError();
    }

    @Test
    public void showSignUpErrorMessageWhenPasswordIsToShort() throws Exception {
        mPresenter.signUp(CORRECT_NAME, CORRECT_EMAIL_ADDRESS, "zaq", "zaq");
        verify(mView).displayPasswordError();
    }

    @Test
    public void showSignUpErrorMessageWhenPasswordIsEmpty() throws Exception {
        mPresenter.signUp(CORRECT_NAME, CORRECT_EMAIL_ADDRESS, "", "");
        verify(mView).displayPasswordError();
    }

    @Test
    public void showSignUpErrorMessageWhenPasswordIsNull() throws Exception {
        mPresenter.signUp(CORRECT_NAME, CORRECT_EMAIL_ADDRESS, null, null);
        verify(mView).displayPasswordError();
    }

    @Test
    public void showConfirmPasswordErrorWhenPasswordAndConfirmPasswordNotValid() throws Exception {
        mPresenter.signUp(CORRECT_NAME, CORRECT_EMAIL_ADDRESS, CORRECT_PASSWORD, INCORRECT_PASSWORD);
        verify(mView).displayPasswordMatchError();
    }

    @Test
    public void showConfirmPasswordErrorWhenPasswordAndConfirmPasswordNotValidReverse() throws Exception {
        mPresenter.signUp(CORRECT_NAME, CORRECT_EMAIL_ADDRESS, INCORRECT_PASSWORD, CORRECT_PASSWORD);
        verify(mView).displayPasswordMatchError();
    }

    @Test
    public void showUserNameErrorWhenNameIsEmpty() throws Exception {
        mPresenter.signUp("", CORRECT_EMAIL_ADDRESS, CORRECT_PASSWORD, CORRECT_PASSWORD);
        verify(mView).displayUserNameError();
    }

    @Test
    public void showUserNameErrorWhenNameIsNull() throws Exception {
        mPresenter.signUp(null, CORRECT_EMAIL_ADDRESS, CORRECT_PASSWORD, CORRECT_PASSWORD);
        verify(mView).displayUserNameError();
    }

    @Test
    public void showProgressIndicatorWhenInputDataAreCorrect() throws Exception {
        mPresenter.signUp(CORRECT_NAME, CORRECT_EMAIL_ADDRESS, CORRECT_PASSWORD, CORRECT_PASSWORD);
        verify(mView).showProgressDialog();
    }

    @Test
    public void showEmailErrorWhenPasswordIsNotValidWhenTryResetPassword() throws Exception {
        mPresenter.resetPassword(INCORRECT_EMAIL_ADDRESS);
        verify(mView).displayEmailError();
    }

    @Test
    public void showProgressIndicatorWhenResetPasswordEmailIsSending() throws Exception {
        mPresenter.resetPassword(CORRECT_EMAIL_ADDRESS);
        verify(mView).showProgressDialog();
    }
}