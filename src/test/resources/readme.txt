README

*GIT

    git add .
    git commit -m "message"
    git push origin "branchname"

    git status

    git pull


*ALLURE

plugins {
    id 'java-library'
    id 'io.qameta.allure' version '2.10.0'
}

allure {
    report {
        version.set("2.19.0")
    }
    adapter { // отвечает за появление папочки build/allure-results
        aspectjWeaver.set(true) //обработка аннотации @Step
        frameworks {
            junit5 { //название фреймворка
                adapterVersion.set("2.19.0") //версия интеграции фреймворка и Allure
            }
        }
    }
}

dependencies {
    testImplementation (
            'io.qameta.allure:allure-selenide:2.19.0'
    )
}

gradle :allureReport --clean

SelenideLogger.addListener("allure", new AllureSelenide());

Для запуска отчета можно в терминале в директории проекта выполнить команду (\\AQA_examples\build) allure serve allure-results

****** CI GITHUB
1. Добавляем в build.gradle
test {
    systemProperties(System.getProperties())
}
2. Добавить файл .github/workflows/test.yaml
on: workflow_dispatch //ручной запуск тестов
on [push] //запуск тестов при пуше
3. Отправить изменения в репозиторий
4. В репозитории во вклдаке Actions запустить тесты через CI
5. Далее перейти в Setting - Pages
Source - Deploy from branch
Branch - main / root
6. Setting - Action - General
Workflow permisson - read and write


****** JENKINS CI
1. Добавляем Теги к тестовому классу / методам
2. В build.gradle добавляем блок с задачей (task)
3. В Jenkins создаем "New Item" - "название" - FreeStyle project
Source Code Management - GIT - ссылка на репозиторий
Branches to build - */main (ветка из которой запускаем тесты)
Build Invoke Gradle Gradle 7.4.1
Tasks clean files_test (files_test - название задачи в файле build.gradle)
Post-build Actions - Allure Report - build/allure-results (путь до папки allure-results, можно посмотреть в папке Workspace)

********* Удаленный запуск на Selenoid
1. Добавляем свойства
    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.browser = "chrome";
        Configuration.browserVersion = "100.0";
        Configuration.baseUrl = "https://demoqa.com";
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
