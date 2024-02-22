package files;

import com.codeborne.selenide.Condition;
import jdk.jfr.Description;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Tag("files")
public class SelenideFilesTest {

    //Если у кнопки нет атрибута href, скачать такой файл можно через proxy-server
    //В selenoid такой метод работать не будет
//    static {
//        Configuration.fileDownload = FileDownloadMode.PROXY;
//    }


    @Description("Скачиваем файл по кнопке у которой есть атрибут href (присутствует в 95% случаев")
    @Test
    void selenideDownloadTest() throws Exception {
        open("https://github.com/junit-team/junit5/blob/main/README.md");
        File downloadedFile = $("[data-testid=raw-button]").download();
        try (InputStream is = new FileInputStream(downloadedFile)){
        byte[] bytes = is.readAllBytes();
        String textContent = new String(bytes, StandardCharsets.UTF_8);
        assertThat(textContent).contains("This repository is the home of _JUnit 5_");
        }
    }

    @Description("Подгружаем файл на сайт (В большинстве случае локатор будет input[type='file']")
    @Test
    void selenideUploadFile() {
        open("https://fineuploader.com/demos.html");
        $("input[type='file']").uploadFromClasspath("example/cat.png");
        $("span.qq-upload-file-selector").shouldHave(Condition.attribute("title", "cat.png"));
    }
}