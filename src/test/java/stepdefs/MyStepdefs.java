package stepdefs;

import pages.*;
import com.codeborne.selenide.ElementsCollection;
import cucumber.api.java.ru.И;
import cucumber.api.java.ru.*;
import org.openqa.selenium.By;
import pages.AbstractPage;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Random;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;


public class MyStepdefs {
        MainPage mainPage = new MainPage();

        private String randomMessage;
        //MyStepdefs stepdefs = new MyStepdefs();
//        @Пусть("я как пользователь открыла сайт")
//        public void openedSite() {
//                System.out.println("Открываем сайт для тестирования");
//                open("https://dev.n7lanit.ru/");
//        };

        @Пусть("я как пользователь открыла {string}")
        public void openedSite(String site) throws ClassNotFoundException {
                System.out.println("Открываем сайт для тестирования");
                open(AbstractPage.getUrlByTitle(site));
        };

        @И("открыла окно авторизации")
        public void openedSignInWindow() throws IllegalAccessException, InstantiationException, ClassNotFoundException, InvocationTargetException {
                System.out.println("Открываем окно авторизации");
                AbstractPage.getPageByTitle("главная страница").getElementByName("Войти")
                .shouldBe(visible).click();//найти элемент по тексту и проверить видимость
                AbstractPage.getPageByTitle("страница авторизации").getElementByName("Забыли пароль?")
                .shouldBe(visible);//найти элемент по тексту и проверить видимость, кликнуть
        };
//
//        @И("открыла {string}")
//        public void openedSignInWindow(String login) throws InvocationTargetException, IllegalAccessException {
//                System.out.println("Открываем окно авторизации");
//                mainPage.getElementByName(login).click();
//        };

        @Затем("авторизовалась с {string} и {string}")
        public void signedIn(String login, String password) {
                System.out.println("Авторизуемся с логином \"" + login + "\" и паролем \"" + password + "\".");
                $(byId("id_username")).sendKeys(login);//ввести логин
                $(byId("id_password")).sendKeys(password);//ввести пароль
                $(byXpath("//*[@id=\"modal-mount\"]/div/div/form/div[2]/button")).click(); // кликнуть на кнопку Войти
        };
        @Тогда("отобразился аватар")
        public void avatarIsPresent() {
                System.out.println("Проверяем, отобразился ли аватар");
                $(byClassName("user-avatar")).shouldBe(visible);//проверить авторизацию-отображается ли аватар
        };
        @И("я открыла тему без опроса")
        public void openedTopic() throws InterruptedException {
                System.out.println("Открываем тему");
                ElementsCollection collection = $$(By.xpath("//a[contains(@class, 'item-title thread-title') and not(contains(text(), 'Опрос'))]"));//найти вcе элементы определенного класса без текста "Опрос"
                collection.get((int) (collection.size() * Math.random())).click();//взять элемент (тема) под случайным номером в коллекции
                //*[@id="page-mount"]/div/div[2]/div[2]/ul/li[8]/div[2]/div[1]/div/div[2]/div/div[1]/span[1]/span[2]
                Thread.sleep(3000);
        };
        @И("написала сообщение")
        public void addedMessage() throws InterruptedException {
                System.out.println("Пишем туда рандомно выбранную из HashMap цитату Оскара Уайльда");
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
                Thread.sleep(4000);
                $(byId("editor-textarea")).shouldBe(visible).sendKeys(randomMessage);//проверить видимость текстового поля и добавить сообщение
                Thread.sleep(3000);
                $(byText("Отправить ответ")).shouldBe(visible);//найти кнопку "Отправить ответ", проверить видимость
                $(byText("Отправить ответ")).click();//найти кнопку "Отправить ответ" и кликнуть
        };
        @Тогда("мое сообщение отобразилось на странице")
        public void messageIsSent() {
                System.out.println("Проверяем, отображается ли в теме добавленное сообщение");
                $(byText(randomMessage)).shouldBe(visible);//проверить видимость отправленного сообщения на странице
        };
        @Затем("я открыла вкладку Темы")
        public void openedTab() {
                System.out.println("Открываем вкладку с темами");
                //найти вкладку Темы и кликнуть
                $(By.xpath("//ul[@class = 'nav navbar-nav']//a[contains(text(), 'Темы')]")).scrollTo().shouldBe(visible).click();
        };
        @И("повторила добавление сообщения")
        public void addedMessageAgain() throws InterruptedException {
                System.out.println("Повторяем шаги с добавлением темы");
                openedTopic();
                addedMessage();
                messageIsSent();
                openedTab();
        };
}
