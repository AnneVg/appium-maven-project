package models.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import models.authentication.LoginDialogComponent;
import models.components.global.BottomNavComponent;
import org.openqa.selenium.By;

public class LoginPage {
    private AppiumDriver<MobileElement> appiumDriver;
    private static final By usernameSel = MobileBy.AccessibilityId("input-email");
    private static final By passwordSel = MobileBy.AccessibilityId("input-password");
    private static final By loginBtnSel = MobileBy.AccessibilityId("button-LOGIN");
    private BottomNavComponent bottomNavComp;
    private LoginDialogComponent loginDialogComponent;

    public LoginPage(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }
    @Step("Input username as {username}")
    public LoginPage inputUsername(String username) {
        appiumDriver.findElement(usernameSel).sendKeys(username);
        return this;
    }
    @Step("Input username as {password}")
    public LoginPage inputPassword(String password) {
        appiumDriver.findElement(passwordSel).sendKeys(password);
        return this;
    }
    @Step("Click on login Button")

    public LoginPage clickOnLoginBtn() {
        appiumDriver.findElement(loginBtnSel).click();
        return this;
    }


    public BottomNavComponent bottomNavComp() {
        return new BottomNavComponent(appiumDriver);
    }

    public LoginDialogComponent loginDialogComp() {
        return new LoginDialogComponent(appiumDriver);
    }

}
