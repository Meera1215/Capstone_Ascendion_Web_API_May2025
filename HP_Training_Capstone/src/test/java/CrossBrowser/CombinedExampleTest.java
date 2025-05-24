package CrossBrowser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class CombinedExampleTest {

    @Test
    public void runAllExamples() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // ========== ACTION EXAMPLE ==========
        driver.get("https://demoqa.com/menu");
        WebElement mainItem = driver.findElement(By.xpath("//a[text()='Main Item 2']"));
        Actions act = new Actions(driver);
        act.moveToElement(mainItem).perform();
        System.out.println("Hovered on Main Item 2");

        // ========== IFRAME EXAMPLE ==========
        driver.get("https://demoqa.com/frames");
        driver.switchTo().frame("frame1"); // Switch to iframe by ID
        WebElement heading = driver.findElement(By.id("sampleHeading"));
        System.out.println("Text inside iframe: " + heading.getText());
        driver.switchTo().defaultContent(); // Come back to main page

        // ========== WINDOW HANDLING ==========
        driver.get("https://demoqa.com/browser-windows");
        driver.findElement(By.id("tabButton")).click(); // Opens new tab

        // Get window handles
        Set<String> allWindowHandles = driver.getWindowHandles();
        Iterator<String> iterator = allWindowHandles.iterator();
        String parentWindow = iterator.next();
        String childWindow = iterator.next();

        // Switch to new tab/window
        driver.switchTo().window(childWindow);
        Thread.sleep(2000);
        String newTabText = driver.findElement(By.id("sampleHeading")).getText();
        System.out.println("Text in new tab: " + newTabText);

        // Close child tab and switch back
        driver.close();
        driver.switchTo().window(parentWindow);

        // Final cleanup
        driver.quit();
    }
}
