package driver;

import caps.MobileCapabilityTypeEx;
import flags.AndroidServerFlagEx;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class DriverFactoryEx {
    private AppiumDriver<MobileElement> appiumDriver;
    private static AppiumDriverLocalService appiumServer;

    // SOLID | Single Responsibility
    public AppiumDriver<MobileElement> getAppiumDriver() {
        if (appiumDriver == null)
            appiumDriver = initAppiumDriver();
        return appiumDriver;
    }


    public static void stopAppiumServer() {
        String killNodeWindowCmd = "taskkill /F /IM node.exe";
        String killNodeLinuxCmd = "killall node";
        String currentOS = System.getProperty("os.name").toLowerCase();
        String killNodeCmd = currentOS.startsWith("windows") ? killNodeWindowCmd : killNodeLinuxCmd;
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec(killNodeCmd);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private AppiumDriver<MobileElement> initAppiumDriver() {
        AppiumServiceBuilder appiumServiceBuilder = new AppiumServiceBuilder();
        appiumServiceBuilder.withArgument(AndroidServerFlagEx.ALLOW_INSECURE, "chromedriver_autodownload");
        appiumServiceBuilder.withIPAddress("127.0.0.1").usingAnyFreePort();
        appiumServer = AppiumDriverLocalService.buildService(appiumServiceBuilder);
        appiumServer.start();
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.PLATFORM_NAME, "Android");
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.AUTOMATION_NAME, "uiautomator2");
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.UDID, "emulator-5554");
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.APP_PACKAGE, "com.wdiodemoapp");
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.APP_ACTIVITY, "com.wdiodemoapp.MainActivity");
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.NEW_COMMAND_TIMEOUT, 120);
        appiumDriver = new AndroidDriver<>(appiumServer.getUrl(), desiredCapabilities);
        //implicitlyWait:a ch??? t?????ng minh - th???i gian t???i ??a ch??? l?? 30s. thg apply locally
        //ExplicitlyWait: ch??? t?????ng minh cho c??i n??o ????, v?? d??? t???i ??a ch??? 45s cho 1 ?????i t?????ng
        appiumDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
        return appiumDriver;
    }

    public void quitAppiumSesion() {
        if (appiumDriver != null) {
            appiumDriver.quit();
            appiumDriver = null;
            // When we have final infrastructure for parallel testing, this one is not neccessay
            stopAppiumServer();

        }
    }
}
