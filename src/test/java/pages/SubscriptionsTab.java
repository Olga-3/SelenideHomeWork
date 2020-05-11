package pages;

import com.codeborne.selenide.ElementsCollection;
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

@Page(title = "подписки", url = "https://dev.n7lanit.ru/subscribed/")
public class SubscriptionsTab {
//    @Element("вкладка Темы")
//    public WebElement getTopicsTab() {
//        return $(By.xpath("//ul[@class = 'nav navbar-nav']//a[contains(text(), 'Темы')]"));//найти элемент Темы по xpath
//    }
}
