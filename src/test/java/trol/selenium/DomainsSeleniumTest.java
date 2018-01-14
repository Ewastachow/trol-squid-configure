package trol.selenium;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.concurrent.TimeUnit;


import static org.junit.Assert.*;



public class DomainsSeleniumTest {
    private final String TROL_URL = "http://localhost:8090/";
    private final String TROL_URL_ADD_BAD_DOMAIN = "http://localhost:8090/domains/list/3";


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
        WebElement addBtn = driver.findElementByXPath("//*[@id=\"domains-title\"]/h2/a");
        addBtn.click();
        assertTrue(driver.getCurrentUrl().contains("domains/add"));
    }

    @Test
    public void badDomainFormatTest(){
        driver.get(TROL_URL_ADD_BAD_DOMAIN);

        WebDriverWait wait = new WebDriverWait(driver,4000);
        WebElement addedDomain = driver.findElementByXPath("//*[@id=\"domains-table\"]/tbody/tr[1]/td[1]/div");
        WebElement domainsString = driver.findElementById("domainString");
        WebElement saveBtn = driver.findElementByXPath("//*[@id=\"add-domain\"]/form/button");
        WebElement alertMessage = driver.findElementByXPath("//*[@id=\"add-domain\"]/form/div");

        domainsString.sendKeys("bad domain.pl");
        saveBtn.click();

        wait.until(ExpectedConditions.
                visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"add-domain\"]/form/div")));
        assertNotEquals("bad domain.pl",alertMessage.getText());
    }

    @AfterClass
    public static void finish(){
        driver.close();
    }
}
