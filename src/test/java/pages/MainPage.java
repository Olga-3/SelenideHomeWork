package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.annotations.Element;
import pages.annotations.Page;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

//тут адреса всех нужных элементов главной страницы (сюда ссылаются шаги в MyStepdefs)
@Page(title = "главная страница", url = "https://dev.n7lanit.ru")
public class MainPage extends AbstractPage {



    @Element("вкладка Темы")
    public WebElement getTopicsTab() {
        return $(By.xpath("//ul[@class = 'nav navbar-nav']//a[contains(text(), 'Темы')]"));//найти элемент Темы по xpath
    }

    @Element("Войти")
    public SelenideElement logIn() {
        //return $(byText("Войти"));//найти элемент по тексту
        return $(By.xpath(".//*[@class='btn navbar-btn btn-default btn-sign-in']"));
    }

    @Element("аватар")
    public SelenideElement avatar() {
        return $(byClassName("user-avatar"));//найти элемент по тексту
    }

    @Element("случайная тема")
    public SelenideElement randomTopic() {
        ElementsCollection collection = $$(By.xpath("//a[contains(@class, 'item-title thread-title') and not(contains(text(), 'Опрос'))]"));//найти вcе элементы определенного класса без текста "Опрос"-но это
        return collection.get((int) (collection.size() * Math.random()));//взять элемент (тема) под случайным номером в коллекции
    }

    @Element("выбранная тема")
    public SelenideElement chosenTopic() {
        return  $(By.xpath("//*[@id=\"page-mount\"]/div/div[1]/div/div[2]/h1"));
    }

    @Element("неактивна")
    public SelenideElement InactiveSubscription() throws InterruptedException {
        ElementsCollection collection2 = $$(By.xpath("//*[text()='Неактивна']"));//найти вcе элементы определенного класса
        Thread.sleep(3000);
        return collection2.get((int) (collection2.size() * Math.random()));//взять элемент под случайным номером в коллекции
    }
    @Element("подписаться")
    public SelenideElement subscribe() {
        //return $(By.xpath("//*[text()='Подписаться']"));//работает,вроде бы, но через раз
        return $(By.xpath("//li[@class='btn-link')][text()='Подписаться']"));
    }
    @Element("активна")
    public SelenideElement active() {
        return $(By.xpath("//*[text()='Активна']"));
    }

}
