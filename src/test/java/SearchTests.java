import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SearchTests {
    @Test
    void successfulSearchTestYandex() {
       //Configuration.headless = true;
      //Configuration.browser = "edge";
        //Configuration.timeout = 10000;
        open("https://www.ya.ru/");
        $("[name=text]").setValue("selenide").pressEnter();
        $("[id=search-result]").shouldHave(text("ru.selenide.org"));
    }

    @Test
    void testAlfa() {
        open("https://www.alfabank.ru/");
        $(byText("Вклады")).click();
        $("body").shouldHave(text("Вклады"));
        $$(byText("Вклады")).find(visible).click();
        $("body").shouldHave(text("Альфа-Вклад Максимальный"));
    }
}
