package steps;

import PageElements.StatusCheckPage;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

public class StatusCheckSteps extends StatusCheckPage {

    @Step("Открыта страница Проверки статуса пользователя")
    public static void statusCheckPageOpened() {
        spinerWait();
        statusCheckPageIsOpened();
    }

    @Step("Проверяем, что текст верные имя пользователя: {name} и Страну: {country}")
    public static void checkStatusText(String name, String country) {
        String actualText = checkText();
        String text1 = "Уважаемый '" + name + "'";
        String text2 = "регионе '" + country + "'";
        Assertions.assertTrue(actualText.contains(text1));
        Assertions.assertTrue(actualText.contains(text2));
    }

}
