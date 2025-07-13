package com.deepti.scrumsimulator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;

public class Login_PageTest {

    @Test
    public void testValidLoginCredentials() throws NoSuchFieldException, IllegalAccessException {
        Login_Page loginPage = new Login_Page();
        setTextFieldValue(loginPage, "jTextField1", "Soumith");
        setPasswordFieldValue(loginPage, "jPasswordField1", "Ss@02190220");
        clickOkButton(loginPage);
        // TODO: Add assertions based on the expected behavior after a successful login
    }

    @Test
    public void testInvalidLoginCredentials() throws NoSuchFieldException, IllegalAccessException {
        Login_Page loginPage = new Login_Page();
        setTextFieldValue(loginPage, "jTextField1", "nag");
        setPasswordFieldValue(loginPage, "jPasswordField1", "Ss@02190220");
        clickOkButton(loginPage);
        // TODO: Add assertions based on the expected behavior after an unsuccessful login
    }

    @Test
    public void testEmptyUsername() throws NoSuchFieldException, IllegalAccessException {
        Login_Page loginPage = new Login_Page();
        setTextFieldValue(loginPage, "jTextField1", "");
        setPasswordFieldValue(loginPage, "jPasswordField1", "Ss@02190220");
        clickOkButton(loginPage);
        // TODO: Add assertions based on the expected behavior for an empty username
    }

    @Test
    public void testEmptyPassword() throws NoSuchFieldException, IllegalAccessException {
        Login_Page loginPage = new Login_Page();
        setTextFieldValue(loginPage, "jTextField1", "Soumith");
        setPasswordFieldValue(loginPage, "jPasswordField1", "");
        clickOkButton(loginPage);
        // TODO: Add assertions based on the expected behavior for an empty password
    }

    @Test
    public void testBackButton() throws NoSuchFieldException, IllegalAccessException {
        Login_Page loginPage = new Login_Page();
        clickButton(loginPage, "jButton2");
        // TODO: Add assertions based on the expected behavior when the "Back" button is clicked
    }

    // TODO: Add more test cases as needed

    // Helper methods to set the value of private fields and click buttons using reflection
    private void setTextFieldValue(Login_Page loginPage, String fieldName, String value)
            throws NoSuchFieldException, IllegalAccessException {
        Field field = Login_Page.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        ((javax.swing.JTextField) field.get(loginPage)).setText(value);
    }

    private void setPasswordFieldValue(Login_Page loginPage, String fieldName, String value)
            throws NoSuchFieldException, IllegalAccessException {
        Field field = Login_Page.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        ((javax.swing.JPasswordField) field.get(loginPage)).setText(value);
    }

    private void clickButton(Login_Page loginPage, String fieldName)
            throws NoSuchFieldException, IllegalAccessException {
        Field field = Login_Page.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        ((javax.swing.JButton) field.get(loginPage)).doClick();
    }

    private void clickOkButton(Login_Page loginPage)
            throws NoSuchFieldException, IllegalAccessException {
        Field field = Login_Page.class.getDeclaredField("jButton1");
        field.setAccessible(true);
        ((javax.swing.JButton) field.get(loginPage)).doClick();
    }
}

