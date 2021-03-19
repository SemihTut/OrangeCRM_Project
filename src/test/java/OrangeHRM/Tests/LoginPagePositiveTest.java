package OrangeHRM.Tests;

import OrangeHRM.Pages.LoginPage;
import OrangeHRM.Pages.MainPage;
import OrangeHRM.Utilities.BrowserUtils;
import OrangeHRM.Utilities.ConfigurationReader;
import OrangeHRM.Utilities.Driver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginPagePositiveTest extends TestBase{

    @Test(dataProvider = "LoginData")
    public void test1(String username, String password){
        new LoginPage()
                .enterUserName(username)
                .enterPassword(password)
                .clickLoginBtn().clickMyProfile();

        Actions newTab = new Actions(driver);
        newTab.keyDown(Keys.CONTROL).click(link).keyUp(Keys.CONTROL).build().perform();
        String chord = Keys.chord(Keys.CONTROL, Keys.ENTER);
        Driver.get().findElement(new MainPage().aboutLink).sendKeys(chord);

        Driver.get().switchTo().activeElement().sendKeys(Keys.CONTROL,Keys.TAB);

        Assert.assertEquals(Driver.get().getTitle(), "OrangeHRM");
    }


    @DataProvider(name = "LoginData", parallel = true)
    public Object[][] loginData(){

        return new Object[][]{
                {"Admin","admin123"},
        };
    }
}
