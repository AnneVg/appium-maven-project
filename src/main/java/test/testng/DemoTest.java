package test.testng;
import org.testng.annotations.*;

public class DemoTest {

    @BeforeTest
    public void beforeTest(){
        System.out.println("DemoTest| before Test");
    }
    @BeforeClass
    public void beforeClass() {
        System.out.println("DemoTest| before class");
    }
    @Test
    public void loginCorrectDemoTest(){
        System.out.println("loginDemoCorrectCredes");

    }
    @Test
    public void loginInCorrectDemoTest(){
        System.out.println("loginDemoIncorrectCreds");
    }
    @AfterTest
    public void afterTest(){
        System.out.println("DemoTest| after Test");
    }
    @AfterClass
    public void afterClass(){
        System.out.println("DemoTest| after class");
    }
}
