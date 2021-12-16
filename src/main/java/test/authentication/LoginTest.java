package test.authentication;

import driver.DriverFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import models.authentication.LoginDialogComponent;
import models.components.global.BottomNavComponent;
import models.pages.LoginPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test_data.LoginCreds;
import test_data.authentication.DataObjectBuilder;

public class LoginTest {
        @Test(dataProvider = "loginCredsData")
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

