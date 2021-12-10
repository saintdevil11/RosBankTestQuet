package steps;

import PageElements.RegistrationPage;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static hooks.Allerts.checkAlert;

public class RegistrationSteps extends RegistrationPage {

    @Step("Открыта страница Регистрации")
    public static void RegistrationPageOpened() {
        spinerWait();
        RegistrationPageIsOpened();
    }

    @Step("Нажимаем на кнопку Зарегистрироваться")
    public static void clickOnRegistrationButton() {
        getRegistrationButton().should(Condition.visible).click();
    }

    @Step("Вводим Имя {name}")
    public static void inputName(String name){
        $x(inputName).click();
        checkAlert();
        $x(inputName).sendKeys(name);
    }

    @Step("Вводим Страну {country}")
    public static void inputCountry(String country){
        $x(inputCountry).click();
        checkAlert();
        $x(inputCountry).sendKeys(country);
    }
}
