package PageElements;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage extends BasePage{

    public static void loginPageIsOpened(){
        $x("//h1[contains(text(),'Тест v')]").should(Condition.visible);
    }
}
