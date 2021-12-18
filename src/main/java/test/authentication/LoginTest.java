package test.authentication;

import driver.DriverFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Description;
import models.authentication.LoginDialogComponent;
import models.components.global.BottomNavComponent;
import models.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.BaseTest;
import test_data.LoginCreds;
import test_data.authentication.DataObjectBuilder;

public class LoginTest extends BaseTest {
        @Description("Test login with Data Driven")
        @Test(dataProvider = "loginCredsData", description = "Login Test")
    public  void loginWithCorrectCreds(LoginCreds loginCreds){
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
            loginPage.inputUsername(loginCreds.getUsername());
            loginPage.inputPassword(loginCreds.getPassword());
            loginPage.clickOnLoginBtn();
            String loginMsg = loginPage.loginDialogComp().msgTitle();
            System.out.println(loginMsg);
            // Verification
            String actualLoginMsg = loginPage.loginDialogComp().msgTitle();
            boolean isTitleCorrect = actualLoginMsg.equals("success");
            String customErrMsg = "[ERR] Login msg title incorrenct";
            Assert.assertTrue(isTitleCorrect,customErrMsg);
            Assert.assertEquals(actualLoginMsg,"Successs",customErrMsg + "| assertEquals");
            System.out.println(actualLoginMsg);

        } catch (Exception ignored) {

        } finally {
            DriverFactory.stopAppiumServer();
        }
        //Relative xPath
    }
    @DataProvider
    public LoginCreds[] loginCredsData(){

        String jsonLoc = "/src/main/resources/test-data/loginCreds.json";
        return DataObjectBuilder.buildDataObject(jsonLoc,LoginCreds[].class);
        //return DataObjectBuilder.buildLoginCreds(jsonLoc);

        };

    }

