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

@Page(title = "вкладка подписки", url = "https://dev.n7lanit.ru/subscribed")
public class SubscriptionsTab {

    @Element("активна")
    public SelenideElement active() {
        return $(By.xpath("//div[@class='col-sm-2 col-md-2 hidden-xs']//div[@class='btn-group']//button[@class='btn btn-default btn-icon btn-block btn-subscribe btn-subscribe-half dropdown-toggle']"));
    }

    @Element("отписаться")
    public SelenideElement unsubscribe() {
        return $(By.xpath("//div[@class='col-sm-2 col-md-2 hidden-xs']//div[@class='btn-group open']//button[@class='btn-link' and text()='Отписаться ']"));
    }
}
