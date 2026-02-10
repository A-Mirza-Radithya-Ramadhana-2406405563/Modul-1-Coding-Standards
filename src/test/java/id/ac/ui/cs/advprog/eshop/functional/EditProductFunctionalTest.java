package id.ac.ui.cs.advprog.eshop.functional;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EditProductFunctionalTest extends BaseFunctionalTest {
    @Test
    void editProductFlow_isCorrect(ChromeDriver driver) throws Exception {
        String createUrl = baseUrl + "/product/create";
        driver.get(createUrl);

        WebElement productNameInput = driver.findElement(By.id("nameInput"));
        productNameInput.clear();
        productNameInput.sendKeys("Sampo cap Test");

        WebElement productQuantityInput = driver.findElement(By.id("quantityInput"));
        productQuantityInput.clear();
        productQuantityInput.sendKeys("61");

        driver.findElement(By.tagName("form")).submit();

        driver.findElement(By.linkText("Edit")).click();

        assertTrue(driver.getCurrentUrl().contains("/product/edit"));

        productNameInput = driver.findElement(By.id("nameInput"));
        productNameInput.clear();
        productNameInput.sendKeys("Sampo cap Edit");

        productQuantityInput = driver.findElement(By.id("quantityInput"));
        productQuantityInput.clear();
        productQuantityInput.sendKeys("16");

        driver.findElement(By.tagName("form")).submit();

        String pageSource = driver.getPageSource();
        assertTrue(pageSource.contains("Sampo cap Edit"));
        assertTrue(pageSource.contains("16"));
    }
}
