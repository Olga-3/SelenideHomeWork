package org.example;

import java.util.*;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selectors.byClassName;
import static org.junit.Assert.assertTrue;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.Keys;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


public class AppTest {
 //   WebDriver driver;
    String login="qazqaz";
    String password="qaz1122";
    String randomMessage;

    /**
     * This method opens website
     */
    public void openWebsite() {
        System.out.println("Открываем сайт для тестирования");
        open("https://dev.n7lanit.ru/");
    }

    /**
     * here we log in and check if the authorization was successful and the avatar is displayed
     */
    public void logIn() {
        $(byText("Войти")).shouldBe(visible).click();//найти элемент по тексту и проверить видимость, кликнуть
        $(byText("Забыли пароль?")).shouldBe(visible);//найти элемент по тексту и проверить видимость
        $(byId("id_username")).sendKeys(login);//ввести логин
        $(byId("id_password")).sendKeys(password);//ввести пароль
        $(byXpath("//*[@id=\"modal-mount\"]/div/div/form/div[2]/button")).click(); // кликнуть на кнопку Войти
        $(byClassName("user-avatar")).shouldBe(visible);//проверить авторизацию-отображается ли аватар
    }

    /**
     * we add all topics on the page to a collection and pick one randomly
     * then we click to open the topic
     */
    public void openTopic() {
        ElementsCollection collection = $$(By.xpath("//li[@class='list-group-item thread-read']//a[@class='item-title thread-title']")).excludeWith(text("assessment"));
        collection.get((int) (collection.size() * Math.random())).click();//взять элемент (тема) под случайным номером в коллекции
    }

    /**
     * we click the button to add a message, check if a text area appears, add some randomly picked message
     * @throws InterruptedException
     */
    public void addMessage() throws InterruptedException {
        $(byCssSelector("[class=\"btn btn-primary btn-block btn-outline\"]")).click();//кликнуть на кнопку "Ответить" сверху
        HashMap<Integer, String> WildeQuotes = new HashMap();//map with messages (O.Wilde quotes)
        WildeQuotes.put(1, "Be yourself; everyone else is already taken. (O.Wilde)");    //we put some values here and below
        WildeQuotes.put(2, "We are all in the gutter, but some of us are looking at the stars. (O.Wilde)");
        WildeQuotes.put(3, "To live is the rarest thing in the world. Most people exist, that is all. (O.Wilde)");
        WildeQuotes.put(4, "If one cannot enjoy reading a book over and over again, there is no use in reading it at all. (O.Wilde)");
        WildeQuotes.put(5, "The truth is rarely pure and never simple. (O.Wilde)");
        WildeQuotes.put(6, "Never love anyone who treats you like you're ordinary. (O.Wilde)");
        WildeQuotes.put(7, "A good friend will always stab you in the front. (O.Wilde)");

        Random generator = new Random(); //это для получения случайного значения из map WildeQuotes
        Object[] values = WildeQuotes.values().toArray(); //это для получения случайного значения из map WildeQuotes
        randomMessage = (String) values[generator.nextInt(values.length)]; //это для получения случайного значения из map WildeQuotes
        System.out.println(randomMessage);
        Thread.sleep(5000);
        $(byId("editor-textarea")).shouldBe(visible).sendKeys(randomMessage);//проверить видимость текстового поля и добавить сообщение
        Thread.sleep(3000);
        $(byText("Отправить ответ")).shouldBe(visible);//найти кнопку "Отправить ответ", проверить видимость
        $(byText("Отправить ответ")).click();//найти кнопку "Отправить ответ" и кликнуть
        Thread.sleep(3000);
    }

    /**
     * we check if the added message is displayed on the page
     * @throws InterruptedException
     */
    public void checkMessage() throws InterruptedException {
        $(byText(randomMessage)).shouldBe(visible);//проверить видимость отправленного сообщения на странице
        Thread.sleep(3000);
    }

    /**
     * we open the list of topics (Topics tab)
     * @throws InterruptedException
     */
    public void openTopics() throws InterruptedException {
        $(By.xpath("//ul[@class = 'nav navbar-nav']//a[contains(text(), 'Темы')]")).scrollTo().shouldBe(visible).click();//найти вкладку Темы и кликнуть
        Thread.sleep(4000);
    }

    @Before
    public void before() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver_win32\\Chromedriver.exe");
        Configuration.timeout = 5000;
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-save-password-bubble");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
        WebDriverRunner.setWebDriver(driver);
        System.out.println("Test started");
    }
    @After
    public void after() {
        System.out.println("Test finished");
        WebDriverRunner.closeWebDriver();
    }
    @Test
    public void test() throws InterruptedException {
        //hopefully, it's the right way to put everything in methods and then to call them here?
        //yes, thread.sleeps are really necessary here, I think, because tests fail without them, idk why
        openWebsite();
        logIn();
        openTopic();
        addMessage();
        checkMessage();
        openTopics();
        openTopic();
        addMessage();
        checkMessage();
    }
}
