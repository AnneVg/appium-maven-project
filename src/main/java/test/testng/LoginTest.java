package test.testng;
import org.testng.annotations.*;

public class LoginTest {

    @BeforeTest
    public void beforeTest(){
        System.out.println("LoginTest| before Test");
    }
    @BeforeClass
    public void beforeClass(){
        System.out.println("LoginTest| before class");
    }
    @Test
    public void loginCorrectCredens(){
        System.out.println("loginCorrectCredes");

    }
    @Test
    public void loginInCorrectCredens(){
        System.out.println("loginIncorrectCreds");
    }

    @AfterTest
    public void afterTest(){
        System.out.println("LoginTest| after Test");
    }
    @AfterClass
    public void afterClass(){
        System.out.println("LoginTest| after class");
    }
}
