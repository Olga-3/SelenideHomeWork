package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebElement;
import pages.annotations.Element;
import pages.annotations.Page;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;

public abstract class AbstractPage {
    static final LinkedList<Class<? extends AbstractPage>> CLASSES = new LinkedList<>();

    static {
        CLASSES.add(MainPage.class);
        CLASSES.add(AuthorizationWindow.class);
    }

    public static String getUrlByTitle(String title) throws ClassNotFoundException {
        for (Class<? extends AbstractPage> clazz : CLASSES) {
            if (clazz.isAnnotationPresent(Page.class)) {
                if (clazz.getAnnotation(Page.class).title().equals(title)) {
                    return clazz.getAnnotation(Page.class).url();
                }
            }
        }
        throw new ClassNotFoundException("Окно с заголовком " + title + " не найдено в списке");
    }

//    public static String getUrl(String url) {
//        return url;
//    }

    public static AbstractPage getPageByTitle(String title) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        for (Class<? extends AbstractPage> clazz : CLASSES) {
            if (clazz.isAnnotationPresent(Page.class)) {
                if (clazz.getAnnotation(Page.class).title().equals(title)) {
                    return clazz.newInstance();
                }
            }
        }
        throw new ClassNotFoundException("Окно с заголовком " + title + " не найдено в списке");
    }

    public SelenideElement getElementByName(String name) throws InvocationTargetException, IllegalAccessException {
        for (Method method : this.getClass().getMethods()) {
            if (method.isAnnotationPresent(Element.class)) {
                Element element = method.getAnnotation(Element.class);
                if (element.value().equals(name)) {
                    return (SelenideElement) method.invoke(this);
                }
            }
        }
        throw new RuntimeException();
    }
}
