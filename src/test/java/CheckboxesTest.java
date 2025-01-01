import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class CheckboxesTest {

    WebDriver driver;

    @BeforeMethod
    public void setup(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://the-internet.herokuapp.com/checkboxes");
    }

    @Test
    public void checkCheckboxUncheckedChecked(){
        SoftAssert softAssert = new SoftAssert();
        boolean checkboxChecked = driver.findElement(By.xpath("//*[@id='checkboxes']/input[1]")).isSelected();
        softAssert.assertFalse(checkboxChecked);

        driver.findElement(By.xpath("//*[@id='checkboxes']/input[1]")).click();
        checkboxChecked = driver.findElement(By.xpath("//*[@id='checkboxes']/input[1]")).isSelected();
        softAssert.assertTrue(checkboxChecked);
        softAssert.assertAll();
    }

    @Test
    public void checkCheckboxCheckedUnchecked(){
        SoftAssert softAssert = new SoftAssert();
        boolean checkboxChecked = driver.findElement(By.xpath("//*[@id='checkboxes']/input[2]")).isSelected();
        softAssert.assertTrue(checkboxChecked);

        driver.findElement(By.xpath("//*[@id='checkboxes']/input[2]")).click();
        checkboxChecked = driver.findElement(By.xpath("//*[@id='checkboxes']/input[2]")).isSelected();
        softAssert.assertFalse(checkboxChecked);
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void quit(){
        driver.quit();
    }
}
