package test.testng;

import org.testng.annotations.*;

public class SignUpTest {
    @BeforeTest
    public void beforeTest(){
        System.out.println("SignUpTest |before Test");
    }
    @BeforeClass
    public void beforeClass() {
        System.out.println("SignUpTest| before class");
    }
    @Test
    public void signupCorrectEmail(){
        System.out.println("signupCorrectEmail");

    }
    @Test
    public void signupInCorrectEmail(){
        System.out.println("signupInCorrectEmail");
    }
    @AfterMethod
    public void afterMethod(){
        System.out.println("SignUpTest| after Method");
    }
    @AfterTest
    public void afterTest(){
        System.out.println("SignUpTest| after Test");
    }
    @AfterClass
    public void afterClass(){
        System.out.println("SignUpTest| after class");
    }
}
