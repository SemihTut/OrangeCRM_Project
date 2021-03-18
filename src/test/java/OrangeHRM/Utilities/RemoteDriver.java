package OrangeHRM.Utilities;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class RemoteDriver {

    private RemoteDriver(){

    }
    private static InheritableThreadLocal<WebDriver> driverPool = new InheritableThreadLocal<>();
    public static WebDriver getRemoteDriver(String browser) {
        if (driverPool.get() == null) {
//            if we pass the driver from terminal then use that one
//           if we do not pass the driver from terminal then use the one properties file
        switch (browser) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setCapability("platform", Platform.ANY);
                try {
                    //https://stackoverflow.com/questions/44614849/unable-to-see-selenium-grid-running-when-viewed-from-browser
                    //$ docker-machine ip
                    //http://192.168.99.100:4444/wd/selenium-hub
                    driverPool.set(new RemoteWebDriver(new URL("http://192.168.99.100:4444/wd/hub"), chromeOptions));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;

            case "firefox":
                FirefoxOptions ffOptions = new FirefoxOptions();
                ffOptions.setCapability("platform", Platform.ANY);
                try {
                    driverPool.set(new RemoteWebDriver(new URL("http://192.168.99.100:4444/wd/hub"), ffOptions));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
        return driverPool.get();
    }

    public static void closeRemoteDriver() {
        driverPool.get().quit();
        //to prevent from memory leaks
        driverPool.remove();
    }
}
