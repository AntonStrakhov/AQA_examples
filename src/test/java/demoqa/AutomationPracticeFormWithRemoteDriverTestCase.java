package demoqa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import demoqa.pages.RegistrationPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class AutomationPracticeFormWithRemoteDriverTestCase{

    RegistrationPage registrationPage = new RegistrationPage();

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.browser = "chrome";
        Configuration.browserVersion = "100.0";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        // для запуска тестов на удаленном сервере селенид
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

        // добавялем опции для запуска браузера в окошке селеноида и запись видео
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));

        Configuration.browserCapabilities = capabilities;
    }

    @Test
    @Tag("remote")
    void fillFormTest() {
        String userFirstname = "Ant";
        String userSurname = "Str";
        String userEmail = "antstrwwefw@gmail.com";
        String userNumber = "9999999999";
        String gender = "Male";
        String dayOfBirth = "12";
        String monthOfBirth = "September";
        String yearOfBirth = "1986";
        String hobby = "Sports";
        String imagePath = "test.png";
        String state = "NCR";
        String city = "Delhi";
        String subject = "Maths";

        registrationPage.openPage();
        //Строка ниже нужна для запуска на Selenoid
        //$(byText("Соглашаюсь")).click();
        //Строка ниже нужна для запуска на Jenkins не нужна
        $(byText("Consent")).click();
        registrationPage.checkFormTitle("Student Registration Form")
        .setFirstName(userFirstname)
                .setLastName(userSurname)
                .setEmail(userEmail)
                .setGender(gender)
                .setPhone(userNumber)
                .setBirthDate(dayOfBirth, monthOfBirth, yearOfBirth)
                .setHobby(hobby)
                .setState(state)
                .setCity(city)
                .setPicture(imagePath)
                .setSubject(subject)
                .submitForm()
        ;


        registrationPage.verifyResultsModalAppears()
                .verifyResult("Student Name", text(userFirstname + " " + userSurname))
                .verifyResult("Student Email", text(userEmail))
                .verifyResult("Gender", text(gender))
                .verifyResult("Mobile", text(userNumber))
                .verifyResult("Date of Birth", text(dayOfBirth + " " + monthOfBirth + "," + yearOfBirth))
                .verifyResult("Hobbies", text(hobby))
                .verifyResult("Subjects", text(subject))
                .verifyResult("Picture", text(imagePath))
                .verifyResult("Address", empty)
                .verifyResult("State and City", text(state + " " + city))
        ;
    }
}