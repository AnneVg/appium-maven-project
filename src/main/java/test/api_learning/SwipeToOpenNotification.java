package test.api_learning;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SwipeToOpenNotification {
    public static void main(String[] args) {
        DriverFactory.startAppiumServer();
        try {
            AppiumDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();

            // Get mobile screen size
            Dimension windowsSize = androidDriver.manage().window().getSize();
            int screenHeight = windowsSize.getHeight();
            int screenWidth = windowsSize.getWidth();
            // caculate touch point
            int xStartPoint = (50 * screenWidth) / 100;
            int xEndPoint = xStartPoint;
            int yStartPoint = 0 ;
            int yEndPoint = (50 * screenHeight) / 100;
            // Convert to PointOption - Coordinates
            PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
            PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);
            // Perform touch action
            TouchAction touchAction = new TouchAction(androidDriver);

            //Swipe down to open notification
                touchAction
                        .press(startPoint)
                        .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1)))
                        .moveTo(endPoint)
                        .release()
                        .perform();
                Thread.sleep(2000);

                // Get the info inside the notification by getting a list
                List<MobileElement> notificationElems = androidDriver.findElements(By.id("android:id/notification_main_column"));
                if(notificationElems.isEmpty())
                    throw  new RuntimeException("Notification List is empty");
                Map<String, String> notificationList = new HashMap<>();

                notificationElems.forEach(notification ->{
                    //System.out.println(notification.findElement(By.id("android:id/title")).getText());

                    String notificationTitle = notification.findElement(By.id("android:id/title")).getText();
                   // System.out.println(notificationTitle);
                  //  String notificationTitle = notification.findElements(By.className("android.widget.TextView")).get(1).getText();

                    String notificationContent = notification.findElements(By.className("android.widget.TextView")).get(1).getText();
                    //System.out.println(notificationContent);
                    notificationList.put(notificationTitle, notificationContent);
                });
                touchAction
                        .press(endPoint)
                        .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1)))
                        .moveTo(startPoint)
                        .release()
                        .perform();
                notificationList.keySet().forEach(key -> System.out.println(key + ": " + notificationList.get(key)));



        } catch (Exception e) {
            e.printStackTrace();

        } finally {
        //    DriverFactory.stopAppiumServer();

        }
    }


}
