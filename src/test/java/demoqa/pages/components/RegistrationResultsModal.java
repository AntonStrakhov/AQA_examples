package demoqa.pages.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationResultsModal {
    public void verifyModalAppears() {
        $(".modal-dialog").should(appear);
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
    }

    public void verifyResult(String key, Condition value) {
        SelenideElement modal = $("[role=dialog]");
        modal.shouldBe(visible);

        SelenideElement tableTbody = modal.$("table tbody");

        tableTbody.$(byText(key)).parent()
                .$("td", 1)
                .shouldBe(value);
    }
}
