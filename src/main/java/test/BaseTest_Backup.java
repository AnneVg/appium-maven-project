package test;

import driver.DriverFactoryEx;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BaseTest_Backup {
    // Thread-safe
    // 1. SynchronizedList
    // 2. LocalThread | isolate appium threads
    private final static List<DriverFactoryEx> driverThreadPool = Collections.synchronizedList(new ArrayList<>());
    private static ThreadLocal<DriverFactoryEx> driverThread;

    @BeforeSuite(alwaysRun = true)
    public static void beforeSuite() {
        // () -> {} anonymous function
        driverThread = ThreadLocal.withInitial(() -> {
            DriverFactoryEx driverThread = new DriverFactoryEx();
            driverThreadPool.add(driverThread);
            return driverThread;
        });
    }

    @AfterSuite(alwaysRun = true)
    public static void afterSuite() {
        for (DriverFactoryEx webDriverThread : driverThreadPool) {
            webDriverThread.quitAppiumSesion();
        }
    }

    public static AppiumDriver<MobileElement> getDriver() {
        return driverThread.get().getAppiumDriver();
    }
}
