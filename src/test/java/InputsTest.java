import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class InputsTest {

    WebDriver driver;

    @BeforeMethod
    public void setup(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://the-internet.herokuapp.com/inputs");
    }

    @Test
    public void checkArrowUp(){
        SoftAssert softAssert = new SoftAssert();
        String valueNumber = driver.findElement(By.xpath("//*[@class='example']/input")).getAttribute("value");
        softAssert.assertEquals(valueNumber,"");
        driver.findElement(By.xpath("//*[@class='example']/input")).sendKeys(Keys.ARROW_UP);
        valueNumber = driver.findElement(By.xpath("//*[@class='example']/input")).getAttribute("value");
        softAssert.assertEquals(valueNumber,"1");
        softAssert.assertAll();
    }

    @Test
    public void checkArrowDown(){
        SoftAssert softAssert = new SoftAssert();
        String valueNumber = driver.findElement(By.xpath("//*[@class='example']/input")).getAttribute("value");
        softAssert.assertEquals(valueNumber,"");
        driver.findElement(By.xpath("//*[@class='example']/input")).sendKeys(Keys.ARROW_DOWN);
        valueNumber = driver.findElement(By.xpath("//*[@class='example']/input")).getAttribute("value");
        softAssert.assertEquals(valueNumber,"-1");
        softAssert.assertAll();
    }

    @Test
    public void checkInputDigitAndArrowDown(){
        SoftAssert softAssert = new SoftAssert();
        String valueNumber = driver.findElement(By.xpath("//*[@class='example']/input")).getAttribute("value");
        softAssert.assertEquals(valueNumber,"");

        driver.findElement(By.xpath("//*[@class='example']/input")).sendKeys("-1");
        valueNumber = driver.findElement(By.xpath("//*[@class='example']/input")).getAttribute("value");
        softAssert.assertEquals(valueNumber,"-1");

        driver.findElement(By.xpath("//*[@class='example']/input")).sendKeys(Keys.ARROW_DOWN);
        valueNumber = driver.findElement(By.xpath("//*[@class='example']/input")).getAttribute("value");
        softAssert.assertEquals(valueNumber,"-2");

        driver.findElement(By.xpath("//*[@class='example']/input")).sendKeys("1");
        valueNumber = driver.findElement(By.xpath("//*[@class='example']/input")).getAttribute("value");
        softAssert.assertEquals(valueNumber,"-21");

        driver.findElement(By.xpath("//*[@class='example']/input")).sendKeys(Keys.ARROW_DOWN);
        valueNumber = driver.findElement(By.xpath("//*[@class='example']/input")).getAttribute("value");
        softAssert.assertEquals(valueNumber,"-22");

        softAssert.assertAll();
    }

    @Test
    public void checkInputLetter(){
        driver.findElement(By.xpath("//*[@class='example']/input")).sendKeys("d");
        String valueNumber = driver.findElement(By.xpath("//*[@class='example']/input")).getAttribute("value");
        Assert.assertEquals(valueNumber,"");
    }

    @AfterMethod(alwaysRun = true)
    public void quit(){
        driver.quit();
    }
}
