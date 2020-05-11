package stepdefs;

import com.codeborne.selenide.SelenideElement;
import cucumber.api.java.ru.Затем;
import cucumber.api.java.ru.И;
import cucumber.api.java.ru.Пусть;
import cucumber.api.java.ru.Тогда;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.AbstractPage;
import pages.annotations.Element;

import java.lang.reflect.InvocationTargetException;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.Assert.assertEquals;


public class MyStepdefs {

        public static String topicUrl = ""; //сюда я присваиваю url случайно выбранной в шаге страницы, но
        //но не получается использовать его в специально созданном классе SelectedTopicMessage
        private String randomMessage;//этой переменной присваивается рандомно выбранное из array значение
        private String topicName; //тут будет сохранен текст-название случайно выбранной темы для теста 1
       // public String topicUrl;

        @Element("Ответить")
        public WebElement addMessage() {
                return $(byCssSelector("[class=\"btn btn-primary btn-block btn-outline\"]"));//найти элемент Темы по xpath
        }

//адреса элементов описаны в классе AuthorizationWindow (элементы окна авторизации: поля для ввода логина и пароля и т.д.)
//и в классе MainPage (элементы Войти, аватар, темы..)

        //Пусть я как пользователь открыла "главная страница"
        @Пусть("я как пользователь открыла {string}")
        public void openedSite(String mainPage) throws ClassNotFoundException {
                System.out.println("Открываем сайт для тестирования");
                open(AbstractPage.getUrlByTitle(mainPage));
        }

        //И на "главная страница" открыла окно авторизации
        @И("на {string} открыла окно авторизации")//тут mainPage = "главная страница", это название связано с адресом в классе MainPage
        public void openedSignInWindow(String mainPage) throws IllegalAccessException, InstantiationException, ClassNotFoundException, InvocationTargetException {
                System.out.println("Открываем окно авторизации");
                AbstractPage.getPageByTitle(mainPage).getElementByName("Войти")
                .shouldBe(visible).click();//найти элемент по тексту и проверить видимость, кликнуть
                AbstractPage.getPageByTitle("страница авторизации").getElementByName("Забыли пароль?")
                .shouldBe(visible);//найти элемент по тексту и проверить видимость
        }

        //Затем на "страница авторизации" авторизовалась с <login> и <password>
        @Затем("на {string} авторизовалась с {string} и {string}") //тут authPage = "страница авторизации", это название связано с адресом в классе AuthorizationWindow
        public void signedIn(String authPage, String login, String password) throws IllegalAccessException, InstantiationException, ClassNotFoundException, InvocationTargetException {
                System.out.println("Авторизуемся с логином \"" + login + "\" и паролем \"" + password + "\".");
                AbstractPage.getPageByTitle(authPage).getElementByName("логин")
                        .shouldBe(visible).sendKeys(login);// проверить видимость поля для ввода логина, ввести логин
                AbstractPage.getPageByTitle(authPage).getElementByName("пароль")
                        .shouldBe(visible).sendKeys(password); // проверить видимость поля для ввода пароля, ввести пароль
                AbstractPage.getPageByTitle(authPage).getElementByName("Войти")
                        .shouldBe(visible).click(); // кликнуть на кнопку Войти
        }

        //Тогда на "главная страница" отобразился аватар
        @Тогда("на {string} отобразился аватар")//тут mainPage = "главная страница", это название связано с адресом в классе MainPage
        public void avatarIsPresent(String mainPage) throws IllegalAccessException, InstantiationException, ClassNotFoundException, InvocationTargetException {
                System.out.println("Проверяем, отобразился ли аватар");
                AbstractPage.getPageByTitle(mainPage).getElementByName("аватар").shouldBe(visible);
        }

        //И на "главная страница" я открыла "вкладка Темы"
        @И("на {string} я открыла {string}")//тут mainPage = "главная страница", topicsTab - "вкладка Темы"
        public void openedTopicsTab(String mainPage, String topicsTab) throws IllegalAccessException, InstantiationException, ClassNotFoundException, InvocationTargetException {
                System.out.println("Открываем вкладку \"Темы\"");
                AbstractPage.getPageByTitle(mainPage).getElementByName(topicsTab).shouldBe(visible).click();//открыть вкладку Темы
                //Thread.sleep(3000);
        }

        //И на "главная страница" открыла "случайная тема"
        @И("на {string} открыла {string}")//тут mainPage = "главная страница"
        public void openedTopic(String mainPage, String randomTopic) throws InterruptedException, IllegalAccessException, InstantiationException, ClassNotFoundException, InvocationTargetException {
                System.out.println("Открываем случайную тему");
                SelenideElement randomTopicElement = AbstractPage.getPageByTitle(mainPage).getElementByName(randomTopic);//взять элемент (тема) под случайным номером в коллекции
                topicName = randomTopicElement.getText();//получаем текст-название случайно выбранной темы

                System.out.println("Выбрана тема: " + topicName);//выводим название случайно выбранной темы
                randomTopicElement.shouldBe(visible).click();//кликнуть на случайно выбранный элемент(тема)
                topicUrl = url();//получаем URL страницы с выбранной темой
                assertEquals(topicName, AbstractPage.getPageByTitle(mainPage).getElementByName("выбранная тема").getText());//проверим, открылась ли тема с тем самым полученным текстом в названии
                //Thread.sleep(3000);
        }

