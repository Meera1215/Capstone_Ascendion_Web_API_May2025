package CrossBrowser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import utility.Constant;

public class BaseTestCrossBrowser {
    public WebDriver driver;

    @Parameters("br_info")
    @BeforeTest
    public void launchApp(String browser) throws Exception {
        System.out.println("Launching browser: " + browser);

        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else {
            driver = new ChromeDriver(); // default fallback
        }

        driver.manage().window().maximize();
        driver.get(Constant.demoblaze_appURL);
        Thread.sleep(3000);
    }

    @AfterTest
    public void closeApp() throws Exception {
        Thread.sleep(3000);
        driver.quit();
    }
}
