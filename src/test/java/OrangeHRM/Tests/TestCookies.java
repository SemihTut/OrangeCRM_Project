package OrangeHRM.Tests;

import OrangeHRM.Pages.LoginPage;
import OrangeHRM.Utilities.ConfigurationReader;
import OrangeHRM.Utilities.Driver;
import org.openqa.selenium.Cookie;
import org.testng.annotations.Test;

import java.util.Set;

public class TestCookies extends TestBase{
    @Test
    public void myCookies(){
        new LoginPage().enterUserName(ConfigurationReader.get("username"))
                       .enterPassword(ConfigurationReader.get("password"))
                       .clickLoginBtn();
        Cookie domain = Driver.get().manage().getCookieNamed("domain");
        System.out.println("domain.toString() = " + domain.toString());
        Set<Cookie> myCookie = Driver.get().manage().getCookies();
        myCookie.forEach(System.out::print);

    }
}
