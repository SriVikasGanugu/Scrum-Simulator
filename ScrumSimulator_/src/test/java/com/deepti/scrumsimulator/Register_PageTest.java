package com.deepti.scrumsimulator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;

public class Register_PageTest {

    @Test
    public void testValidRegistration() throws NoSuchFieldException, IllegalAccessException {
        Register_Page registerPage = new Register_Page();
        setTextFieldValue(registerPage, "jTextField1", "NagaS");
        setPasswordFieldValue(registerPage, "jPasswordField1", "StrongP@ssword");
        clickButton(registerPage, "jButton1");

        // TODO: Add assertions based on the expected behavior after a successful registration
    }

    @Test
    public void testExistingUsername() throws NoSuchFieldException, IllegalAccessException {
        Register_Page registerPage = new Register_Page();
        setTextFieldValue(registerPage, "jTextField1", "Soumith");
        setPasswordFieldValue(registerPage, "jPasswordField1", "Ss@02190220");
        clickButton(registerPage, "jButton1");

        // TODO: Add assertions based on the expected behavior when the username already exists
    }

   

    @Test
    public void testInvalidPassword() throws NoSuchFieldException, IllegalAccessException {
        Register_Page registerPage = new Register_Page();
        setTextFieldValue(registerPage, "jTextField1", "NagaS");
        setPasswordFieldValue(registerPage, "jPasswordField1", "weakpassword");
        clickButton(registerPage, "jButton1");

        // TODO: Add assertions based on the expected behavior for an invalid password
    }

    // TODO: Add more test cases as needed

    // Helper methods to set the value of private fields and click buttons using reflection
    private void setTextFieldValue(Register_Page registerPage, String fieldName, String value)
            throws NoSuchFieldException, IllegalAccessException {
        Field field = Register_Page.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        ((javax.swing.JTextField) field.get(registerPage)).setText(value);
    }

    private void setPasswordFieldValue(Register_Page registerPage, String fieldName, String value)
            throws NoSuchFieldException, IllegalAccessException {
        Field field = Register_Page.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        ((javax.swing.JPasswordField) field.get(registerPage)).setText(value);
    }

    private void clickButton(Register_Page registerPage, String fieldName)
            throws NoSuchFieldException, IllegalAccessException {
        Field field = Register_Page.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        ((javax.swing.JButton) field.get(registerPage)).doClick();
    }
}
