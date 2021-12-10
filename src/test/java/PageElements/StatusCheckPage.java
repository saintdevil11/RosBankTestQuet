package PageElements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class StatusCheckPage extends BasePage {

    private static SelenideElement textMessage = $x("//h2[contains(@class,'MuiTypography-root')]");

    public static void statusCheckPageIsOpened() {
        $x("//h1[contains(text(),'Проверка статуса v')]").should(Condition.visible);
    }

    public static String checkText() {
        String text = textMessage.should(Condition.visible).getText();
        return text;
    }
}
