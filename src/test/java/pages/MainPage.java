package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.annotations.Element;
import pages.annotations.Page;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;

@Page(title = "главная страница", url = "https://dev.n7lanit.ru")
public class MainPage extends AbstractPage {


    @Element("вкладка Темы")
    public WebElement getTopicsTab() {
        return $(By.xpath("//ul[@class = 'nav navbar-nav']//a[contains(text(), 'Темы')]"));
    }
//    @Element("окно авторизации")
//    public void WebElement getLogInWindow() {
//        //(value = "logIn")
//        $(byText("Войти")).shouldBe(visible).click();//найти элемент по тексту и проверить видимость, кликнуть
//        $(byText("Забыли пароль?")).shouldBe(visible);//найти элемент по тексту и проверить видимость
//    }

    @Element("Войти")
    public SelenideElement logIn() {
        //(value = "logIn")
        return $(byText("Войти"));//найти элемент по тексту и проверить видимость, кликнуть
    }
//
//    @Element("поле ввода логина")
//    public WebElement logInField(String login) {
//        return $(byId("id_username")).sendKeys(login);
//    }
}
