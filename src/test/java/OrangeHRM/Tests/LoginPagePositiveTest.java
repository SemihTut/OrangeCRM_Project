package OrangeHRM.Tests;

import OrangeHRM.Pages.LoginPage;
import OrangeHRM.Pages.MainPage;
import OrangeHRM.Utilities.BrowserUtils;
import OrangeHRM.Utilities.ConfigurationReader;
import OrangeHRM.Utilities.Driver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPagePositiveTest extends TestBase{

    @Test
    public void test1(){
        new LoginPage()
                .enterUserName(ConfigurationReader.get("username"))
                .enterPassword(ConfigurationReader.get("password"))
                .clickLoginBtn().logOut();

        Assert.assertEquals(Driver.get().getTitle(), "OrangeHRM");
    }

    @Test
    public void test2(){
        new LoginPage()
                .enterUserName(ConfigurationReader.get("username"))
                .enterPassword(ConfigurationReader.get("password"))
                .clickLoginBtn().logOut();

        Assert.assertEquals(Driver.get().getTitle(), "OrangeHRM");
    }
}