        //И написала сообщение
        @И("написала сообщение")
        public void addedMessage() {
                System.out.println("Пишем туда рандомно выбранную из массива цитату Оскара Уайльда: ");

                $(byCssSelector("[class=\"btn btn-primary btn-block btn-outline\"]")).shouldBe(visible).click();//кликнуть на кнопку "Ответить" сверху

                randomMessage = pages.TopicMessage.PickMessage();//используем метод для выбора случайн.значения (метод находится во вспомогательном классе TopicMessage)
                System.out.println(randomMessage);
                //Thread.sleep(4000);
                $(byId("editor-textarea")).shouldBe(visible).sendKeys(randomMessage);//проверить видимость текстового поля и добавить сообщение
                //Thread.sleep(3000);
                $(byText("Отправить ответ")).shouldBe(visible).click();//найти кнопку "Отправить ответ", проверить видимость, кликнуть
        }

        //Тогда мое сообщение отобразилось на странице
        @Тогда("мое сообщение отобразилось на странице")
        public void messageIsSent() {
                System.out.println("Проверяем, отображается ли в теме добавленное сообщение");
                $(byText(randomMessage)).shouldBe(visible);//проверить видимость отправленного сообщения на странице
        }

        //
        @Затем("я открыла {string}")
        public void openedTab(String topicsTab) {
                System.out.println("Открываем вкладку с темами");
                //найти вкладку Темы и кликнуть
                //AbstractPage.getElementByName(topicsTab).shouldBe(visible).click();
                //AbstractPage.getPageByTitle(mainPage).getElementByName(topicsTab).shouldBe(visible).click();
                $(By.xpath("//ul[@class = 'nav navbar-nav']//a[contains(text(), 'Темы')]")).scrollTo().shouldBe(visible).click();
        }

        //И повторила добавление сообщения
        @И("повторила добавление сообщения")
        public void addedMessageAgain() throws InterruptedException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
                System.out.println("Повторяем шаги с добавлением темы");
                openedTopic("главная страница", "случайная тема");
                addedMessage();
                messageIsSent();
                openedTab("вкладка Темы");
        }

        //И на "главная страница" нажала на элемент "неактивна" случайной темы
        @И("на {string} нажала на элемент {string} случайной темы")//тут mainPage = "главная страница"
        public void clickInactive(String mainPage, String inactive) throws InterruptedException, IllegalAccessException, InstantiationException, ClassNotFoundException, InvocationTargetException {
                System.out.println("Нажимаем на Неактивна");
                SelenideElement randomInactiveElement = AbstractPage.getPageByTitle(mainPage).getElementByName(inactive).shouldBe(visible);//взять элемент (тема) под случайным номером в коллекции
                //String InactiveTopic = randomInactiveElement.getText();//получаем текст-название случайно выбранной темы

                //System.out.println("Выбрана тема: " + InactiveTopic);//выводим название случайно выбранной темы
                randomInactiveElement.shouldBe(visible).scrollTo().click();//кликнуть на случайно выбранный элемент Неактивна
                System.out.println("текст род элемента: " + randomInactiveElement.getWrappedElement().getText());//?
                Thread.sleep(3000);
        }

        //Затем на "главная страница" в выпадающем списке выбрала "подписаться"
        @Затем("на {string} в выпадающем списке выбрала {string}")
        public void clickSubscribe(String mainPage, String subscribe) throws InterruptedException, IllegalAccessException, InstantiationException, ClassNotFoundException, InvocationTargetException {
                System.out.println("В выпадающем списке выбираем Подписаться");
                AbstractPage.getPageByTitle(mainPage).getElementByName(subscribe).shouldBe(visible).click();//кликнуть на Подписаться
                Thread.sleep(3000);
        }

        //И на "главная страница" убедилась, что подписка "активна"
        @И("на {string} убедилась, что подписка {string}")
        public void isActive(String mainPage, String active) throws InterruptedException, IllegalAccessException, InstantiationException, ClassNotFoundException, InvocationTargetException {
                System.out.println("проверяем, что подписка Активна");
                AbstractPage.getPageByTitle(mainPage).getElementByName(active).shouldBe(visible);
                Thread.sleep(3000);
        }

        //И на "главная страница" так же подписалась на другую тему
        @И("на {string} так же подписалась на другую тему")
        public void repeatSubscribe(String mainPage) throws InterruptedException, IllegalAccessException, InstantiationException, ClassNotFoundException, InvocationTargetException {
                System.out.println("подписываемся еще на одну тему");
                clickInactive(mainPage, "неактивна");
                clickSubscribe(mainPage, "подписаться");
                isActive(mainPage, "активна");
                Thread.sleep(3000);
        }

        //Затем на "главная страница" открыла "вкладка подписки"
        @Затем("на {string} открыла страницу {string}")
        public void openSubscriptions(String mainPage, String subscriptionsTab) throws InterruptedException, IllegalAccessException, InstantiationException, ClassNotFoundException, InvocationTargetException {
                AbstractPage.getPageByTitle(mainPage).getElementByName(subscriptionsTab).shouldBe(visible).click();
        }


}
