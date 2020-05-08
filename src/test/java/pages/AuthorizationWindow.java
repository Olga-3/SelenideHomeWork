package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.annotations.Element;
import pages.annotations.Page;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
@Page(title = "страница авторизации", url = "https://dev.n7lanit.ru")

public class AuthorizationWindow extends AbstractPage{
    @Element("Забыли пароль?")
    public SelenideElement forgotPass() {
        return  $(byText("Забыли пароль?"));
    }
}
