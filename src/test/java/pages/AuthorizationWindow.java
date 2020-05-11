package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.annotations.Element;
import pages.annotations.Page;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

//тут адреса всех нужных элементов окна авторизации (сюда ссылаются шаги в MyStepdefs)
@Page(title = "страница авторизации", url = "https://dev.n7lanit.ru")

public class AuthorizationWindow extends AbstractPage{
    @Element("Забыли пароль?")
    public SelenideElement forgotPass() {
        return  $(byText("Забыли пароль?"));//xpath кнопки "Забыли пароль?" в окне авторизации
    }
    @Element("логин")
    public SelenideElement inputLogin() {
        return $(byId("id_username"));//xpath поля для ввода логина
    }
    @Element("пароль")
    public SelenideElement inputPassword() {
        return $(byId("id_password")); //xpath поля для ввода пароля
    }
    @Element("Войти")
    public SelenideElement authorize() {
        return $(byXpath("//*[@id=\"modal-mount\"]/div/div/form/div[2]/button"));//xpath кнопки Войти в окне авторизации
    }
}
