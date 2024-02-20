package demoqa;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.text;
import static utils.RandomUtils.randomEmail;
import static utils.RandomUtils.randomString;

public class AutomationPracticeFormWithJavaFakerTestCase extends TestBase {

    @Test
    void fillFormTest() {
        //Faker faker = new Faker();
        Faker faker = new Faker(new Locale("ru"));



        String userFirstname = faker.name().firstName();
        String userSurname = faker.name().lastName();
        String userEmail = faker.internet().emailAddress();
        String userNumber = faker.number().digits(10);
        String gender = "Male";
        String dayOfBirth = "12";
        String monthOfBirth = "September";
        String yearOfBirth = "1986";
        String hobby = "Sports";
        String imagePath = "test.png";
        String state = "NCR";
        String city = "Delhi";
        String subject = "Maths";

        registrationPage.openPage()
                .checkFormTitle("Student Registration Form")
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