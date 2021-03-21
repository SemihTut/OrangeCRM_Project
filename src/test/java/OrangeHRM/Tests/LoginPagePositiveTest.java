package OrangeHRM.Tests;

import OrangeHRM.Pages.LoginPage;
import OrangeHRM.Pages.MainPage;
import OrangeHRM.Utilities.BrowserUtils;
import OrangeHRM.Utilities.ConfigurationReader;
import OrangeHRM.Utilities.Driver;
import OrangeHRM.Utilities.ExcelUtil;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginPagePositiveTest extends TestBase {

    int count = 1;
    ExcelUtil loginDatas = new ExcelUtil("src/test/resources/dataProvider.xlsx", "Sheet1");
    @Test(dataProvider = "LoginData")
    public void test1(String username, String password, String result) throws InterruptedException {

            new LoginPage()
                    .enterUserName(username)
                    .enterPassword(password)
                    .clickLoginBtn();

            BrowserUtils.waitForVisibility(new MainPage().myProfile, 5);
            if(Driver.get().findElement(new MainPage().myProfile).isDisplayed()){
                loginDatas.setCellData("YES", "Result", count);
            }else{
                loginDatas.setCellData("NO", "Result", count);
            }
        System.out.println("count++ = " + count++);
    }


    @DataProvider(name = "LoginData", parallel = true)
    public Object[][] loginData() {
        return loginDatas.getDataArrayWithoutFirstRow();

    }
}
