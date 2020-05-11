//package pages;
//
//import com.codeborne.selenide.ElementsCollection;
//import com.codeborne.selenide.SelenideElement;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
//import pages.annotations.Element;
//import pages.annotations.Page;
//
//import static com.codeborne.selenide.Condition.visible;
//import static com.codeborne.selenide.Selectors.*;
//import static com.codeborne.selenide.Selenide.$;
//import static com.codeborne.selenide.Selenide.$$;
//
////тут адреса всех нужных элементов вкладки Темы (сюда ссылаются шаги в MyStepdefs)
//@Page(title = "вкладка Темы", url = "https://dev.n7lanit.ru")
//
//public class TopicsTab extends AbstractPage{
//
//    @Element("случайная тема")
//    public SelenideElement randomTopic() {
//        ElementsCollection collection = $$(By.xpath("//a[contains(@class, 'item-title thread-title') and not(contains(text(), 'Опрос'))]"));//найти вcе элементы определенного класса без текста "Опрос"
//        return collection.get((int) (collection.size() * Math.random()));//взять элемент (тема) под случайным номером в коллекции
//    }
//}
