package com.deepti.scrumsimulator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.lang.reflect.Field;
import javax.swing.JButton;

public class Registration_PageTest {

    @Test
    public void testNewUserButtonClicked() throws NoSuchFieldException, IllegalAccessException {
        Registration_Page registrationPage = new Registration_Page();
        clickButton(registrationPage, "jButton1");

        // TODO: Add assertions based on the expected behavior when the "New User" button is clicked
    }

    @Test
    public void testExistingUserButtonClicked() throws NoSuchFieldException, IllegalAccessException {
        Registration_Page registrationPage = new Registration_Page();
        clickButton(registrationPage, "jButton2");

        // TODO: Add assertions based on the expected behavior when the "Existing User" button is clicked
    }

    // Helper method to click buttons using reflection
    private void clickButton(Registration_Page registrationPage, String fieldName)
            throws NoSuchFieldException, IllegalAccessException {
        Field field = Registration_Page.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        JButton button = (JButton) field.get(registrationPage);
        button.getActionListeners()[0].actionPerformed(new ActionEvent(button, ActionEvent.ACTION_PERFORMED, ""));
    }
}
