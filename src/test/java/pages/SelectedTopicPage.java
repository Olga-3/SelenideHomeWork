package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.annotations.Element;
import pages.annotations.Page;
import stepdefs.*;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

//тут адреса всех нужных элементов страницы выбранной темы (сюда ссылаются шаги в MyStepdefs)


//@Page(title = "страница выбранной темы", url = stepdefs.MyStepdefs.topicUrl)
//public class SelectedTopicPage extends AbstractPage {
//}
