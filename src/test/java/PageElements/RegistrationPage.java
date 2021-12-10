package PageElements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class RegistrationPage extends BasePage{

    public static final String inputName = "//input[@id='name']";
    public static final String inputCountry = "//input[@id='country']";

    static final SelenideElement registrationButton = $x("//button[@type='submit']");

    public static void RegistrationPageIsOpened() {
        $x("//h1[contains(text(),'Регистрация v')]").should(Condition.visible);
    }

    public static SelenideElement getRegistrationButton() {
        return registrationButton;
    }

}
