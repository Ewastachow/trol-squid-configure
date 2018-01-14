package trol.selenium;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;
public class DomainsSeleniumTest {
    private final String TROL_URL = "http://localhost:8090/";

    private static ChromeDriver driver;

    @BeforeClass
    public static void init(){
        System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void loginTest(){
        driver.get(TROL_URL);

        WebElement usernameInput = driver.findElementById("username");
        usernameInput.clear();
        usernameInput.sendKeys("admin");

        WebElement passwordInput = driver.findElementById("password");
        passwordInput.clear();
        passwordInput.sendKeys("admin1");

        WebElement submitButton = driver.findElementById("submit");
        submitButton.click();

        assertFalse(driver.getCurrentUrl().contains("login"));
    }

    @Test
    public void goToAddDomainsListFormTest(){
        WebElement domainsBtn = driver.findElementById("domainslistbtn");
        domainsBtn.click();

        WebElement addBtn = driver.findElementByCssSelector(
                "#domains-title > h2 > a"
        );
        addBtn.click();

        assertTrue(driver.getCurrentUrl().contains("domains/add"));
    }


    @AfterClass
    public static void finish(){
        driver.close();
    }
}
