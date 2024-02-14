package gitHub;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SelenideRepositorySearch {

    @Test
    void shouldFindSelenideRepositoryAtTheTop() throws InterruptedException {
        open("https://github.com/");
        $(byText("Search or jump to...")).click();
        $("[name=query-builder-test]").setValue("selenide").pressEnter();
        $$("div.Box-sc-g0xbh4-0.bDcVHV").first().$("a").click();
        $("#repository-container-header").shouldHave(text("Selenide / Selenide"));
    }
}
