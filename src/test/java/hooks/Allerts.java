package hooks;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Allerts {

    public static void checkAlert() {
        WebDriverWait wait = new WebDriverWait(WebDriverRunner.getWebDriver(), 1);
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            String actualText = Selenide.switchTo().alert().getText();
            String text = "Ваше устройство не поддерживается в регионе ";
            if (!actualText.contains(text)) {
                Assertions.assertTrue(actualText.contains(text));
            } else {
                Selenide.switchTo().alert().accept();
            }
        } catch (TimeoutException ex) {
        }
    }
}
