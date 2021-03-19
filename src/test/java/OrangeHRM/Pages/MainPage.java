package OrangeHRM.Pages;

import OrangeHRM.Utilities.BrowserUtils;
import OrangeHRM.Utilities.Driver;
import org.openqa.selenium.By;

public final class MainPage {

    public final By myProfile = By.id("welcome");
    public final By logOutBtn = By.linkText("Logout");
    public final By aboutLink = By.id("aboutDisplayLink");

    public MainPage clickMyProfile(){
        BrowserUtils.waitForClickablility(myProfile,5);
        Driver.get().findElement(myProfile).click();
        return this;

    }

    public LoginPage clickLogOutBtn(){
        BrowserUtils.waitForClickablility(logOutBtn,5);
        Driver.get().findElement(logOutBtn).click();
        return new LoginPage();
    }

    public LoginPage logOut(){
        return clickMyProfile().clickLogOutBtn();
    }
}
