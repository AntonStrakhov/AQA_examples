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