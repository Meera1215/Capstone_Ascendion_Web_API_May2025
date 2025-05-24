package CrossBrowser;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DemoBlaze_Test extends BaseTestCrossBrowser {

    @Test
    public void completePurchaseFlow() throws InterruptedException {
        
        // Click on a product (e.g., Samsung Galaxy S6)
        driver.findElement(By.linkText("Samsung galaxy s6")).click();
        Thread.sleep(2000);

        // Click 'Add to cart'
        driver.findElement(By.linkText("Add to cart")).click();
        Thread.sleep(3000);
        Alert alert = driver.switchTo().alert();
        String alertMessage = alert.getText();
        System.out.println("Alert Text: " + alertMessage);
        alert.accept();
        
        // Navigate to cart
        driver.findElement(By.id("cartur")).click();
        Thread.sleep(2000);

        // Click 'Place Order'
        driver.findElement(By.cssSelector("button[data-target='#orderModal']")).click();
        Thread.sleep(2000);

        // Fill order form
        driver.findElement(By.id("name")).sendKeys("John Doe");
        driver.findElement(By.id("country")).sendKeys("USA");
        driver.findElement(By.id("city")).sendKeys("New York");
        driver.findElement(By.id("card")).sendKeys("1234567890");
        driver.findElement(By.id("month")).sendKeys("May");
        driver.findElement(By.id("year")).sendKeys("2025");

        // Click 'Purchase'
        driver.findElement(By.xpath("//button[text()='Purchase']")).click();
        Thread.sleep(3000);

        // Validate confirmation
        boolean thankYouDisplayed = driver.findElement(By.cssSelector(".sweet-alert h2")).isDisplayed();
        Assert.assertTrue(thankYouDisplayed, "Order not confirmed");

        System.out.println("Order placed successfully.");
    }
}
