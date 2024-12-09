import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class AddRemoveElementsTest {

    WebDriver driver;

    @BeforeMethod
    public void setup(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
//        options.addArguments("headless");
//        options.addArguments("disable-notification");
//        options.addArguments("incognito");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");
    }

    @Test
    public void checkAddRemoveElements(){
        SoftAssert softAssert = new SoftAssert();
        int quantityButtonDelete = driver.findElements(By.xpath("//*[@id='elements']/button")).size();
        softAssert.assertEquals(quantityButtonDelete,0);

        driver.findElement(By.xpath("//button[text()='Add Element']")).click();
        driver.findElement(By.xpath("//button[text()='Add Element']")).click();
        quantityButtonDelete = driver.findElements(By.xpath("//*[@id='elements']/button")).size();
        softAssert.assertEquals(quantityButtonDelete,2);

        driver.findElement(By.xpath("//*[@id='elements']/button[1]")).click();
        quantityButtonDelete = driver.findElements(By.xpath("//*[@id='elements']/button")).size();
        softAssert.assertEquals(quantityButtonDelete,1);
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void quit(){
        driver.quit();
    }
}
