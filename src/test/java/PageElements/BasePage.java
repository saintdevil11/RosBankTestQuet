package PageElements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class BasePage {

    public static final String inputUid = "//input[@id='uid']";
    public static final String inputPass = "//input[@id='password']";
    public static final String inputSId = "//*[@name='sessionId']";

    static final SelenideElement loginButton = $x("//button[@type='submit']");

    public static void setCheckBox(String name, String condition) {
        SelenideElement checkBox = $x("//label[./span[text()='" + name + "']]//input");
        if (condition.equals("false")) {
            if (checkBox.$x("./../..").getAttribute("class").contains("Mui-checked")) {
                checkBox.click();
            }
        } else {
            if (!checkBox.$x("./../..").getAttribute("class").contains("Mui-checked")) {
                checkBox.click();
            }
        }
    }

    public static SelenideElement getLoginButton() {
        return loginButton;
    }

    public static SelenideElement getButton(String name) {
        SelenideElement uniButton = $x("//*[@class='MuiButton-label' and text() = '" + name + "']");
        return uniButton;
    }

    public static SelenideElement grtHrefButton(String name) {
        SelenideElement uniHref = $x("//*[contains(@class,'MuiTypography-root') and text() = '" + name + "']");
        return uniHref;
    }

    public static void spinerWait() {
        $x("//div[@role='progressbar']").shouldNot(Condition.visible, Duration.ofMillis(50000));
    }
}
