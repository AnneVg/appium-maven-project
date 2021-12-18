package models.components.global;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;

public class BottomNavComponent {
    private final AppiumDriver<MobileElement> appiumDriver;
    private static final MobileBy loginLabelSel = (MobileBy) MobileBy.AccessibilityId("Login");
    private static final MobileBy formLabelSel = (MobileBy) MobileBy.AccessibilityId("Forms");
    private static final MobileBy swipeLabelSel = (MobileBy) MobileBy.AccessibilityId("Swipe");

    public BottomNavComponent(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    // return Mobile Element
    public MobileElement loginLabelElem() {
        return appiumDriver.findElement(loginLabelSel);
    }

    public MobileElement formLabelElem() {
        return appiumDriver.findElement(formLabelSel);
    }

    public MobileElement swipeLabelElem() {
        return appiumDriver.findElement(swipeLabelSel);
    }

    // Main interation method
    @Step ("Click on login label")
    public void clickOnLoginLabel() {
        appiumDriver.findElement(loginLabelSel).click();
    }
}
