package stepdefs;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class Hooks {
    @Before
    public void before(){
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver_win32\\Chromedriver.exe");
        Configuration.timeout = 3000;
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-save-password-bubble");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
        WebDriverRunner.setWebDriver(driver);
        System.out.println("Начало теста");
    }
    @After
    public void after(){
        WebDriverRunner.closeWebDriver();
        System.out.println("Конец теста");
    }
}