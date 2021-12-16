package test.authentication;

import driver.DriverFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import models.authentication.LoginDialogComponent;
import models.components.global.BottomNavComponent;
import models.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import test_data.LoginCreds;

public class LoginTestWithTestng {
    private SoftAssert softAssert;
    @BeforeClass
    public void beforeClass(){
        softAssert = new SoftAssert();
    }
    @AfterClass
    public void afterClass(){
        softAssert.assertAll();
    }
    @Ignore // ignore test loginWithCorrectcreds ..
    @Test (priority = 2)
    public void loginWithCorrectcreds(LoginCreds loginCreds) {
        DriverFactory.startAppiumServer();

        try {
            AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
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
            // Verification
            String actualLoginMsg = loginPage.loginDialogComp().msgTitle();
            boolean isTitleCorrect = actualLoginMsg.equals("success");
            String customErrMsg = "[ERR] Login msg title incorrenct";
            softAssert.assertTrue(isTitleCorrect,customErrMsg);
            softAssert.assertEquals(actualLoginMsg,"Successs",customErrMsg + "| assertEquals");
            System.out.println(actualLoginMsg);

        } catch (Exception exception) {
            exception.printStackTrace();

        } finally {
           // DriverFactory.stopAppiumServer();
        }
        //Relative xPath
    }
    @Test(priority = 1, dependsOnMethods = {"a3Void"}) // add dependsOnMethods: a1 only execute when a3 executed successfully

    void a1Void(){
        System.out.println("this would be execute first");
        System.out.println("add priority, this test would be execute after a2");
    }
    @Test
    void a2Void(){
        System.out.println("This would be execute second");
    }
    @Test
    void a3Void(){
        Assert.assertTrue(false);
    }
}
