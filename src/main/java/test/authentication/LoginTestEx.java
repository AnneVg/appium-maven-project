package test.authentication;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.authentication.LoginDialogComponent;
import models.components.global.BottomNavComponent;
import models.pages.LoginPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import test.BaseTest;

public class LoginTestEx extends BaseTest {
        private SoftAssert softAssert;
@Test()
        public  void loginWithCorrectCreds(){

            //Init driver
            AppiumDriver<MobileElement> androidDriver = getDriver();
            // Login Page
            LoginPage loginPage = new LoginPage(androidDriver);
            // Bottom Nav Comp
            BottomNavComponent bottomNavComponent = loginPage.bottomNavComp();
            LoginDialogComponent loginDialogComponent = loginPage.loginDialogComp();
            bottomNavComponent.loginLabelElem().click();
            bottomNavComponent.formLabelElem().click();
            bottomNavComponent.clickOnLoginLabel();
            // Fill login form
            loginPage.inputUsername("test@gmail.com");
            loginPage.inputPassword("12345678");
            loginPage.clickOnLoginBtn();
            String loginMsg = loginPage.loginDialogComp().msgTitle();
            System.out.println(loginMsg);
        // Verification
            String actualLoginMsg = loginPage.loginDialogComp().msgTitle();
            boolean isTitleCorrect = actualLoginMsg.equals("success");
            String customErrMsg = "[ERR] Login msg title incorrenct";
            softAssert.assertTrue(isTitleCorrect,customErrMsg);
            softAssert.assertEquals(actualLoginMsg,"Successs",customErrMsg + "| assertEquals");
            System.out.println(actualLoginMsg);

        //Relative xPath
    }

    }

