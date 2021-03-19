package OrangeHRM.Tests;

import OrangeHRM.Pages.LoginPage;
import OrangeHRM.Pages.MainPage;
import OrangeHRM.Utilities.BrowserUtils;
import OrangeHRM.Utilities.ConfigurationReader;
import OrangeHRM.Utilities.Driver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginPagePositiveTest extends TestBase{

    @Test(dataProvider = "LoginData")
    public void test1(String username, String password) throws InterruptedException {
        new LoginPage()
                .enterUserName(username)
                .enterPassword(password)
                .clickLoginBtn().clickMyProfile();
        Thread.sleep(1000);
        BrowserUtils.clickNewTab(new MainPage().aboutLink);
        Thread.sleep(2000);
        //String chor = Keys.chord(Keys.CONTROL,Keys.PAGE_DOWN);
        BrowserUtils.goToNewTab(new MainPage().aboutLink);
        Assert.assertEquals(Driver.get().getTitle(), "OrangeHRM");
    }


    @DataProvider(name = "LoginData", parallel = false)
    public Object[][] loginData(){

        return new Object[][]{
                {"Admin","admin123"},
        };
    }
}
