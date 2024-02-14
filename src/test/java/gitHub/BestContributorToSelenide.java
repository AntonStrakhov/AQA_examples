package gitHub;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class BestContributorToSelenide {

    @Test
    void solntsevShouldBeTheTopContributor() {
        // открыть страницу репозиторя селенида
        open("https://github.com/selenide/selenide");
        // подвести мышку к первому аватору из блока contributors
        $(".BorderGrid").$(byText("Contributors")).ancestor(".BorderGrid-row")
                .$$("ul li").first().hover();
        // проверка: во всплывающем окне есть текст Andrei Solntsev
        $$(".Popover .Popover-message").findBy(visible).shouldHave(text("Andrei Solntsev"));
    }
}